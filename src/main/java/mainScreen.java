import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class mainScreen {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;

	boolean state = false;
	private static JTextField log;

	public static void writeToLog(String message){
		if(log == null) return;
		
		log.setText(message);	
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		txtpnUsername.setBounds(10, 30, 105, 20);
		txtpnUsername.setText("Email / username");
		frame.getContentPane().add(txtpnUsername);

		textField = new JTextField();
		textField.setBounds(133, 30, 285, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setEditable(false);
		txtpnPassword.setFocusable(false);
		txtpnPassword.setBounds(52, 61, 63, 20);
		txtpnPassword.setText("password");
		frame.getContentPane().add(txtpnPassword);

		textField_1 = new JTextField();
		textField_1.setBounds(133, 59, 285, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		btnNewButton = new JButton("start");
		btnNewButton.setFocusable(false);
		btnNewButton.setBounds(133, 92, 149, 112);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!state){
					state = true;
					btnNewButton.setText("stop");

					String username = textField.getText();
					String pass = textField_1.getText();

					Main.start(username, pass);
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
		log.setEnabled(false);
		log.setBounds(133, 215, 149, 20);
		frame.getContentPane().add(log);
		log.setColumns(10);
	}
}
