package UDP_Chat;

import java.net.*;
import java.util.Scanner;

public class ChatServer {
	private static DatagramPacket inPacket, outPacket;
	
	
	public static void main(String[] args) throws Exception{
		
		DatagramSocket serverSocket = new DatagramSocket(1234);
		System.out.println("Server is started");
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		//nhap tin nhan de gui toi client
		Scanner kb = new Scanner(System.in);
		while (true)
		{
			DatagramPacket inPacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(inPacket);
			InetAddress IPAddress = inPacket.getAddress();
			int port = inPacket.getPort();
			String messageClient = new String(inPacket.getData(), 0, inPacket.getLength());
			System.out.println("Client: " + messageClient);
			System.out.print("Server: ");
			String messageServer = kb.nextLine();
			sendData = messageServer.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
			
			
		}
	}

}
