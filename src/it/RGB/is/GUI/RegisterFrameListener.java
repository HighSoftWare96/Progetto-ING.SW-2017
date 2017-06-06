package it.RGB.is.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrameListener implements ActionListener {

	private RegisterFrame registerFrame;

	static final String REGISTER_CONFIRM = "Conferma";
	static final String DISPOSE_WINDOW = "Annulla";

	public RegisterFrameListener(RegisterFrame frame) {
		this.registerFrame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String generator = e.getActionCommand();
		switch (generator) {
		case DISPOSE_WINDOW:
			registerFrame.dispose();
			break;

		case REGISTER_CONFIRM:
			Controller.registerConfirm(registerFrame);
			break;

		default:
			break;
		}

	}
}
