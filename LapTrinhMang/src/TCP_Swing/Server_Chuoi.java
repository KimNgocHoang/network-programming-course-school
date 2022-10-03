package TCP_Swing;

import java.io.*;
import java.util.*;

import TCP_Chuoi.ChuoiServer;

import java.net.*;

public class Server_Chuoi {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(9999);
		System.out.println("Server is started");
		
		while(true)
		{
			new ThreadSocket_Chuoi(server.accept()).start();              
			
		}
	}

}
