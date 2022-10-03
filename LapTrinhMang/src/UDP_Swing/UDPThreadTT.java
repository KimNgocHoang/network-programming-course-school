package UDP_Swing;

import java.io.*;
import java.util.*;
import java.net.*;

public class UDPThreadTT extends Thread {
	protected DatagramSocket socket=null;

	public UDPThreadTT(DatagramSocket socket)
	{
		this.socket=socket;
	}
	
	public static boolean hasPrecedence(char op1, char op2)
	 {
		 if (op2 == '(' || op2 == ')')
			 return false;
		 if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			 return false;
		 else
			 return true;
	 }
	 public static int applyOp(char op, int b, int a)
	 {
		 switch (op)
		 {
		 case '+': return a + b;
		 case '-': return a - b;
		 case '*': return a * b;
		 case '/': if (b == 0)throw new UnsupportedOperationException( "Cannot divide by zero");
			 return a / b;
		 }
		 return 0;
	 }
	
	 public static float TT(String expression)
	    {
	        char[] tokens = expression.toCharArray();
	        Stack<Integer> values = new Stack<Integer>();
	        
	        Stack<Character> ops = new Stack<Character>();
	 
	        for (int i = 0; i < tokens.length; i++)
	        {
//	            if (tokens[i] == ' ')
//	                continue;
	            if (tokens[i] >= '0' && tokens[i] <= '9')
	            {
	                StringBuffer sbuf = new StringBuffer();
	                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
	                    sbuf.append(tokens[i++]);
	                values.push(Integer.parseInt(sbuf.toString()));
	               
	                  i--;
	            }
	            else if (tokens[i] == '(')
	                ops.push(tokens[i]);
	            else if (tokens[i] == ')')
	            {
	                while (ops.peek() != '(') values.push(applyOp(ops.pop(), values.pop(), values.pop()));
	                ops.pop();
	            }
	            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/')
	            {
	                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
	                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
	                ops.push(tokens[i]);
	            }
	        }
	        while (!ops.empty()) values.push(applyOp(ops.pop(), values.pop(), values.pop()));
	 
	        return values.pop();
	    }
	
	public void run()
	{
		try
		{
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			socket.receive(receivePacket);
			InetAddress IPAddress= receivePacket.getAddress();
			int port = receivePacket.getPort();
			
			String str= new String(receivePacket.getData());
			System.out.println("From CLient: "+ str);
			String xl = "Ket qua = " + TT(str);
			sendData = xl.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			socket.send(sendPacket);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
