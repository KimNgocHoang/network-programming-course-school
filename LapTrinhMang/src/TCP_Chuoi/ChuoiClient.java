package TCP_Chuoi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.*;

public class ChuoiClient {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 7000);
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		
		//Nhap chuoi gui den Server
		Scanner kb = new Scanner(System.in);
		while(true)
		{
			System.out.print("Client: ");
			String msg = kb.nextLine(); //Nhap chuoi gui len server
			dos.writeUTF(msg);
			dos.flush();
			
			//Client nhan du lieu tu Server gui lai
			String st = dis.readUTF();
			System.out.println(st);
			kb=kb.reset();
		}

	}

}
