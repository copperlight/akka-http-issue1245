
Example of behavior:

```
$ sbt run

... runs fine for a while ...

[DEBUG] [07/04/2017 11:29:49.974] [default-akka.actor.default-dispatcher-6] [default/Pool(shared->http://localhost:51171)] [0] Idle -> Loaded(1)
[DEBUG] [07/04/2017 11:29:49.975] [default-akka.actor.default-dispatcher-6] [default/Pool(shared->http://localhost:51171)] [0] </127.0.0.1:51173->localhost:51171> pushing request to connection: GET /test Empty
server received request 22
server sent response 22
[DEBUG] [07/04/2017 11:29:50.981] [default-akka.actor.default-dispatcher-5] [default/Pool(shared->http://localhost:51171)] [0] </127.0.0.1:51173->localhost:51171> Received response: GET /test Empty -> 200 OK Empty
[DEBUG] [07/04/2017 11:29:50.981] [default-akka.actor.default-dispatcher-5] [default/Pool(shared->http://localhost:51171)] [0] </127.0.0.1:51173->localhost:51171> Finished reading response entity for GET /test Empty -> 200 OK Empty
[DEBUG] [07/04/2017 11:29:50.981] [default-akka.actor.default-dispatcher-5] [default/Pool(shared->http://localhost:51171)] [0] Loaded(1) -> Idle
HttpResponse(200 OK,List(Date: Tue, 04 Jul 2017 18:29:50 GMT),HttpEntity.Strict(none/none,ByteString()),HttpProtocol(HTTP/1.1))
[DEBUG] [07/04/2017 11:29:50.982] [default-akka.actor.default-dispatcher-6] [akka://default/user/StreamSupervisor-0] now supervising Actor[akka://default/user/StreamSupervisor-0/flow-45-0-ignoreSink#2069019155]
[DEBUG] [07/04/2017 11:29:50.982] [default-akka.actor.default-dispatcher-5] [akka://default/user/StreamSupervisor-0/flow-45-0-ignoreSink] started (akka.stream.impl.fusing.ActorGraphInterpreter@2a844a7e)
[DEBUG] [07/04/2017 11:29:50.982] [default-akka.actor.default-dispatcher-5] [akka://default/user/StreamSupervisor-0/flow-45-0-ignoreSink] stopped
[DEBUG] [07/04/2017 11:29:51.977] [default-akka.actor.default-dispatcher-5] [default/Pool(shared->http://localhost:51171)] [0] Idle -> Loaded(1)
[DEBUG] [07/04/2017 11:29:51.977] [default-akka.actor.default-dispatcher-5] [default/Pool(shared->http://localhost:51171)] [0] </127.0.0.1:51173->localhost:51171> pushing request to connection: GET /test Empty
server received request 23
server sent response 23
[DEBUG] [07/04/2017 11:29:51.980] [default-akka.actor.default-dispatcher-2] [default/Pool(shared->http://localhost:51171)] [0] </127.0.0.1:51173->localhost:51171> Received response: GET /test Empty -> 200 OK Empty
[DEBUG] [07/04/2017 11:29:51.980] [default-akka.actor.default-dispatcher-2] [default/Pool(shared->http://localhost:51171)] [0] </127.0.0.1:51173->localhost:51171> Finished reading response entity for GET /test Empty -> 200 OK Empty
[DEBUG] [07/04/2017 11:29:51.980] [default-akka.actor.default-dispatcher-2] [default/Pool(shared->http://localhost:51171)] [0] Loaded(1) -> Idle
HttpResponse(200 OK,List(Date: Tue, 04 Jul 2017 18:29:51 GMT),HttpEntity.Strict(none/none,ByteString()),HttpProtocol(HTTP/1.1))
[DEBUG] [07/04/2017 11:29:51.981] [default-akka.actor.default-dispatcher-5] [akka://default/user/StreamSupervisor-0] now supervising Actor[akka://default/user/StreamSupervisor-0/flow-47-0-ignoreSink#132240001]
[DEBUG] [07/04/2017 11:29:51.981] [default-akka.actor.default-dispatcher-6] [akka://default/user/StreamSupervisor-0/flow-47-0-ignoreSink] started (akka.stream.impl.fusing.ActorGraphInterpreter@6baf5e18)
[DEBUG] [07/04/2017 11:29:51.981] [default-akka.actor.default-dispatcher-6] [akka://default/user/StreamSupervisor-0/flow-47-0-ignoreSink] stopped
[DEBUG] [07/04/2017 11:29:53.982] [default-akka.actor.default-dispatcher-2] [default/Pool(shared->http://localhost:51171)] [0] Idle -> Loaded(1)
[DEBUG] [07/04/2017 11:29:53.982] [default-akka.actor.default-dispatcher-2] [default/Pool(shared->http://localhost:51171)] [0] </127.0.0.1:51173->localhost:51171> pushing request to connection: GET /test Empty
server received request 24
[DEBUG] [07/04/2017 11:29:53.985] [default-akka.actor.default-dispatcher-5] [default/Pool(shared->http://localhost:51171)] Shutting down host connection pool
[DEBUG] [07/04/2017 11:29:53.987] [default-akka.actor.default-dispatcher-2] [default/Pool(shared->http://localhost:51171)] [0] </127.0.0.1:51173->localhost:51171> Slot was stopped
[DEBUG] [07/04/2017 11:29:53.987] [default-akka.actor.default-dispatcher-2] [default/Pool(shared->http://localhost:51171)] [1] <unconnected> Slot was stopped
[DEBUG] [07/04/2017 11:29:53.987] [default-akka.actor.default-dispatcher-2] [default/Pool(shared->http://localhost:51171)] [2] <unconnected> Slot was stopped
[DEBUG] [07/04/2017 11:29:53.987] [default-akka.actor.default-dispatcher-2] [default/Pool(shared->http://localhost:51171)] [3] <unconnected> Slot was stopped
[DEBUG] [07/04/2017 11:29:53.993] [default-akka.actor.default-dispatcher-5] [akka://default/user/pool-master/PoolInterfaceActor-0] stopped
[DEBUG] [07/04/2017 11:29:53.994] [default-akka.actor.default-dispatcher-2] [akka://default/user/pool-master] received AutoReceiveMessage Envelope(Terminated(Actor[akka://default/user/pool-master/PoolInterfaceActor-0#-447846004]),Actor[akka://default/user/pool-master/PoolInterfaceActor-0#-447846004])
server sent response 24
waiting for response for 2.003 seconds
waiting for response for 4.008 seconds
waiting for response for 6.01 seconds
waiting for response for 8.014 seconds
waiting for response for 10.017 seconds
waiting for response for 12.021 seconds
waiting for response for 14.021 seconds
waiting for response for 16.024 seconds
waiting for response for 18.025 seconds
waiting for response for 20.03 seconds
waiting for response for 22.033 seconds
[DEBUG] [07/04/2017 11:30:16.466] [default-akka.actor.default-dispatcher-5] [akka://default/system/IO-TCP/selectors/$a/0] stopped
[DEBUG] [07/04/2017 11:30:16.467] [default-akka.actor.default-dispatcher-6] [akka://default/system/IO-TCP/selectors/$a] received AutoReceiveMessage Envelope(Terminated(Actor[akka://default/system/IO-TCP/selectors/$a/0#1082023394]),Actor[akka://default/system/IO-TCP/selectors/$a/0#1082023394])
[DEBUG] [07/04/2017 11:30:16.467] [default-akka.actor.default-dispatcher-2] [akka://default/user/StreamSupervisor-1/flow-0-0-mergePreferred] stopped
waiting for response for 24.036 seconds
waiting for response for 26.039 seconds
waiting for response for 28.043 seconds
waiting for response for 30.046 seconds
waiting for response for 32.048 seconds
waiting for response for 34.051 seconds
```
