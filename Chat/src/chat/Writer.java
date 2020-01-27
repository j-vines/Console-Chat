package chat;

/**
 * Client-Server Chat
 * Send messages over client server socket connection
 * @author Jack Vines
 */
import java.io.*;
import java.net.*;
public class Writer extends Thread {
	
	private DataOutputStream output = null;
	private BufferedReader userInput = null;
	
	public Writer(Socket socket) {
		try {
			userInput = new BufferedReader(new InputStreamReader(System.in));
			output = new DataOutputStream(socket.getOutputStream());
		}
		catch(IOException i) {
			System.out.println(i);
		}
	}
	
	public void run() {
		//first send user name
		try {
			output.writeUTF(Driver.username);
		}
		catch(IOException i) {
			System.out.println(i);
		}
		
		String outgoingMessage = "";
		
		while(!outgoingMessage.equals("EXIT")) { //until user types EXIT, read for outgoing message and send it to other user
			try {
				outgoingMessage = userInput.readLine();
				//System.out.println("You> " + outgoingMessage);
				output.writeUTF(outgoingMessage);
			}
			catch(IOException i) {
				System.out.println(i);
			}
		}
		//you've sent "EXIT"
		System.out.println("You've disconnected");
		System.exit(0);
	}
}