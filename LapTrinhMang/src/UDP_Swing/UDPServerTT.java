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
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.awt.event.ActionEvent;
import java.util.*;

public class UDPServerTT extends JFrame {

	private JPanel contentPane;
	private JTextField txfPort;

	 
	
	public void Server(int port) throws IOException
	{
		DatagramSocket socket = new DatagramSocket(port);
		System.out.println("Server is started");
		while(true)
		{
			new UDPThreadTT(socket).start();              
			
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UDPServerTT frame = new UDPServerTT();
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
	public UDPServerTT() {
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 431, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNhapSHiu = new JLabel("Nhap s\u1ED1 hi\u1EC7u c\u1ED5ng:  ");
		lblNhapSHiu.setBounds(93, 29, 147, 13);
		contentPane.add(lblNhapSHiu);
		
		txfPort = new JTextField();
		txfPort.setColumns(10);
		txfPort.setBounds(221, 26, 85, 19);
		contentPane.add(txfPort);
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int port = Integer.parseInt(txfPort.getText());
					Server(port);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRun.setBounds(115, 63, 68, 29);
		contentPane.add(btnRun);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 98, 397, 218);
		contentPane.add(textArea);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClose.setBounds(211, 63, 68, 29);
		contentPane.add(btnClose);
	}

}
