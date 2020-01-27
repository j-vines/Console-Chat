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
			System.out.print("You are chatting with ");
			if(otherUsername.equals("stranger")) { //user is anonymous
				System.out.println("an anonymous user");
			} else {								//user is not anonymous
				System.out.println(otherUsername);
			}
			System.out.println("Say hello!");
		}
		catch(IOException i) {
			System.out.println(i);
		}
		
		String incomingMessage = "";
		
		while(!incomingMessage.equals("EXIT")) { //until user receives EXIT, read for incoming message and print it
			try {
				incomingMessage = input.readUTF();
				System.out.println(otherUsername + "> " + incomingMessage);
			}
			catch(IOException i) {
				System.out.println(i);
			}
		}
		//other user has sent "EXIT"
		System.out.println(otherUsername + " has disconnected");
		System.exit(0);
	}
	
}