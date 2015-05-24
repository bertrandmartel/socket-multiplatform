package fr.bmartel.protocol.http.server;

import fr.bmartel.protocol.http.listeners.IHttpServerEventListener;

/**
 * Template for http server interface featuring close and events
 * 
 *
 * @author Bertrand Martel
 *
 */
public interface IHttpServer {

	/**
	 * close http server
	 */
	public void closeServer();

	/**
	 * Add Client event listener to server list for library user to be notified
	 * of all server events
	 * 
	 * @param listener
	 */
	public void addServerEventListener(IHttpServerEventListener listener);

}
