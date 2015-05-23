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
package fr.bmartel.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import fr.bmartel.protocol.udp.IUdpServer;
import fr.bmartel.protocol.udp.IUdpServerEventListener;
import fr.bmartel.protocol.udp.IudpEventListener;
import fr.bmartel.protocol.udp.UdpClient;
import fr.bmartel.protocol.udp.UdpServer;

/**
 * Launch UDP server socket and build one UDP client that will send message to
 * the latter server, server will response according to client's message. UDP
 * client will read until it receives the message response from the server.
 * 
 * Server will sent response back to client according to client's address and
 * respective port
 * 
 * @author Bertrand Martel
 *
 */
public class LaunchUdpSocket {

	/**
	 * server port
	 */
	private final static int PORT = 8443;

	private final static String SERVER_BROADCAST_IP = "255.255.255.255";

	/**
	 * Launch udp server socket and client udp socket instance
	 */
	public static void main(String[] args) {

		// set instance of UDP server
		UdpServer udpServer = new UdpServer(PORT);

		udpServer.addServerEventListener(new IUdpServerEventListener() {

			@Override
			public void onDataReceived(byte[] data, IUdpServer server,
					InetAddress clientAddress, int clientPort) {
				String dataStr = new String(data);

				System.out
						.println("[UDP SERVER] new data receveived in server : "
								+ dataStr);

				switch (dataStr) {
				case "HELLO":
					server.write("Hello from UDP server".getBytes(),
							clientAddress, clientPort);
					break;
				case "GOODBYE":
					server.write(
							"Goodbye, See you from UDP server".getBytes(),
							clientAddress, clientPort);
					break;
				case "T_T":
					server.write("O_O from UDP server".getBytes(),
							clientAddress, clientPort);
					break;
				}

			}
		});

		// start UDP server
		Thread serverThread = new Thread(udpServer,"UDP SERVER");
		serverThread.start();

		// setup UDP client
		try {
			UdpClient udpClient = new UdpClient(
					InetAddress.getByName(SERVER_BROADCAST_IP), PORT);

			udpClient.addClientEventListener(new IudpEventListener() {

				@Override
				public void onDataReceived(byte[] data) {
					System.out
							.println("[UDP CLIENT] new data receveived in client : "
									+ new String(data));
				}
			});

			// you can choose here which command to send to the server
			Scanner scan = new Scanner(System.in);

			System.out
					.println("------------------------------------------------");
			System.out.println("Started UDP chat with server "
					+ SERVER_BROADCAST_IP + ":" + PORT);
			System.out
					.println("------------------------------------------------");
			System.out.println("List of chat command :");
			System.out.println("HELLO");
			System.out.println("GOODBYE");
			System.out.println("T_T");
			System.out.println("EXIT");
			System.out
					.println("------------------------------------------------");

			String command = "";

			while (!command.equals("EXIT")) {

				command = scan.nextLine();

				switch (command) {
				case "HELLO":
					udpClient.sendMessage("HELLO");
					break;
				case "GOODBYE":
					udpClient.sendMessage("GOODBYE");
					break;
				case "T_T":
					udpClient.sendMessage("T_T");
					break;
				case "EXIT":
					break;
				default:
					System.out.println("Unknown command");
				}

			}
			System.out.println("Exiting UDP chat ...");

			// clean event listener list
			udpClient.cleanEventListeners();

			udpClient.close();

			// close server
			if (udpServer != null) {
				udpServer.close();
				serverThread.join();
			}
			
			scan.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
