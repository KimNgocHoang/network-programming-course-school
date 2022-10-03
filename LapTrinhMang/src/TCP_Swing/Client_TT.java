package TCP_Swing;

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
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class Client_TT extends JFrame {

	private JPanel contentPane;
	private JTextField txfPort;
	private JTextField txfTT;
	private JTextArea textArea;
	
	public Socket connect(int port) throws UnknownHostException, IOException
	{
		Socket socket = new Socket("localhost", port);
		return socket;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_TT frame = new Client_TT();
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
	public Client_TT() {
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
				try {
					int port = Integer.parseInt(txfPort.getText());
					connect(port);
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
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
					Socket socket = connect(port);
					DataInputStream dis = new DataInputStream(socket.getInputStream());
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					dos.writeUTF(msg);
					String st = dis.readUTF();
					textArea.setText(st);
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
