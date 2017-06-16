package it.RGB.is.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

import it.RGB.is.Exceptions.IllegalUserRegistration;

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
		cellText = new cellTextField();

		cellText.setFont(cellText.getFont().deriveFont(Font.ITALIC));
		cellText.setForeground(Color.LIGHT_GRAY);
		cellText.setText("Opzionale");

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

	public void disposeWithSuccess() {
		this.dispose();
		// chiudo finestra login
		parent.dispose();
	}

	public void checkPswsMatch() throws IllegalUserRegistration {
		if (!new String(passwordText.getPassword()).equals(new String(passwordText2.getPassword()))) {
			throw new IllegalUserRegistration(IllegalUserRegistration.MSG_PSWS_NMATCH);
		}
	}

	public void setErrorLayout(String message) {
		switch (message) {
		case IllegalUserRegistration.MSG_CF_SINTAX:
			this.cFText.setText("");
			break;
		case IllegalUserRegistration.MSG_EMPTY_FIELDS:
			break;
		case IllegalUserRegistration.MSG_PSW_LOW:
		case IllegalUserRegistration.MSG_PSWS_NMATCH:
			this.passwordText.setText("");
			this.passwordText2.setText("");
			break;
		case IllegalUserRegistration.MSG_TEL_ERROR:
			this.telText.setText("");
			break;
		case IllegalUserRegistration.MSG_CELL_ERROR:
			this.cellText.setText("");
			break;
		case IllegalUserRegistration.MSG_USRNM_SPACES:
		case IllegalUserRegistration.MSG_USRNM_USED:
			this.userText.setText("");
			break;

		}
	}

	private class cellTextField extends JTextField {
		private static final long serialVersionUID = 1L;

		public cellTextField() {
			super();

			// listener per rimuovere la scritta al focus sul campo
			super.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					if (cellText.getText() == null) {
						cellText.setText("");
						cellText.setForeground(Color.BLACK);
						cellText.setFont(cellText.getFont().deriveFont(Font.PLAIN));
					}
				}

				@Override
				public void focusLost(FocusEvent e) {
					focusGained(e);
				}

			});

		}

		@Override // override diretto del metodo
		public String getText() {
			// per impedire che ritorni "opzionale" se è vuoto o c'è ancora
			// opzionale ritorno null
			if (super.getText().equalsIgnoreCase("opzionale") || super.getText().equals(""))
				return null;
			else
				return super.getText();
		}

	}

}
