package TCP_Swing;

import java.io.*;
import java.util.*;
import java.net.*;

public class TCPServerChat {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(7777);
		System.out.println("Server is started");
		while(true)
		{
			Socket socket = server.accept();
			System.out.println("A new client has connected!");
			if(socket.isConnected())
			{
				
			}
			TCPThread tcp = new TCPThread(server, socket);
			Thread th = new Thread( tcp);
			th.start();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
