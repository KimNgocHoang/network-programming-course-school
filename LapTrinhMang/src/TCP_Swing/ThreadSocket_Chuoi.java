package TCP_Swing;

import java.io.*;
import java.net.*;

	
public class ThreadSocket_Chuoi extends Thread {
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	public ThreadSocket_Chuoi(Socket socket)
	{
		try
		{
			this.socket=socket;
			this.dos = new DataOutputStream(socket.getOutputStream());
			this.dis = new DataInputStream(socket.getInputStream());
		}
		catch(IOException e)
		{
			
		}
		
	}
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
	public void run()
	{
		try
		{
			System.out.println(socket);
			String st =dis.readUTF();
			System.out.println("From CLient: "+ st);
			String xl = "Chuoi hoa: " +UpperCase(st) + "\n" + "Chuoi thuong: " +LowerCase(st) + "\n" 
					+"Chuoi mix hoa va thuong: " + LowerAndUpperCase(st) + "\n" 
					+"So tu cua chuoi: " +demtu(st) + "\n" +"So nguyen am cua chuoi: " +NguyenAm(st) + "\n";
			dos.writeUTF(xl);	
			dos.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
