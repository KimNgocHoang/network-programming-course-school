package TCP_Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.*;

public class ChatClient {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 7000);
		DataInputStream din = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		// Nhap chuoi de gui den server
		Scanner kb = new Scanner(System.in);
		while(true)
		{
			System.out.print("Client: ");
			String msg = kb.nextLine(); //Nhap chuoi gui len server
			dos.writeUTF("Client: " +msg);
			dos.flush();
			// Client nhan du lieu tu Server gui lai
			String st = din.readUTF();
			System.out.println(st);
			kb = kb.reset();
		}
	}

}
