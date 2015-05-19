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
package fr.bmartel.protocol.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * UDP Client used to broadcast messages to an UDP server
 * 
 * @author Bertrand Martel
 *
 */
public class UdpClient implements IUdpClient {

	/**
	 * broadcast address
	 */
	private InetAddress address = null;

	/**
	 * buffer size for expecting message
	 */
	private int bufferSize = 4096;

	/**
	 * udp server port
	 */
	private int serverPort = -1;

	/**
	 * thread used for waiting for server response 
	 * 
	 * TODO: put a timer and set a timeout
	 */
	private Thread readingThread = null;

	/**
	 * list of client event listener
	 */
	private ArrayList<IudpEventListener> udpClientEventListener = new ArrayList<IudpEventListener>();

	/**
	 * Build UDP client message object
	 *
	 * @param message
	 *            message to be sent
	 * @param address
	 *            address tro broadcast
	 * @param serverPort
	 *            udp server port
	 */
	public UdpClient(InetAddress address, int serverPort) {
		this.address = address;
		this.serverPort = serverPort;
	}

	/**
	 * Broadcast a specific message
	 */
	@Override
	public void sendMessage(String message) {

		DatagramSocket udpSocket = null;

		try {

			// create socket
			udpSocket = new DatagramSocket();

			DatagramPacket packet;

			// create packet to be sent to server
			packet = new DatagramPacket(message.getBytes(), message.length(),
					this.address, this.serverPort);

			// broadcast data
			udpSocket.setBroadcast(true);

			udpSocket.send(packet);

			final DatagramSocket socket = udpSocket;

			if (readingThread != null) {
				readingThread.interrupt();
				readingThread.join();
			}

			readingThread = new Thread(new Runnable() {

				@Override
				public void run() {

					byte[] message = new byte[bufferSize];

					DatagramPacket datagramePacket = new DatagramPacket(
							message, message.length);

					// block until one message is received
					try {
						socket.receive(datagramePacket);
					} catch (IOException e) {
						e.printStackTrace();
					}

					byte[] data = Arrays.copyOfRange(message, 0,
							datagramePacket.getLength());

					if (data != null) {
						for (int i = 0; i < udpClientEventListener.size(); i++) {
							udpClientEventListener.get(i).onDataReceived(data);
						}
					}
					if (socket != null) {
						socket.close();
					}
				}
			});
			readingThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * add udp client event listener to listener list
	 * 
	 * @param listener
	 *            listener to add
	 */
	@Override
	public void addClientEventListener(IudpEventListener listener) {
		udpClientEventListener.add(listener);
	}

	@Override
	public void close() {
		if (readingThread != null) {
			readingThread.interrupt();
			try {
				readingThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void cleanEventListeners() {
		udpClientEventListener.clear();
	}
}
