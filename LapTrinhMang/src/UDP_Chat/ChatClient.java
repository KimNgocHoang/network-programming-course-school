package UDP_Chat;

import java.net.*;
import java.util.*;

public class ChatClient {

	public static void main(String[] args) throws Exception {
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		Scanner msgSend = new Scanner(System.in);
		while(true)
		{
			System.out.print("Client: ");
			sendData = msgSend.nextLine().getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1234);
			clientSocket.send(sendPacket);
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			String msgServer = new String(receivePacket.getData());
			//String msgServer = new String(receivePacket.getData(), 0, receivePacket.getLength());
			System.out.println("Server: " + msgServer);
			//clientSocket.close();
		}
		
	}

}
