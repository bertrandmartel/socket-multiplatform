/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Bertrand Martel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
/**
    ClientSocketHandler.cpp
    Client socket managing event handler

    @author Bertrand Martel
    @version 1.0
*/
#include "ClientSocketHandler.h"

#include "httpserverinter/IHttpClient.h"
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
void ClientSocketHandler::onHttpResponseReceived(IHttpClient &client,Ihttpframe * frame,std::string peer_address)
{
    cout << "http response received for client " << peer_address << endl;
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
void ClientSocketHandler::onHttpRequestReceived(IHttpClient &client,Ihttpframe * frame,std::string peer_address)
{
    cout << "Http request received for client " << peer_address << endl;

    if (strcmp(frame->getMethod().data(),"GET")==0 && strcmp(frame->getUri().data(),"/index")==0)
    {
        cout << "Send Hello to client" << endl;
        string body = "Hello from custom CPP HTTP Server\r\nThis page has been seen " + std::to_string(PAGE_VIEW_COUNT)+ " times before.";
        client.sendHttpMessage("HTTP/1.1 200 OK\r\nContent-Length:  " + std::to_string(body.length()) + "\r\n\r\n" + body + "\r\n");
        PAGE_VIEW_COUNT++;
    }
}
