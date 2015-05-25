import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;

public class clientForm extends JFrame
	implements ActionListener {

	private static final long serialVersionUID = 4164713201071854899L;
	private static final String TITLE = "JChat 0.8";
	private static final int WIDTH = 500;
	private static final int HEIGHT = 675;
	
	private static String user="";
	
	private JTextField messageTextField = new JTextField();
	private JButton sendButton = new JButton();
	private JTextArea chatTextArea = new JTextArea();
	
	private client localClient;
	
	public clientForm(client c) {
		
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
		
		localClient = c;
		
		chatContainer.setBounds(20,36,440,520);
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
			
	public static String getUsername() {
		
		user = JOptionPane.showInputDialog("Please enter login name:");
		return user;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==sendButton) {
			// send message to server here when the time comes
			localClient.sendMessage(getMessage(messageTextField.getText()));
		}
	}
	
	private String getMessage(String in) {
		
		Calendar cal = new GregorianCalendar(2015,0,31);
		cal = Calendar.getInstance();
		
		String user = localClient.username;
		
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		
		String out = ("[" + day + "-" + month + "-" + year + " " + hour + ":" + min + ":" + sec + "] " + user + ":   " + in);
		return out;
	}

	public static String getHost() {
		String host = JOptionPane.showInputDialog("Please enter server IP:");
		return host;
	}
}