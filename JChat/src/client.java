import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

public class client extends JFrame
	implements ActionListener {

	private static final long serialVersionUID = 4164713201071854899L;
	private static final String TITLE = "JChat 0.1";
	private static final int WIDTH = 500;
	private static final int HEIGHT = 680;
	
	private JTextField messageTextField = new JTextField();
	private JButton sendButton = new JButton();
	private JTextArea chatTextArea = new JTextArea();
		
	public client() {
		
		setTitle(TITLE);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// set background
		setLayout(null);
		
		add(messageTextField);
		add(sendButton);
		add(chatTextArea);
		
		chatTextArea.setBounds(20,36,440,520);
		sendButton.setBounds(370,572,90,50);
		messageTextField.setBounds(20,572,340,50);
		
		sendButton.setHorizontalTextPosition(SwingConstants.CENTER);
		sendButton.setText("Send");
		sendButton.addActionListener(this);
		PrintStream printStream = new PrintStream(new CustomOutputStream(chatTextArea));
		System.setOut(printStream);
		
		super.getRootPane().setDefaultButton(sendButton);
		
		setVisible(true);	
	}
	
	public static void main(String[] args) 
		throws InterruptedException {
		
		System.out.println("Starting chat client...");
		
		// init main frame
		client mainFrame = new client();		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==sendButton) {
			String input = messageTextField.getText();
	
			// send message to server here when the time comes
			
			System.out.println(getMessage(input));
		}
	}
	
	String getMessage(String in) {
		
		Calendar cal = new GregorianCalendar(2015,0,31);
		
		cal = Calendar.getInstance();
		Date date =  cal.getTime();
		
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		
		
		int i = (int) new Date().getTime();
		in = ("[" + day + "-" + month + "-" + year + " " + hour + ":" + min + ":" + sec + "] " /*+ otherUsername + */ +": " + in);
		
		return in;
	}

}