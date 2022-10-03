package TCP_Chat;

import java.io.*;
import java.util.*;
import java.net.*;
public class ChatServer {

	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(7000);
		System.out.println("Server is started");
		Socket socket = server.accept();
		//2 bien din và dos nhan va gui du lieu cua socket
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		DataInputStream din = new DataInputStream(socket.getInputStream());
		//Nhap chuoi de gui den Client
		Scanner kb = new Scanner(System.in);
		while(true)
		{
			//Nhan du lieu tu Client thong din
			String st= din.readUTF();
			System.out.println(st); // Chuoi nhan duoc tu Client hien thi tren server
			System.out.print("Server: ");
			String msg = kb.nextLine();
			dos.writeUTF("Server: " +msg);
			dos.flush();
			kb = kb.reset();
		}
	}

}
