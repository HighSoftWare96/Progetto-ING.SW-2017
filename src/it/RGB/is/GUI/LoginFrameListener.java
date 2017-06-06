package it.RGB.is.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrameListener implements ActionListener {

	private LoginFrame loginFrame;

	static final String LOGIN_COMMAND = "LOGIN";
	static final String REGISTER_COMMAND = "Registra";
	static final String DISPOSE_WINDOW = "Annulla";

	public LoginFrameListener(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String generator = e.getActionCommand();
		switch (generator) {
		case DISPOSE_WINDOW:
			loginFrame.dispose();
			break;

		case REGISTER_COMMAND:
			new RegisterFrame(loginFrame);
			break;

		case LOGIN_COMMAND:
			Controller.login(this.loginFrame);
			break;

		default:
			break;
		}

	}
}
