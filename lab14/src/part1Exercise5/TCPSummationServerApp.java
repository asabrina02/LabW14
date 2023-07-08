package part1Exercise5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import controller.NumberCalculator;

/**
 * Lab Week 14 Part 1 Exercise 5
 * This class is to compute the summation 
 * and the multiplication of 3 numbers received from the client.
 * 
 * @author: Anis Sabrina
 * 
 */
public class TCPSummationServerApp {
	
	public static void main (String [] args)
	{
		System.out.println("Executing TCP Summation Server: ");
		
		try
		{
			// 1. Bind to a port number of 8087
			int portNo = 8087;
			ServerSocket serverSocket = new ServerSocket(portNo);
			
			// Create object for ThreeNumberCalculator class
			NumberCalculator cals = new NumberCalculator();
			
			// 2. Listen to request
			// Server needs to be alive forever in unterminated while loop
			while(true)
			{
				// 3. Accept client request for connection
				Socket clientRequest = serverSocket.accept();
				
				// 4. Process client request
				InputStream inStream = clientRequest.getInputStream();
				DataInputStream disInput = new DataInputStream(inStream);
				int number1 = disInput.readInt();
				int number2 = disInput.readInt();
				
				// display the numbers input received from the client
				System.out.println("\tFrom client: " + number1 + ", " 
						+ number2 );
				
				// Further processing
				// obtain sum of two numbers received from client input
				int total = cals.getSum(number1, number2);
				
				// obtain product of three numbers received from client input
				int product = cals.getMultiplication(number1, number2);
				
				
				// 5. Respond to client - return total to client
				OutputStream outStream = clientRequest.getOutputStream();
				DataOutputStream dosOutput = new DataOutputStream(outStream);
				
				// write the total and product to the stream to send back to client
				dosOutput.writeInt(total);
				dosOutput.writeInt(product);
				
				// display the data to be sent back to client
				System.out.println("\tTo client: ");
				System.out.println("\tTotal: "+ total + " and Product: " + product);
				
				// 6. Clear the stream
				dosOutput.flush();
				
				// 7. Close the stream
				dosOutput.close();
				
				System.out.println("\n\tWaiting for next request");
				
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
