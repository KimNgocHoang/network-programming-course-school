package UDP_Swing;

import java.io.*;
import java.net.*;


public class UDPThreadChuoi extends Thread {
	protected DatagramSocket socket=null;
	//1. Chuoi hoa
			public String UpperCase(String s)
			{
				char c;
				String inhoa = "";
				for(int i =0; i<s.length(); i++ )
				{
					c = s.charAt(i);
					if(c >= 'a' && c <= 'z')
					{
						c = (char)(c-32);
					}
					inhoa += c;
				}
				return inhoa;
			}
			
			//2. Chuoi thuong
			public String LowerCase(String s)
			{
				char c;
				String inthuong ="";
				for(int i=0; i<s.length();i++)
				{
					c = s.charAt(i); // lay ki tu thu i cua chuoi st
					if(c>='A' && c<='Z')
					{
						c =(char) (c+32);
					}
					inthuong+=c;
				}
				return inthuong;
			}
			//3. Vua hoa vua thuong
			public String LowerAndUpperCase(String s)
			{
				char c;
				String hoathuong ="";
				for(int i=0; i<s.length();i++)
				{
					c = s.charAt(i); // lay ki tu thu i cua chuoi st
					if(c>='A' && c<='Z')
					{
						c =(char) (c+32);
					}
					else if((c>='a' && c<='z'))
					{
						c =(char) (c-32);
					}
					hoathuong+=c;
				}
				return hoathuong;
			}
			
			//4. Dem tu cua chuoi
			public int demtu(String s)
			{
				int d=0;
				boolean khdem = true;
				for(int i=0; i<s.length();i++)
				{
					if(s.charAt(i) !=' ' && s.charAt(i) != '\t') //  
					{
						if(khdem)
						{
							d++;
							khdem = false;
						}
					}
					else khdem = true;
				}
				return d;
			}
			
			//5. Dem cac nguyen am
			public int NguyenAm(String s)
			{
				String s1 ="";
				int d=0;
				s1 = LowerCase(s);
				for(int i =0; i<s1.length(); i++)
				{
					char c = s1.charAt(i);
					if(c=='a'|| c == 'e' || c == 'i' || c == 'o' || c == 'u')
					{
						d++;
					}
				}
				return d;
			}
	
	public UDPThreadChuoi(DatagramSocket socket) throws IOException {
		this.socket = socket;
	}
	public void run()
	{
		try {
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			socket.receive(receivePacket);
			InetAddress IPAddress= receivePacket.getAddress();
			int port = receivePacket.getPort();
			String str= new String(receivePacket.getData());
			System.out.println("From CLient: "+ str);
			String ser = "Chuoi Hoa: "+ UpperCase(str).trim() + "\n" + "Chuoi thuong: "+ LowerCase(str).trim() + "\n"
					+ "Chuoi Vua hoa vua thuong: " + LowerAndUpperCase(str).trim() + "\n" + "So tu cua chuoi: "+ demtu(str) + "\n"
					+ "So cac nguyen am: "+ NguyenAm(str) + "\n";
			sendData = ser.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			socket.send(sendPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

