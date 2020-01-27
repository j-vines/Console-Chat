package chat;

/**
 * Client-Server Chat
 * Send messages over client server socket connection
 * @author Jack Vines
 */
import java.io.*;
import java.net.*;
public class Reader extends Thread {
	
	private DataInputStream input = null;
	private String otherUsername = null;
	
	public Reader(Socket socket) {
		try {
			input = new DataInputStream(socket.getInputStream());
		}
		catch(IOException i) {
			System.out.println(i);
		}
	}
	
	public void run() {
		//first read user name
		try {
			otherUsername = input.readUTF();
			System.out.println("YOU ARE CHATTING WITH: " + otherUsername);
		}
		catch(IOException i) {
			System.out.println(i);
		}
		
		String incomingMessage = "";
		
		while(incomingMessage != "EXIT") { //until user receives EXIT, read for incoming message and print it
			try {
				incomingMessage = input.readUTF();
				System.out.println(otherUsername + "> " + incomingMessage);
			}
			catch(IOException i) {
				System.out.println(i);
			}
		}
	}
	
}
