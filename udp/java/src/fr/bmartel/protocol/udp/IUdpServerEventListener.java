package fr.bmartel.protocol.udp;

import java.net.InetAddress;

/**
 * Generic interface for Server event listener
 * 
 * @author Bertrand Martel
 *
 */
public interface IUdpServerEventListener {

	/**
	 * called when data is received by udp server
	 * 
	 * @param data
	 *            data received
	 */
	public void onDataReceived(byte[] data, IUdpServer server,
			InetAddress clientAddress, int clientPort);

}
