package socket;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MultiThreadedServer {

	public static void main(String[] args) throws InterruptedException{
	    
        Console console = System.console();
		
		
		Scanner scnr = new Scanner(System.in);
		
		System.out.print("Please enter the port number of the server: ");
		
		int port = Integer.parseInt(console.readLine());
		System.out.println("");
		
		
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			 
            System.out.println("Server is listening on port " + port);
           
            
            while (true) {
                
            	Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                 
               
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
               
                Threads thread = new Threads(socket);
                thread.start();
                 
		  }
              

	} catch (IOException ex) {
	      System.out.println("Server exception: " + ex.getMessage());
	      ex.printStackTrace();
	  }
         
  } 
		
}

