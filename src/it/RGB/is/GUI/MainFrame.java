
package it.RGB.is.GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import it.RGB.is.Classes.BancaUtenti;
import it.RGB.is.Classes.BancaVendite;
import it.RGB.is.Classes.Cart;
import it.RGB.is.Classes.Catalogo;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private String title;

	private static MainPanelSidebar sidebar;
	private static MainPanelProducts products;

	public MainFrame(String title) {
		super(title);
		this.title = title;

		// setting main theme for the UI
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}

		MainFrameMenu mainFrameMenu = new MainFrameMenu();

		Container mainFrameContainer = getContentPane();
		MainPanelButtons buttonSOUTH = new MainPanelButtons();
		products = new MainPanelProducts();
		sidebar = new MainPanelSidebar();

		mainFrameContainer.setLayout(new BorderLayout());
		mainFrameContainer.add(buttonSOUTH, BorderLayout.SOUTH);
		mainFrameContainer.add(products, BorderLayout.CENTER);
		mainFrameContainer.add(sidebar, BorderLayout.EAST);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new ExitListener());
		setJMenuBar(mainFrameMenu);
		setSize(800, 700);
		setMinimumSize(new Dimension(1000, 700));
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(MainFrame.class.getResource("/resources/main_new.png")).getImage());
		setVisible(true);
	}

	public void setLoggedIn() {
		this.setTitle(title + " [ " + BancaUtenti.getLoggedInUser().getUsername() + " ]");
	}

	public void setLoggedOut() {
		this.setTitle(title);
	}

	private class ExitListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {

			Toolkit.getDefaultToolkit().beep();

			int confirm = JOptionPane.showConfirmDialog(MainFrame.this, "Confermare la chiusura?", "Conferma chiusura",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (confirm == 0) {
				// svuoto il carrello
				Cart.removeAll();

				if (Cart.getCart().length != 0)
					CartFrame.disableEmptyBtn();

				// prima di uscire salvo tutti i dati su file
				Catalogo.saveCatalog();
				BancaUtenti.saveUtenti();
				BancaVendite.saveVendite();
				System.exit(0);
			}
		}
	}

	public static MainPanelProducts getPanelProducts() {
		return products;
	}

	public static MainPanelSidebar getSidebar() {
		return sidebar;
	}
}
