package TCP_Chat;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class Client {

//    // A client has a socket to connect to the server and a reader and writer to receive and send messages respectively.
	private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String username;
    private int port;

//    public Client(Socket socket, String username) {
    public Client(Socket socket, int port) {
        try {
            this.socket = socket;
            //this.username = username;
            this.dos = new DataOutputStream(socket.getOutputStream());
			this.dis = new DataInputStream(socket.getInputStream());
			this.port = this.socket.getPort();
        } catch (IOException e) {
            // Gracefully close everything.
            closeEverything(socket, dis, dos);
        }
    }

//
//    // Sending a message isn't blocking and can be done without spawning a thread, unlike waiting for a message.
    public void sendMessage() {
        try {
            // Initially send the username of the client.
        	dos.write(socket.getPort());
        	dos.flush();
            // Create a scanner for user input.
            Scanner scanner = new Scanner(System.in);
            // While there is still a connection with the server, continue to scan the terminal and then send the message.
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                dos.writeUTF(socket.getPort() + ": " + messageToSend);
                //bufferedWriter.newLine();
                dos.flush();
            }
        } catch (IOException e) {
            // Gracefully close everything.
            closeEverything(socket, dis, dos);
        }
    }
//    

//
//    // Listening for a message is blocking so need a separate thread for that.
    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;
                // While there is still a connection with the server, continue to listen for messages on a separate thread.
                while (socket.isConnected()) {
                    try {
                        // Get the messages sent from other users and print it to the console.
                        msgFromGroupChat = dis.readUTF();
                        System.out.println(msgFromGroupChat);
                    } catch (IOException e) {
                        // Close everything gracefully.
                        closeEverything(socket, dis, dos);
                    }
                }
            }
        }).start();
    }
//
//    // Helper method to close everything so you don't have to repeat yourself.
    public void closeEverything(Socket socket, DataInputStream dis, DataOutputStream dos) {
        try {
            if (dis != null) {
            	dis.close();
            }
            if (dos != null) {
            	dos.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Run the program.
    public static void main(String[] args) throws IOException {

        // Get a username for the user and a socket connection.
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter your username for the group chat: ");
//        String username = scanner.nextLine();
        // Create a socket to connect to the server.
        Socket socket = new Socket("localhost", 1234);
        
        // Pass the socket and give the client a username.
        //Client client = new Client(socket, username);
        Client client = new Client(socket, socket.getPort());
        // Infinite loop to read and send messages.
        client.listenForMessage();
        client.sendMessage();
    }
}