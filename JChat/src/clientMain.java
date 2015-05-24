import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class clientMain {

	private static String user="";
	
	public static void main(String[] args) 
		throws InterruptedException, UnknownHostException, IOException {
		
		System.out.println("Starting chat client...");
		
		String HOST;
		int PORT=6677;
		
		HOST = client.getHost();
		/*try {
			Socket s = new Socket(HOST,PORT);
			
			
			
			user = client.getUsername();
		}
		catch (Exception noServer) {
			client.out.println("The server might not be up at this time.");
			System.out.println("Please try again later.");
		}*/
		
		try 
		{
			
			Socket s = new Socket(HOST, PORT);//CONNECT TO THE SERVER
			
			user = client.getUsername();
			
			System.out.println(user+" connected to " + HOST);//IF CONNECTED THEN PRINT IT OUT
			
			client clientBackend = new client(s);//START NEW CLIENT OBJECT
			
			Thread t = new Thread(clientBackend);//INITIATE NEW THREAD
			t.start();//START THREAD
			
		} 
		catch (Exception noServer)//IF DIDNT CONNECT PRINT THAT THEY DIDNT
		{
			System.out.println("The server might not be up at this time.");
			System.out.println("Please try again later.");
		}	
	}
}