package it.RGB.is.Exceptions;

import javax.swing.JOptionPane;

public class LightCartException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public LightCartException() {
		super();
	}

	public LightCartException(String message) {
		super(message);
		JOptionPane.showMessageDialog(null, message, "Errore carrello", JOptionPane.ERROR_MESSAGE);
	}
}
