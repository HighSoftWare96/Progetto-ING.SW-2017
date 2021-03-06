package it.RGB.is.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.EmptyBorder;

public class MainFrameMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;
	static JMenu sellMenu = new JMenu("Acquisti");
	static JMenu userMenu = new JMenu("Account");
	static JMenu helpMenu = new JMenu("Aiuto");
	static JLabel logInUser = new JLabel();
	
	static JMenuItem logOpt = new JMenuItem("Log in...", new ImageIcon(MainFrameMenu.class.getResource("/resources/login_small.png"))); 
	static JMenuItem cartView = new JMenuItem("Visualizza carrello", new ImageIcon(MainFrameMenu.class.getResource("/resources/view_cart_small.png")));
	static JMenuItem cartDelete = new JMenuItem("Svuota carrello", new ImageIcon(MainFrameMenu.class.getResource("/resources/cart_small.png")));
	static JMenuItem endSell = new JMenuItem("Completa acquisto", new ImageIcon(MainFrameMenu.class.getResource("/resources/finish_buy_small.png")));
	static JMenuItem suggestedOpt = new JMenuItem("Visualizza suggerimenti", new ImageIcon(MainFrameMenu.class.getResource("/resources/suggest_small.png")));
	static JMenuItem discountsOpt = new JMenuItem("I miei sconti", new ImageIcon(MainFrameMenu.class.getResource("/resources/discount_small.png")));
	static JMenuItem usrDetails = new JMenuItem("Dettagli utente", new ImageIcon(MainFrameMenu.class.getResource("/resources/user_small.png")));
	static JMenuItem aboutOpt = new JMenuItem("Informazioni", new ImageIcon(MainFrameMenu.class.getResource("/resources/about_small.png")));

	public MainFrameMenu() {

		super();

		logInUser.setForeground(Color.decode("#099000"));
		logInUser.setFont(logInUser.getFont().deriveFont(Font.ITALIC));
		logInUser.setVisible(false);
		logInUser.setFocusable(false);
		logInUser.setBorder(new EmptyBorder(0, 10, 0, 0));

		sellMenu.setMnemonic(KeyEvent.VK_S);
		userMenu.setMnemonic(KeyEvent.VK_U);
		helpMenu.setMnemonic(KeyEvent.VK_H);

		suggestedOpt.setEnabled(false);
		discountsOpt.setEnabled(false);
		usrDetails.setEnabled(false);
		cartDelete.setEnabled(false);
		endSell.setEnabled(false);

		logOpt.setActionCommand(ActionsCommands.LOG_IN_COMMAND);
		logOpt.addActionListener(new MainFrameListeners());

		aboutOpt.setActionCommand(ActionsCommands.ABOUT_COMMAND);
		aboutOpt.addActionListener(new MainFrameListeners());

		cartView.setActionCommand(ActionsCommands.CART_VIEW_COMMAND);
		cartView.addActionListener(new MainFrameListeners());

		usrDetails.setActionCommand(ActionsCommands.USER_DETAILS_COMMAND);
		usrDetails.addActionListener(new MainFrameListeners());

		cartDelete.setActionCommand(ActionsCommands.DELETE_CART_COMMAND);
		cartDelete.addActionListener(new MainFrameListeners());

		endSell.setActionCommand(ActionsCommands.BUY_COMMAND);
		endSell.addActionListener(new MainFrameListeners());

		suggestedOpt.setActionCommand(ActionsCommands.SUGGESTIONS_COMMAND);
		suggestedOpt.addActionListener(new MainFrameListeners());

		discountsOpt.setActionCommand(ActionsCommands.DISCOUNT_SHOW_COMMAND);
		discountsOpt.addActionListener(new MainFrameListeners());

		sellMenu.add(cartView);
		sellMenu.add(cartDelete);
		sellMenu.add(endSell);
		sellMenu.add(suggestedOpt);
		sellMenu.add(discountsOpt);

		userMenu.add(logOpt);
		userMenu.add(usrDetails);

		helpMenu.add(aboutOpt);

		add(userMenu);
		add(sellMenu);
		add(helpMenu);
		add(logInUser);

	}

	public static void enableEmptyBtn() {
		cartDelete.setEnabled(true);
		endSell.setEnabled(true);
		MainPanelSidebar.setCartIconFull();
	}

	public static void disableEmptyBtn() {
		cartDelete.setEnabled(false);
		endSell.setEnabled(false);
		MainPanelSidebar.setCartIconEmpty();

	}

}
