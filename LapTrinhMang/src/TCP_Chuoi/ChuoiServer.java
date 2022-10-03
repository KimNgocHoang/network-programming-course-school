package TCP_Chuoi;

import java.io.*;
import java.util.*;
import java.net.*;

public class ChuoiServer {
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
	
	public static void main(String[] args) throws Exception {
		ChuoiServer s = new ChuoiServer();
		ServerSocket server = new ServerSocket(7000);
		System.out.println("Server is started");
		Socket socket = server.accept();
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		
		while(true)
		{
			//Nhan du lieu tu Client
			String st =dis.readUTF();
			System.out.println(st); //Chuoi nhan duoc tu Client hien thi len Server
			System.out.println("Server: ");
			dos.writeUTF("Server: \n" +s.UpperCase(st) + "\n" +s.LowerCase(st) + "\n" 
			+s.LowerAndUpperCase(st) + "\n" +s.demtu(st) + "\n" +s.NguyenAm(st) + "\n");              
			dos.flush();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
