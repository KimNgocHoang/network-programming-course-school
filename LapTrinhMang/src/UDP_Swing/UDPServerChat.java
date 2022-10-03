package UDP_Swing;

import java.io.IOException;
import java.net.*;

public class UDPServerChat {

    public static void main(String[] args) throws IOException {
    	
			System.out.println("Bat dau phien lam viec cua Server");
			DatagramSocket socket = new DatagramSocket(9999);
			while(true){
				 byte[] receivedata = new byte[1024];
                   DatagramPacket receivePakage = new DatagramPacket(receivedata, receivedata.length);
                   socket.receive(receivePakage);
                   UDPThread udp = new UDPThread(socket, receivePakage);
                   udp.start();	
				}
			
    }
}
