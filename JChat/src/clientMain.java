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
			
			client localClient = new client(s);//START NEW CLIENT OBJECT
			
			Thread t = new Thread(localClient);//INITIATE NEW THREAD
			t.start();//START THREAD

			@SuppressWarnings("unused")
			clientForm mainForm = new clientForm(localClient);
			localClient.username = clientForm.getUsername();
			localClient.out.println(localClient.username);
		} 
		catch (Exception noServer)//IF DIDNT CONNECT PRINT THAT THEY DIDNT
		{
			System.out.println("The server might not be up at this time.");
			System.out.println("Please try again later.");
		}
	}
}