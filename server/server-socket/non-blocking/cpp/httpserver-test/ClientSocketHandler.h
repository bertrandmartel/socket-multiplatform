/**
    ClientSocketHandler.h
    Client socket managing event handler

    @author Bertrand Martel
    @version 1.0
*/
#ifndef CLIENTSOCKETHANDLER_H
#define CLIENTSOCKETHANDLER_H

#include "httpserver_inter/IClientEventListener.h"

/**
 * @brief The ClientSocketHandler class
 *      Client socket managing event handler
 */
class ClientSocketHandler :  public IClientEventListener
{
    public:

         /**
         * @brief ClientSocketHandler
         *      build client socket handler
         */
        ClientSocketHandler();

        ~ClientSocketHandler();

        /**
         * called when an http request has been received from client
         *
         * @param client
         * 		client object
         * @param message
         * 		message delivered
         */
        void onHttpRequestReceived(IHttpClient &client,Ihttpframe* consumer);

        /**
         * called when an http response has been received from client
         *
         * @param client
         * 		client object
         * @param message
         * 		message delivered
         */
        void onHttpResponseReceived(IHttpClient &client,Ihttpframe* consumer);
};

#endif // CLIENTSOCKETHANDLER_H
