package UDP_Swing;


import java.net.*;


public class UDPServerChuoi {
	
	public static void main(String[] args) throws Exception{
		System.out.println("Server is started");
		DatagramSocket socket = new DatagramSocket(4444);
		while(true)
		{
			new UDPThreadChuoi(socket).start();
			
		}

	}

}
