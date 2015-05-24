/*import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class clientMain {

	private static String user="";
	
	public static void main(String[] args) 
		throws InterruptedException, UnknownHostException, IOException {
		
		System.out.println("Starting chat client...");
		
		client clientBack = new client();
		
		clientBack.connect(clientBack.getHost(), 6677);
		
		Thread t = new Thread(clientBack);//INITIATE NEW THREAD
		t.start();//START THREAD
}*/

import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;


public class clientMain {

	private final static int PORT = 6677;//SET A CONSTANT VARIABLE PORT
	
	public static void main(String[] args) throws IOException
	{
		try 
		{
			String host = JOptionPane.showInputDialog("Please enter server IP:");
			Socket s = new Socket(host, PORT);//CONNECT TO THE SERVER
			
			System.out.println("You connected to " + host);//IF CONNECTED THEN PRINT IT OUT
			
			client client = new client(s);//START NEW CLIENT OBJECT
			
			Thread t = new Thread(client);//INITIATE NEW THREAD
			t.start();//START THREAD
			
		} 
		catch (Exception noServer)//IF DIDNT CONNECT PRINT THAT THEY DIDNT
		{
			System.out.println("The server might not be up at this time.");
			System.out.println("Please try again later.");
		}
	}
}


