import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.*;

public class client extends JFrame
	implements ActionListener, Runnable {

	private static final long serialVersionUID = 4164713201071854899L;
	private static final String TITLE = "JChat 0.1";
	private static final int WIDTH = 500;
	private static final int HEIGHT = 680;
	
	private static String user="";
	
	private Socket socket;
	
	private JTextField messageTextField = new JTextField();
	private JButton sendButton = new JButton();
	private JTextArea chatTextArea = new JTextArea();
	
	private static Scanner in; 
	public static PrintWriter out; 
	
	public client(Socket s) {
		
		setTitle(TITLE);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// set background
		setLayout(null);
		
		chatTextArea.setEditable(false);
		JScrollPane chatContainer = new JScrollPane(chatTextArea);
		
		add(messageTextField);
		add(sendButton);
		add(chatContainer);
		
		chatContainer.setBounds(20,36,440,520);
		sendButton.setBounds(370,572,90,50);
		messageTextField.setBounds(20,572,340,50);
		
		sendButton.setHorizontalTextPosition(SwingConstants.CENTER);
		sendButton.setText("Send");
		sendButton.addActionListener(this);
		
		try {
			
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//PrintStream printStream = new PrintStream(new CustomOutputStream(chatTextArea));
		//System.setOut(printStream);
		
		super.getRootPane().setDefaultButton(sendButton);
		
		setVisible(true);
	}
			
	public static String getUsername() {
		
		String username = JOptionPane.showInputDialog("Please enter login name:");
		return username;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==sendButton) {
			// send message to server here when the time comes
			run();
		}
	}
	
	private String getMessage(String in) {
		
		Calendar cal = new GregorianCalendar(2015,0,31);
		
		cal = Calendar.getInstance();
				
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		
		in = ("[" + day + "-" + month + "-" + year + " " + hour + ":" + min + ":" + sec + "] " + user + ":   " + in);
		
		return in;
	}


	public static String getHost() {
		String host = JOptionPane.showInputDialog("Please enter server IP:");
		return host;
	}
	
	@Override
	public void run()//INHERIT THE RUN METHOD FROM THE Runnable INTERFACE
	{
		try
		{
			//Scanner chat = new Scanner(System.in);//GET THE INPUT FROM THE CMD
			Scanner in = new Scanner(socket.getInputStream());//GET THE CLIENTS INPUT STREAM (USED TO READ DATA SENT FROM THE SERVER)
			PrintWriter out = new PrintWriter(socket.getOutputStream());//GET THE CLIENTS OUTPUT STREAM (USED TO SEND DATA TO THE SERVER)
			
			while (true)//WHILE THE PROGRAM IS RUNNING
			{						
				String input = getMessage(messageTextField.getText());	//SET NEW VARIABLE input TO THE VALUE OF WHAT THE CLIENT TYPED IN
				out.println(input);//SEND IT TO THE SERVER
				out.flush();//FLUSH THE STREAM
				
				if(in.hasNext())//IF THE SERVER SENT US SOMETHING
					System.out.println(in.nextLine());//PRINT IT OUT
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();//MOST LIKELY WONT BE AN ERROR, GOOD PRACTICE TO CATCH THOUGH
		} 
	}
}

/**
 * This class extends from OutputStream to redirect output to a JTextArrea
 * @author www.codejava.net
 *
 */

class CustomOutputStream extends OutputStream {
    private JTextArea textArea;
     
    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}