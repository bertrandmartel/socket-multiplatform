package fr.bmartel.protocol.udp;

/**
 * Generic interface for UDP client
 * 
 * @author Bertrand Martel
 *
 */
public interface IUdpClient {

	/**
	 * close udp client => will stop receiving thread if exists
	 */
	public void close();

	/**
	 * add udp client event listener to listener list
	 * 
	 * @param listener
	 *            listener to add
	 */
	public void addClientEventListener(IudpEventListener listener);

	/**
	 * Broadcast a specific message
	 */
	public void sendMessage(String message);

	/**
	 * clear event listener list
	 */
	public void cleanEventListeners();
}
