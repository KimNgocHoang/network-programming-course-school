package UDP_Chuoi;

import java.net.*;
import java.util.*;
public class ChuoiClient {

	public static void main(String[] args) throws Exception{
		DatagramSocket clientSocket = new DatagramSocket(); //gan cong
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		Scanner st = new Scanner(System.in);
		while(true)
		{
			System.out.println("Nhap chuoi: ");
			sendData = st.nextLine().getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8888);
			clientSocket.send(sendPacket);
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			
			String str= new String(receivePacket.getData());
			System.out.println(str);
			clientSocket.close();
		}
		//
	}

}
