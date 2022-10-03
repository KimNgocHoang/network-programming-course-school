package UDP_Swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class UDPClientTT extends JFrame {

	private JPanel contentPane;
	private JTextField txfPort;
	private JTextField txfTT;
	private JTextArea textArea;
	
	public DatagramSocket connect(int port) throws UnknownHostException, IOException
	{
		DatagramSocket socket = new DatagramSocket(port);
		return socket;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UDPClientTT frame = new UDPClientTT();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UDPClientTT() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNhapSoHieu = new JLabel("Nhap so hieu cong: ");
		lblNhapSoHieu.setBounds(39, 31, 137, 13);
		contentPane.add(lblNhapSoHieu);
		
		txfPort = new JTextField();
		txfPort.setColumns(10);
		txfPort.setBounds(160, 28, 85, 19);
		contentPane.add(txfPort);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int port = Integer.parseInt(txfPort.getText());
				//connect(port);
			}
		});
		btnConnect.setBounds(255, 27, 85, 21);
		contentPane.add(btnConnect);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 96, 421, 215);
		contentPane.add(textArea);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnClose.setBounds(346, 27, 85, 21);
		contentPane.add(btnClose);
		
		txfTT = new JTextField();
		txfTT.setColumns(10);
		txfTT.setBounds(66, 66, 226, 19);
		contentPane.add(txfTT);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int port = Integer.parseInt(txfPort.getText());
				String msg=txfTT.getText();
				try {
					DatagramSocket socket = new DatagramSocket();
					InetAddress IPAddress = InetAddress.getByName("localhost");
					byte[] sendData = new byte[1024];
					byte[] receiveData = new byte[1024];
					sendData = msg.getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					socket.send(sendPacket);
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					socket.receive(receivePacket);
					
					String str= new String(receivePacket.getData());
					textArea.setText(str);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSend.setBounds(302, 65, 69, 21);
		contentPane.add(btnSend);
	}
}
