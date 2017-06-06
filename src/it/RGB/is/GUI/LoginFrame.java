package it.RGB.is.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField userText;
	private JPasswordField passwordText;

	public LoginFrame(JFrame frame) {

		super(frame, true);

		// ESC key
		setFocusable(true);
		addKeyListener(new KeyboardListener(this));

		setTitle("Accesso");

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		// -------------USER E PASSWORD
		JPanel userAndPassPanel = new JPanel(new GridLayout(2, 2));

		// nome utente
		JLabel userLabel = new JLabel("Utente: ");
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);

		userText = new JTextField();

		// password
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);

		passwordText = new JPasswordField();

		userAndPassPanel.add(userLabel);
		userAndPassPanel.add(userText);

		userAndPassPanel.add(passwordLabel);
		userAndPassPanel.add(passwordText);

		// -------------LOGIN E ANULLA

		JPanel logAndExitPanel = new JPanel(new GridLayout(1, 2));

		// pulsante login
		JButton loginButton = new JButton("Accedi");
		loginButton.setActionCommand(LoginFrameListener.LOGIN_COMMAND);
		loginButton.addActionListener(new LoginFrameListener(this));
		getRootPane().setDefaultButton(loginButton);

		// pulsante annulla

		JButton exitButton = new JButton("Annulla");
		exitButton.setActionCommand(LoginFrameListener.DISPOSE_WINDOW);
		exitButton.addActionListener(new LoginFrameListener(this));

		logAndExitPanel.add(loginButton);
		logAndExitPanel.add(exitButton);
		logAndExitPanel.setBorder(new EmptyBorder(25, 10, 15, 10));

		// -------------REGISTRATI

		JPanel registerPanel = new JPanel(new FlowLayout());

		JButton registerButton = new JButton("Registrati");
		registerButton.setForeground(Color.decode("#980000"));

		registerButton.setActionCommand(LoginFrameListener.REGISTER_COMMAND);
		registerButton.addActionListener(new LoginFrameListener(this));

		registerPanel.add(registerButton);

		mainPanel.add(userAndPassPanel, BorderLayout.NORTH);
		mainPanel.add(logAndExitPanel, BorderLayout.CENTER);
		mainPanel.add(registerPanel, BorderLayout.SOUTH);

		getContentPane().add(mainPanel);

		setIconImage(new ImageIcon(this.getClass().getResource("/resources/key.png")).getImage());
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		pack();

	}

	public void flushInputs() {
		passwordText.setText("");
		userText.setText("");
	}

	public JTextField getUserText() {
		return this.userText;
	}

	public JPasswordField getPasswordText() {
		return this.passwordText;
	}

}
