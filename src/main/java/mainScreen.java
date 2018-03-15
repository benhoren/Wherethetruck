import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class mainScreen {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	static private JButton btnNewButton;
	static String username = "";
	static String password = "";

	final static String file = Main.folder+"/usrpssrd.txt";

	static boolean state = false;
	static JTextField log;

	public static void writeToLog(String message){
		if(log == null) return;
		log.setText(message);	
		btnNewButton.setText("start");
		state = false;
	}
	public static void writeOk(){
		if(log == null) return;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String datetime = dtf.format(now); //2016/11/16 12:08:43
		log.setText("logged-in "+ datetime);
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		readFile();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainScreen window = new mainScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	private static void readFile() {
		File f = new File(file);
		if(!f.exists()){
			writeToFile();
			return;
		}

		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);

			String sr = br.readLine();
			String ps = br.readLine();

			username = sr;
			password = ps;

			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}


	private static void writeToFile(){
		File f = new File(file);
		FileWriter writer;

		try {
			writer = new FileWriter(f);
			writer.write(username+'\n');
			writer.write(password);

			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	/**
	 * Create the application.
	 */
	public mainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setEditable(false);
		txtpnUsername.setFocusable(false);
		txtpnUsername.setBounds(5, 30, 118, 20);
		txtpnUsername.setText("Email / Username");
		frame.getContentPane().add(txtpnUsername);

		textField = new JTextField();
		textField.setBounds(133, 30, 285, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(username);

		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setEditable(false);
		txtpnPassword.setFocusable(false);
		txtpnPassword.setBounds(53, 59, 63, 20);
		txtpnPassword.setText("password");
		frame.getContentPane().add(txtpnPassword);

		textField_1 = new JTextField();
		textField_1.setBounds(133, 59, 285, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(password);

		btnNewButton = new JButton("start");
		btnNewButton.setFocusable(false);
		btnNewButton.setBounds(133, 92, 149, 112);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!state){
					
					state = true;
					btnNewButton.setText("stop");
					log.setText("");

					
					username = textField.getText();
					password = textField_1.getText();

					writeToFile();

					Main.start(username, password);

				}

				else if(state){
					state = false;
					btnNewButton.setText("start");
					Main.stop();
				}
			}
		});
		frame.getContentPane().add(btnNewButton);

		log = new JTextField();
		log.setDisabledTextColor(Color.BLACK);
		log.setSelectionColor(Color.WHITE);
		log.setEnabled(false);
		log.setBounds(133, 215, 149, 20);
		frame.getContentPane().add(log);
		log.setColumns(10);
	}
}
