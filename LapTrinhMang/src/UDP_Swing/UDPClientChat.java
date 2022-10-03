package UDP_Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.*;

public class UDPClientChat extends JFrame implements Runnable {

	private JPanel contentPane;
	private JTextField txfNhap;
	private JTextArea textArea;
	private DatagramSocket socket;
	private DatagramPacket sendPackage;
	private DatagramPacket receivePackage;
	private byte[] sendData;
	private byte[] receiveData;
	private InetAddress IPAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UDPClientChat frame = new UDPClientChat();
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
	public UDPClientChat() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txfNhap = new JTextField();
		txfNhap.setBounds(10, 348, 354, 30);
		contentPane.add(txfNhap);
		txfNhap.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					sendData = new byte[1024];
					sendData= (Integer.toString(socket.getLocalPort())+": "+txfNhap.getText().trim()).getBytes();
					txfNhap.setText("");
					sendPackage = new DatagramPacket(sendData,sendData.length, IPAddress, 9999);
							socket.send(sendPackage);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnSend.setBounds(397, 347, 102, 30);
		contentPane.add(btnSend);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 10, 489, 314);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 10, 489, 314);
		scrollPane.setViewportView(textArea);
		contentPane.add(scrollPane);
		
		try {
			socket = new DatagramSocket();
			IPAddress = InetAddress.getByName("localhost");
			sendData = new byte[1024];
			sendData = (Integer.toString(socket.getLocalPort())+ " da tham gia vao phong chat").getBytes();
			DatagramPacket sendPackage = new DatagramPacket(sendData, sendData.length,IPAddress,9999);
			socket.send(sendPackage);
			new Thread(this).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void run() {
		try {
			while (true) {
				receiveData = new byte[1024];
				receivePackage = new DatagramPacket(receiveData, receiveData.length);
				socket.receive(receivePackage);
				receiveData = receivePackage.getData();
				String message = new String(receiveData).trim();
				textArea.append(message + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

