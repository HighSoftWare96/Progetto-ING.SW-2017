package it.RGB.is.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;

public class KeyboardListener implements KeyListener {

	private JDialog frame;

	public KeyboardListener(JDialog frame) {
		this.frame = frame;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// se clicco ESC
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			// chiudo la finestra
			frame.dispose();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
