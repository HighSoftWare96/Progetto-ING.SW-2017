package it.RGB.is.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import it.RGB.is.Classes.BancaUtenti;

public class RegisterFrame extends JDialog {

	private static final long serialVersionUID = 1L;

	private JDialog parent;

	JTextField cFText;
	JTextField userText;
	JPasswordField passwordText;
	JPasswordField passwordText2;
	JTextField nameText;
	JTextField surnameText;
	JTextField cityText;
	JTextField telText;
	JTextField cellText;

	public RegisterFrame(JDialog parent) {
		super(parent, true);

		// ESC key
		setFocusable(true);
		addKeyListener(new KeyboardListener(this));

		this.parent = parent;

		setTitle("Registrazione");

		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel datiPanel = new JPanel(new GridLayout(10, 2));

		// nome utente
		JLabel userLabel = new JLabel("Nome utente: ");
		userText = new JTextField();
		// codice fiscale
		JLabel cFLabel = new JLabel("Codice fiscale: ");
		cFText = new JTextField();
		// password
		JLabel passwordLabel = new JLabel("Password: ");
		passwordText = new JPasswordField();
		// conferma password
		JLabel passwordLabel2 = new JLabel("Ripeti la password: ");
		passwordText2 = new JPasswordField();
		// nome
		JLabel nameLabel = new JLabel("Nome: ");
		nameText = new JTextField();
		// cognome
		JLabel surnameLabel = new JLabel("Cognome: ");
		surnameText = new JTextField();
		// città
		JLabel cityLabel = new JLabel("Città di residenza: ");
		cityText = new JTextField();
		// numero tel
		JLabel telLabel = new JLabel("Numero di telefono: ");
		telText = new JTextField();
		// numero cell
		JLabel cellLabel = new JLabel("Numero di cellulare: ");
		cellText = new JTextField();
		cellText.setFont(cellText.getFont().deriveFont(Font.ITALIC));
		cellText.setForeground(Color.LIGHT_GRAY);

		cellText.setText("Opzionale");
		cellText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cellText.getText().equalsIgnoreCase("Opzionale")) {
					cellText.setText("");
					cellText.setForeground(Color.BLACK);
					cellText.setFont(cellText.getFont().deriveFont(Font.PLAIN));
				}
			}
		});

		datiPanel.add(userLabel);
		datiPanel.add(userText);
		datiPanel.add(cFLabel);
		datiPanel.add(cFText);
		datiPanel.add(passwordLabel);
		datiPanel.add(passwordText);
		datiPanel.add(passwordLabel2);
		datiPanel.add(passwordText2);
		datiPanel.add(nameLabel);
		datiPanel.add(nameText);
		datiPanel.add(surnameLabel);
		datiPanel.add(surnameText);
		datiPanel.add(cityLabel);
		datiPanel.add(cityText);
		datiPanel.add(telLabel);
		datiPanel.add(telText);
		datiPanel.add(cellLabel);
		datiPanel.add(cellText);

		JPanel btnsPanel = new JPanel(new GridLayout(1, 2));

		JButton regBtn = new JButton("Conferma");
		regBtn.setActionCommand(RegisterFrameListener.REGISTER_CONFIRM);
		regBtn.addActionListener(new RegisterFrameListener(this));

		JButton backBtn = new JButton("Annulla");
		backBtn.setActionCommand(RegisterFrameListener.DISPOSE_WINDOW);
		backBtn.addActionListener(new RegisterFrameListener(this));

		btnsPanel.add(regBtn);
		btnsPanel.add(backBtn);

		mainPanel.add(datiPanel, BorderLayout.NORTH);
		mainPanel.add(btnsPanel, BorderLayout.SOUTH);

		getContentPane().add(mainPanel);

		getRootPane().setDefaultButton(regBtn);
		setIconImage(new ImageIcon(this.getClass().getResource("/resources/key.png")).getImage());

		setSize(new Dimension(320, 320));
		setResizable(true);
		mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public boolean checkCorrectData() {
		String psw1 = new String(passwordText.getPassword());
		String psw2 = new String(passwordText2.getPassword());

		if (cFText.getText().equals("") || passwordText.getPassword().equals("")
				|| passwordText2.getPassword().equals("") || nameText.getText().equals("")
				|| surnameText.getText().equals("") || cityText.getText().equals("") || telText.getText().equals("")
				|| userText.getText().equals("")) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Riempire tutti i campi obbligatori", "Errore registrazione",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (userText.getText().contains(" ")) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Username non corretto", "Errore registrazione",
					JOptionPane.ERROR_MESSAGE);
			this.userText.setText("");
			return false;
		} else if (!cFText.getText().equals(cFText.getText().toUpperCase())
				|| !cFText.getText().matches("^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$")) {
			Toolkit.getDefaultToolkit().beep();
			// tutti i caratteri del CF devono essere maiuscoli
			JOptionPane.showMessageDialog(this, "Codice fiscale non corretto", "Errore registrazione",
					JOptionPane.ERROR_MESSAGE);
			this.cFText.setText("");
			return false;
		} else if (!psw1.equals(psw2)) { // password scorrette
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Le due password inserite non corrispondono, riprova",
					"Errore registrazione", JOptionPane.ERROR_MESSAGE);
			this.passwordText.setText("");
			this.passwordText2.setText("");
			return false;
		} else if (!this.telText.getText().matches("[0-9]+")) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Numero di telefono scorretto", "Errore registrazione",
					JOptionPane.ERROR_MESSAGE);
			this.telText.setText("");
			return false;
		} else if (!this.cellText.getText().matches("[0-9]+") && !this.cellText.getText().equals("")
				&& !this.cellText.getText().equalsIgnoreCase("Opzionale")) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Numero di cellulare scorretto", "Errore registrazione",
					JOptionPane.ERROR_MESSAGE);
			this.cellText.setText("");
			return false;
		} else if (passwordText.getPassword().length < 5) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Password poco sicura. Inserire almeno 6 caratteri!",
					"Errore registrazione", JOptionPane.ERROR_MESSAGE);
			this.passwordText.setText("");
			this.passwordText2.setText("");
			return false;
		} else if (BancaUtenti.userNameExists(userText.getText())) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Username già utilizzato", "Errore registrazione",
					JOptionPane.ERROR_MESSAGE);
			this.userText.setText("");
			return false;
		} else {
			return true;
		}
	}

	public void disposeWithSuccess() {
		this.dispose();
		// chiudo finestra login
		parent.dispose();
	}

}
