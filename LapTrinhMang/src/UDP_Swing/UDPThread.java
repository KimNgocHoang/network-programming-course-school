package UDP_Swing;

import java.io.*;
import java.net.*;
import java.util.*;

public class UDPThread extends Thread {
	private DatagramSocket server;
	private int id;
	private DatagramPacket sendPacket;
	private DatagramPacket receivePacket;
	private byte[] senddata;
	private byte[] receivedata;
	private static Hashtable allCon = new Hashtable();
	private InetAddress address;
	private int port;

	public UDPThread(DatagramSocket socketserver, DatagramPacket receivePacket) {
		this.id = allCon.size();
		this.server= socketserver;
		this.receivePacket = receivePacket;
	}
	
	public void run(){
		try {
			receivedata = new byte[1024];
			receivedata = receivePacket.getData();
			String msg = new String(receivedata).trim();

			address = receivePacket.getAddress();
			port = receivePacket.getPort();
			if(!allCon.containsKey(port)){
				allCon.put(port,address);
			}
			message(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void message(String msg) throws IOException{
		synchronized (allCon) {
			Enumeration allPort;
			allPort = allCon.keys();
			while(allPort.hasMoreElements()){
				port = (Integer) allPort.nextElement();
				address = (InetAddress) allCon.get(port);
				senddata = new byte[1024];
				senddata = (msg).getBytes();
				sendPacket = new DatagramPacket(senddata,
						senddata.length, address, port);
				server.send(sendPacket);
			}
		}
	}

}

