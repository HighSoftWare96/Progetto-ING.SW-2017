package it.RGB.is.GUI;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class About extends JDialog {

	private static final long serialVersionUID = 1L;

	public About(JFrame frame) {
		super(frame, true);

		// ESC key
		setFocusable(true);
		addKeyListener(new KeyboardListener(this));

		getContentPane().setLayout(new GridLayout(1, 1));

		JLabel aboutLbl = new JLabel();
		aboutLbl.setText(
				"<html><center><image src=\"" + getClass().getResource("/resources/main_small_new.png").toString()
						+ "\"><br><br><b>Music Store</b><br>Version: 1.0<br>Elaborato presentato per il corso di <br>Ingegneria del Software <i>(A.A. 2016-2017)</i><br><br>Progettato e sviluppato da:<br> <b>Bertoncelli Giovanni, Girelli Alberto, Righi Edoardo.</b><br><br>Quest'opera è stata rilasciata con licenza Creative Commons Attribuzione - Non commerciale - Condividi allo stesso modo 3.0 Italia.<br><br><img src=\""
						+ getClass().getResource("/resources/license.png").toString() + "\">");
		aboutLbl.setBorder(new EmptyBorder(10, 10, 10, 10));

		getContentPane().add(aboutLbl);

		setResizable(false);
		setIconImage(new ImageIcon(MainFrame.class.getResource("/resources/about_icon_new.png")).getImage());
		setTitle("Informazioni su");

		setSize(320, 400);
		setLocationRelativeTo(null);
		setVisible(true);

	}
}
