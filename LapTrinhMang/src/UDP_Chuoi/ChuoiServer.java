package UDP_Chuoi;


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

	public static void main(String[] args) throws Exception{
		ChuoiServer s = new ChuoiServer();
		DatagramSocket serverSocket = new DatagramSocket(8888);
		System.out.println("Server is started");
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		
		while(true)
		{
			//Tao goi rong de nhan du lieu tu client
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			//Nhan du lieu tu client
			serverSocket.receive(receivePacket);
			//Lay dia chi IP cua may client
			InetAddress IPAddress =receivePacket.getAddress();
			//Lay so hieu cong cua chuong trinh client
			int port = receivePacket.getPort();
			
			//chuyen du lieu vua nhan ve dang String			
			String str= new String(receivePacket.getData());
			String ser = "Chuoi Hoa: "+ s.UpperCase(str).trim() + "\n" + "Chuoi thuong: "+ s.LowerCase(str).trim() + "\n"
					+ "Chuoi Vua hoa vua thuong: " + s.LowerAndUpperCase(str).trim() + "\n" + "So tu cua chuoi: "+ s.demtu(str) + "\n"
					+ "So cac nguyen am: "+ s.NguyenAm(str) + "\n";
			
			//tao goi tin de gui di client
			sendData = ser.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			//Gui du lieu lai cho Client
			serverSocket.send(sendPacket);
		}

	}

}
