package socket;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class SingleThreadedServer {

	public static void main(String[] args) throws InterruptedException {
	    
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
               
                 
                String command = reader.readLine();
                
                String count = reader.readLine();

                int option = Integer.parseInt(reader.readLine());

                String date = "no";
                
                  if(option == 1){
                         System.out.println("date is choosen");
                         date = "yes";
		  }
                
                Process p = Runtime.getRuntime().exec(command);
               
                
                
                System.out.println(command);
                
                
                BufferedReader stdInput = new BufferedReader( new InputStreamReader(p.getInputStream()));
                
                BufferedReader stdError = new BufferedReader(new 
                        InputStreamReader(p.getErrorStream()));
                
                
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
 
                String text;
                
                
                while ((text = stdInput.readLine()) != null) {
                       
                       if(date == "yes"){
                	 text = text.replaceAll("\"", " ");
                         text = text.substring(1,text.length());
                         writer.println(text);
                       }
                      
                       else{
                         writer.println(text);
                       }
                }
                
                
                while ((text = stdError.readLine()) != null) {
                	System.out.println("The error is:  " + text);
                }
                
                
                System.out.println("Client Request:  " + count);
                
                
                p.waitFor();
                System.out.println ("exit: " + p.exitValue());
                p.destroy();
                
                System.out.println("");
                System.out.println("");
                socket.close();
                reader.close();
            }
            
           
           
            
			
		} catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
        
		
	}

}
