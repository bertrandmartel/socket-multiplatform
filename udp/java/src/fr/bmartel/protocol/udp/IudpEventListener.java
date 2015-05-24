package fr.bmartel.protocol.udp;

/**
 * Udp Server event listener
 * 
 * @author Bertrand Martel
 *
 */
public interface IudpEventListener {

	/**
	 * called when data is received by udp server
	 * 
	 * @param data
	 *            data received
	 */
	public void onDataReceived(byte[] data);

}
