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

    // Setup a server, to minimize noise in the logs we do not use akka-http for the
    // server side.
    val count = new AtomicInteger()
    val server = HttpServer.create(new InetSocketAddress(0), 100)
    val port = server.getAddress.getPort
    server.createContext("/test", exchange => {
      val i = count.incrementAndGet()
      println(s"server received request $i")
      ignore(exchange.getRequestBody)

      // By alternating between fast and slow responses we make it more likely the pool
      // will get shutdown just after a request has gone out with a slow response.
      if (i % 2 == 0) Thread.sleep(1000L)
      exchange.sendResponseHeaders(200, -1L)
      exchange.close()
      println(s"server sent response $i")
    })
    server.start()

    import scala.concurrent.ExecutionContext.Implicits.global
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()

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

      // This should match the idle-timeout for the connection pool. To reproduce making the
      // idle-timeout slightly smaller seems to help
      Thread.sleep(2000L)
    }
  }
}

