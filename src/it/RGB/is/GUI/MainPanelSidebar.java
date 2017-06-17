package it.RGB.is.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainPanelSidebar extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JButton logInBtn = new JButton();
	private static JButton cartBtn = new JButton();
	private static JButton infoBtn = new JButton();
	private static JButton buyBtn = new JButton();
	private static int selectedID;

	public MainPanelSidebar() {
		super(new BorderLayout());

		buyBtn.setActionCommand(ActionsCommands.ADD_ITEM_TO_CART);
		buyBtn.addActionListener(new MainFrameListeners());

		cartBtn.setIcon(new ImageIcon(MainPanelSidebar.class.getResource("/resources/cart_new2.png")));
		cartBtn.setToolTipText("Visualizza carrello");
		cartBtn.setActionCommand(ActionsCommands.CART_VIEW_COMMAND);
		cartBtn.addActionListener(new MainFrameListeners());

		logInBtn.setIcon(new ImageIcon(MainPanelSidebar.class.getResource("/resources/login.png")));
		logInBtn.setToolTipText("Accedi");
		logInBtn.setActionCommand(ActionsCommands.LOG_IN_COMMAND);
		logInBtn.addActionListener(new MainFrameListeners());

		buyBtn.setIcon(new ImageIcon(MainPanelSidebar.class.getResource("/resources/buy_new.png")));
		buyBtn.setEnabled(false);
		buyBtn.setToolTipText("Aggiungi al carrello");

		infoBtn.setIcon(new ImageIcon(MainPanelSidebar.class.getResource("/resources/info_new.png")));
		infoBtn.setEnabled(false);
		infoBtn.setToolTipText("Informazioni sul prodotto");
		infoBtn.setActionCommand(ActionsCommands.INFO_PRODUCT_COMMAND);
		infoBtn.addActionListener(new MainFrameListeners());

		cartBtn.setBackground(Color.WHITE);
		logInBtn.setBackground(Color.WHITE);

		cartBtn.setBorder(new EmptyBorder(5, 5, 5, 5));
		logInBtn.setBorder(new EmptyBorder(5, 5, 5, 5));
		infoBtn.setBorder(new EmptyBorder(5, 5, 5, 5));
		JPanel sidebarNord = new JPanel(new GridLayout(7, 1));

		sidebarNord.add(logInBtn);
		sidebarNord.add(cartBtn);

		JPanel sidebarSud = new JPanel(new GridLayout(2, 1));

		sidebarSud.add(buyBtn);
		sidebarSud.add(infoBtn);

		add(sidebarSud, BorderLayout.SOUTH);
		add(sidebarNord, BorderLayout.NORTH);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(60, 0));

	}

	// metodo per attivare il bottone sulle info
	public static void enableSelectionBtn() {
		infoBtn.setEnabled(true);
		buyBtn.setEnabled(true);
	}

	public static void disableSelectionBtn() {
		infoBtn.setEnabled(false);
		buyBtn.setEnabled(false);
	}

	public static void setSelectedID(int ID) {
		selectedID = ID;
	}

	public static int getSelectedID() {
		return selectedID;
	}

	public static void setLoggedIn() {
		logInBtn.setActionCommand(ActionsCommands.LOG_OUT_COMMAND);
		logInBtn.setIcon(new ImageIcon(MainPanelSidebar.class.getResource("/resources/logout.png")));
	}

	public static void setLoggedOut() {
		logInBtn.setActionCommand(ActionsCommands.LOG_IN_COMMAND);
		logInBtn.setIcon(new ImageIcon(MainPanelSidebar.class.getResource("/resources/login.png")));
	}

	public static void setCartIconFull() {
		cartBtn.setIcon(new ImageIcon(MainPanelSidebar.class.getResource("/resources/cart_new2_full2.png")));
	}

	public static void setCartIconEmpty() {
		cartBtn.setIcon(new ImageIcon(MainPanelSidebar.class.getResource("/resources/cart_new2.png")));
	}

}
