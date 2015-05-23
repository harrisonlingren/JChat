import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class clientMain {

	private static String user;
	
	public static void main(String[] args) 
			throws InterruptedException, UnknownHostException, IOException {
			
			System.out.println("Starting chat client...");
			
			String HOST;
			int PORT=6677;
			
			HOST = client.getHost();
			try {
				Socket s = new Socket(HOST,PORT);
				
				
				
				user = client.getUsername();
			}
			catch (Exception noServer) {
				client.out.println("The server might not be up at this time.");
				System.out.println("Please try again later.");
			}
						
			// init main frame
			client localClient = new client();		
		}
}