# QT4 C++ Non blocking HTTP Server socket

<b>QT C++ Non Blocking HTTP server socket</b>

Instantiation and start : 

```
HttpServer server;

server.listen(QHostAddress(IP),PORT);

```

You can add socket event listener that notify you of incominh http request/response 

```
ClientSocketHandler *clientHandler = new ClientSocketHandler();

server.addClientEventListener(clientHandler);
```

``ClientSocketHandler`` inherit from interface ``IClientEventListener`` which contains following methods :
* ``void onHttpRequestReceived(IHttpClient &client,Ihttpframe* consumer,std::string peer_address);`` : notify when http request is received
* ``void onHttpResponseReceived(IHttpClient &client,Ihttpframe* consumer,std::string peer_address);`` : notify when http response is received

You can send http response or http request back to the client with ``IHttpClient`` got from previous callback.

 ``IHttpClient`` has a ``sendHttpMessage(std::string message)`` for sending http message back to client

<hr/>