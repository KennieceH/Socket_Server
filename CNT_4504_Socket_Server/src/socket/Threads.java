package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;

public class Threads extends Thread {
	private Socket socket;

	public Threads(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			
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
         
			
		} catch (IOException | InterruptedException ex) {
		      System.out.println("Server exception: " + ex.getMessage());
		      ex.printStackTrace();
		  }
		
	}

}
