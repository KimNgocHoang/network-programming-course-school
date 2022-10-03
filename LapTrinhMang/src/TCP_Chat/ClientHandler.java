package TCP_Chat;


// 1. Open a socket.
// 2. Open an input stream and output stream to the socket.
// 3. Read from and write to the stream according to the server's protocol.
// 4. Close the streams.
// 5. Close the socket.

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * When a client connects the server spawns a thread to handle the client.
 * This way the server can handle multiple clients at the same time.
 *
 * This keyword should be used in setters, passing the object as an argument,
 * and to call alternate constructors (a constructor with a different set of
 * arguments.
 */

// Runnable is implemented on a class whose instances will be executed by a thread.
public class ClientHandler implements Runnable {

    // Array list of all the threads handling clients so each message can be sent to the client the thread is handling.
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    // Id that will increment with each new client.

    // Socket for a connection, buffer reader and writer for receiving and sending data respectively.
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String clientUsername;
    private int port;

    // Creating the client handler from the socket the server passes.
    @SuppressWarnings("deprecation")
	public ClientHandler(Socket socket) {
        try {
        	this.socket=socket;
			this.dos = new DataOutputStream(socket.getOutputStream());
			this.dis = new DataInputStream(socket.getInputStream());
            // When a client connects their username is sent.
            //this.port = dis.read();
			this.port =this.socket.getPort();
            // Add the new client handler to the array so they can receive messages from others.
            clientHandlers.add(this);
            broadcastMessage("SERVER: " + port + " has entered the chat!");
            //System.out.println(socket);
        } catch (IOException e) {
            // Close everything more gracefully.
            closeEverything(socket, dis, dos);
        }
    }

    // Everything in this method is run on a separate thread. We want to listen for messages
    // on a separate thread because listening (bufferedReader.readLine()) is a blocking operation.
    // A blocking operation means the caller waits for the callee to finish its operation.
    @SuppressWarnings("deprecation")
	@Override
    public void run() {
        String messageFromClient;
        // Continue to listen for messages while a connection with the client is still established.
        while (socket.isConnected()) {
            try {
                // Read what the client sent and then send it to every other client.
                messageFromClient = dis.readUTF();
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                // Close everything gracefully.
                closeEverything(socket, dis, dos);
                break;
            }
        }
    }

    // Send a message through each client handler thread so that everyone gets the message.
    // Basically each client handler is a connection to a client. So for any message that
    // is received, loop through each connection and send it down it.
    public void broadcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                // You don't want to broadcast the message to the user who sent it.
                if (!(clientHandler.port==(socket.getPort()))) {
                    clientHandler.dos.writeUTF(messageToSend);
                    //clientHandler.dos.newLine();
                    clientHandler.dos.flush();
                }
            	
            } catch (IOException e) {
                // Gracefully close everything.
                closeEverything(socket, dis, dos);
            }
        }
    }

    // If the client disconnects for any reason remove them from the list so a message isn't sent down a broken connection.
    public void removeClientHandler() {
        clientHandlers.remove(this);
        //broadcastMessage("SERVER: " + clientUsername + " has left the chat!");
        broadcastMessage("SERVER: " + socket.getPort() + " has left the chat!");
    }

    // Helper method to close everything so you don't have to repeat yourself.
    public void closeEverything(Socket socket, DataInputStream dis, DataOutputStream dos) {
        
        removeClientHandler();
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
}
