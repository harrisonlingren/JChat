import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client 
	implements Runnable {

	private Socket socket;//MAKE SOCKET INSTANCE VARIABLE
	PrintWriter out;
	String username;
	
	public client(Socket s)
	{
		socket = s;//INSTANTIATE THE INSTANCE VARIABLE
	}
	
	@Override
	public void run()//INHERIT THE RUN METHOD FROM THE Runnable INTERFACE
	{
		try
		{
			@SuppressWarnings("resource")
			Scanner in = new Scanner(socket.getInputStream());//GET THE CLIENTS INPUT STREAM (USED TO READ DATA SENT FROM THE SERVER)
			out = new PrintWriter(socket.getOutputStream());//GET THE CLIENTS OUTPUT STREAM (USED TO SEND DATA TO THE SERVER)
						
			while (true)//WHILE THE PROGRAM IS RUNNING
			{						
				if(in.hasNext())//IF THE SERVER SENT US SOMETHING
					System.out.println(in.nextLine());//PRINT IT OUT
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();//MOST LIKELY WONT BE AN ERROR, GOOD PRACTICE TO CATCH THOUGH
		} 
	}
	
	public void sendMessage(String msg) {
		
		try	{
			
			new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream());//GET THE CLIENTS OUTPUT STREAM (USED TO SEND DATA TO THE SERVER)
			
			out.println(msg);//SEND IT TO THE SERVER
			out.flush();//FLUSH THE STREAM
			
		}
		catch (Exception e)
		{
			e.printStackTrace();//MOST LIKELY WONT BE AN ERROR, GOOD PRACTICE TO CATCH THOUGH
		} 
	}	
}