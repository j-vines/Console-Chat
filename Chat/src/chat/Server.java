package chat;

/**
 * Client-Server Chat
 * Send messages over client server socket connection
 * @author Jack Vines
 */
import java.io.*;
import java.net.*;
public class Server {
	
	private ServerSocket server = null;
	private Socket socket = null;
	
	public Server(int port) {
		try {
			server = new ServerSocket(port);
			System.out.println("Server opened...\nWaiting for client...");
			socket = server.accept();
			System.out.println("New client connection!");
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
	 * Get port to connect to, create server on said port
	 */
	public static void startServer() {
		int port = 0;
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("What port would you like to host on?");
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

		//open server on provided port
		Server server = new Server(port);
	}
	
}
