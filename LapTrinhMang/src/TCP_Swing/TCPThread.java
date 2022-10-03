package TCP_Swing;

import java.io.*;
import java.net.*;
import java.util.*;

public class TCPThread extends Thread {
	
	private Socket socket;
	ServerSocket server;
	private DataInputStream dis;
	private DataOutputStream dos;
	private int id;
	private static Hashtable allCon = new Hashtable();

	public TCPThread(ServerSocket server, Socket socket) {
		this.id = allCon.size();
		allCon.put(socket,this);
		this.server= server;
		this.socket=socket;
	}
	public void run(){
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());

			String host  =dis.readUTF();
			messageAllSockets(host+" da tham gia vao phong chat.");
			String receiveData;
			while(( receiveData = dis.readUTF())!=null){
				messageAllSockets(host+": "+receiveData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void messageAllSockets(String message){
		synchronized (allCon) {
			for (Enumeration e = allCon.elements();
					e.hasMoreElements();) {
				TCPThread tcpt = (TCPThread)
						e.nextElement();
				try {
					tcpt.dos.writeUTF(message);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}
