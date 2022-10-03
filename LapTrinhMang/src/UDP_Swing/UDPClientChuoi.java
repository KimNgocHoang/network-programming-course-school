package UDP_Swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class UDPClientChuoi extends JFrame {

	private JPanel contentPane;
	private JTextField txfNhap;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UDPClientChuoi frame = new UDPClientChuoi();
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
	public UDPClientChuoi() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(30, 69, 380, 215);
		contentPane.add(textArea);
		
		JLabel lblNhapChuoi = new JLabel("Nhap chuoi: ");
		lblNhapChuoi.setBounds(30, 28, 83, 13);
		contentPane.add(lblNhapChuoi);
		
		txfNhap = new JTextField();
		txfNhap.setColumns(10);
		txfNhap.setBounds(123, 28, 174, 19);
		contentPane.add(txfNhap);
		
		JButton btnXL = new JButton("X\u1EED l\u00ED");
		btnXL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String msg=txfNhap.getText();
					DatagramSocket clientSocket = new DatagramSocket(); //gan cong
					InetAddress IPAddress = InetAddress.getByName("localhost");
					byte[] sendData = new byte[1024];
					byte[] receiveData = new byte[1024];
					sendData = msg.getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 4444);
					clientSocket.send(sendPacket);
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					clientSocket.receive(receivePacket);
					
					String str= new String(receivePacket.getData());
					textArea.setText(str);
					//clientSocket.close();
					
				}
				catch(Exception a)
				{
					
				}
			
			}
		});
		btnXL.setBounds(325, 24, 85, 21);
		contentPane.add(btnXL);
	}
}
