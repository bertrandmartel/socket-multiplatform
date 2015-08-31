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
    clientsocket.cpp

    http client object featuring one physical http connection

    @author Bertrand Martel
    @version 1.0
*/
#include "clientSocket.h"

#include <QTcpSocket>
#include <iostream>
#include "QHostAddress"

using namespace std;

/**
 * @brief ClientSocketObj::ClientSocketObj
 *      Build one client
 */
ClientSocket::ClientSocket()
{
    clientSocket=0;
}

/**
 * @brief ClientSocket::~ClientSocket
 *      clean pointers
 */
ClientSocket::~ClientSocket()
{
    //client socket should NOT be deleted here => connection MUST be deleted in HttpServer class
}

/**
 * @brief getPeerAddress
 *      Retrieve client ip address
 * @return
 */
std::string ClientSocket::getPeerAddress(){
    return clientSocket->peerAddress().toString().toStdString();
}

/**
 *  close websoclet client object
 *
 * @return
 * 		0 if success -1 if error
 */
int ClientSocket::close()
{
    clientSocket->close();

    return 0;
}

/**
 * Send a message to HTTP client
 *
 * @param string
 * 		HTTP Message to be sent to client
 * @return
 *		0 if success -1 if error
 */
int ClientSocket::sendHttpMessage(std::string message)
{
    clientSocket->write(message.data());
    clientSocket->flush();

    return 0;
}

/**
 * @brief setSocketClient
 *      set socket client socket
 * @param clientSocket
 */
void ClientSocket::setSocketClient(QTcpSocket * clientSocket)
{
    this->clientSocket=clientSocket;
}
