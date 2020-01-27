package chat;

import java.io.*;
public class Driver {
	
	//name options
	public static final int NAME = 1;
	public static final int ANON = 2;
	//main options
	public static final int HOST = 1;
	public static final int JOIN = 2;
	public static final int EXIT = 3;

	public static String username = null;
	private static BufferedReader input = null;
	
	/**
	 * First screen user sees, greets user, asks if they want to host or join chat server
	 */
	public static void startScreen() { //First screen user sees
		int answer = 0;
		input = new BufferedReader(new InputStreamReader(System.in));
		
		//greeting screen
		System.out.println("Welcome, stranger! Would you like to...");
		//menu options
		System.out.println("1.) Enter your name");
		System.out.println("2.) Continue anonymously");
		
		answer = intParse(getInput());
	
		while(answer != NAME && answer != ANON) {
			System.out.println("Improper input");
			answer = intParse(getInput());
		}
		
		if(answer == NAME) {
			System.out.println("What would you like your name to be?");
			String name = getInput();
			mainMenu(name);
		} else {
			mainMenu("stranger");
		}
	}
	
	public static void mainMenu(String name) {
		username = name;
		int answer = 0;
		//greeting screen
		System.out.println("Welcome, " + username + "! Would you like to...");
		//menu options
		System.out.println("1.) Host chat server");
		System.out.println("2.) Join chat server");
		System.out.println("3.) Exit program");
				
		answer = intParse(getInput());
		
		while(answer != HOST && answer != JOIN && answer != EXIT) {
			System.out.println("Improper input");
			answer = intParse(getInput());
		}
		
		switch(answer) { 
        case HOST: 
            Server.startServer(); 
            break; 
        case JOIN: 
            Client.startClient();
            break; 
        case EXIT:
        	System.exit(0);
		}
	}
	
	/**
	 * Get input from the user 
	 * @return user input string
	 */
	public static String getInput() {
		try {
			return input.readLine();
		}
		catch(IOException i) {
			System.out.println(i);
			return null;
		}
	}
	
	/**
	 * Attempt to parse use input into int, on failure return 0
	 * @param str, user input string
	 * @return parsed int or 0 on failure
	 */
	public static int intParse(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException n) {
			return 0;
		}
	}
	
	public static void main(String[] args) {
		startScreen();
	}

}
