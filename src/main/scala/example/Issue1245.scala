package example

import java.io.InputStream
import java.net.InetSocketAddress
import java.util.concurrent.atomic.AtomicInteger

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.model.Uri
import akka.stream.ActorMaterializer
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer

import scala.util.Failure
import scala.util.Success

object Issue1245 {

  private def ignore(in: InputStream): Unit = {
    try {
      val buf = new Array[Byte](4096)
      while (in.read(buf) > 0) {}
    } finally {
      in.close()
    }
  }

  def main(args: Array[String]): Unit = {

    // to minimize noise in the logs, we do not use akka-http for the server side
    val count = new AtomicInteger()
    val server = HttpServer.create(new InetSocketAddress(0), 100)
    val port = server.getAddress.getPort

    val handler = new HttpHandler {
      override def handle(exchange: HttpExchange): Unit = {
        val i = count.incrementAndGet()
        println(s"server received request $i")
        ignore(exchange.getRequestBody)

        if (i % 2 == 0) Thread.sleep(1000L)
        exchange.sendResponseHeaders(200, -1L)
        exchange.close()
        println(s"server sent response $i")
      }
    }

    server.createContext("/test", handler)
    server.start()

    import scala.concurrent.ExecutionContext.Implicits.global
    implicit val system: ActorSystem = ActorSystem()
    implicit val mat: ActorMaterializer = ActorMaterializer()

    val uri = Uri(s"http://localhost:$port/test")
    val request = HttpRequest(HttpMethods.GET, uri)

    @volatile var requestInProgress = false
    @volatile var startTime = System.currentTimeMillis()
    while (true) {
      if (!requestInProgress) {
        requestInProgress = true
        startTime = System.currentTimeMillis()

        Http().singleRequest(request)
          .andThen {
            case _ => requestInProgress = false
          }
          .onComplete {
            case Success(response) =>
              response.discardEntityBytes()
              println(response)
            case Failure(t) =>
              println(s"${t.getClass}: ${t.getMessage}")
          }

      } else {
        val duration = (System.currentTimeMillis() - startTime) / 1000.0
        println(s"waiting for response for $duration seconds")
      }

      Thread.sleep(2000L)
    }
  }
}

