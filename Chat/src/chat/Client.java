package chat;

import java.io.*;
import java.net.*;
public class Client {
	
	private Socket socket = null;
	
	public Client(String address, int port) {
		try {
			socket = new Socket(address, port);
			System.out.println("Connection successful");
		}
		catch(UnknownHostException u) {
			System.out.println(u);
			System.exit(0);
		}
		catch(IOException i) {
			System.out.println(i);
		}
		
		//create reading and writing threads
		Writer writer = new Writer(socket);
		Reader reader = new Reader(socket);
		writer.start();
		reader.start();
	}
	
	/**
	 * Gets host and port to connect to from user, creates client
	 */
	public static void startClient() {
		int port = 0;
		String host = "";
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Host to connect to: ");
		try {
			host = userIn.readLine();
		}
		catch(IOException i) {
			System.out.println(i);
		}

		System.out.println("Port to connect to: ");
		try {
			port = Integer.parseInt(userIn.readLine());
		}
		catch(IOException i) {
			System.out.println(i);
		}
		catch(NumberFormatException n) { //user input is not an int
			System.out.println(n);
			System.exit(0);
		}

		Client client = new Client(host, port);
	}
}
