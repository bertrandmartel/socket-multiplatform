/**
    ClientSocketHandler.cpp
    Client socket managing event handler

    @author Bertrand Martel
    @version 1.0
*/
#include "ClientSocketHandler.h"

#include "httpserver_inter/IHttpClient.h"
#include "iostream"
#include "string.h"
#include "protocol/inter/http/IhttpFrame.h"

using namespace std;

static int PAGE_VIEW_COUNT = 0;

ClientSocketHandler::ClientSocketHandler()
{
}

ClientSocketHandler::~ClientSocketHandler()
{
}

/**
 * called when an http response has been received from client
 *
 * @param client
 * 		client object
 * @param message
 * 		message delivered
 */
void ClientSocketHandler::onHttpResponseReceived(IHttpClient &client,Ihttpframe * frame)
{
    cout << "http response received" << endl;
    client.sendHttpMessage("OK I received your message !");
}

/**
 * called when an http response has been received from client
 *
 * @param client
 * 		client object
 * @param message
 * 		message delivered
 */
void ClientSocketHandler::onHttpRequestReceived(IHttpClient &client,Ihttpframe * frame)
{
    cout << "Http request received" << endl;

    if (strcmp(frame->getMethod().data(),"GET")==0 && strcmp(frame->getUri().data(),"/index")==0)
    {
        cout << "Send Hello to client" << endl;
        string body = "Hello from custom CPP HTTP Server\r\nThis page has been seen " + std::to_string(PAGE_VIEW_COUNT)+ " times before.";
        client.sendHttpMessage("HTTP/1.1 200 OK\r\nContent-Length:  " + std::to_string(body.length()) + "\r\n\r\n" + body + "\r\n");
        PAGE_VIEW_COUNT++;
    }
}
