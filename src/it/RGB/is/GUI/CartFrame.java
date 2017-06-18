package it.RGB.is.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import it.RGB.is.Classes.Cart;

public class CartFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	private static JButton removeCart;
	private static JButton backCart;
	private static JButton buyCart;
	private static JButton emptyCart;
	private static JLabel emptyCartIconLbl;
	private static CartTableModel cartItemsTableModel = new CartTableModel();
	private static JTable cartItemsTable = new JTable(cartItemsTableModel);
	private static JScrollPane containerTable = new JScrollPane(cartItemsTable);
	private static JPanel cartList;

	private static JLabel totalProducts = new JLabel();
	private static JLabel totalPrice = new JLabel();

	private static int selectedID = -1;

	private final Container contentPanel = this.getContentPane();

	public CartFrame(JFrame frame) {

		super(frame, true);

		// ESC key
		setFocusable(true);
		addKeyListener(new KeyboardListener(this));

		JPanel southCartPanel = new JPanel(new GridLayout(1, 2));

		// Zona pulsanti azione fondo Carrello
		TitledBorder titleCartButton = BorderFactory.createTitledBorder("Azioni: ");
		JPanel cartBtnsContainer = new JPanel(new GridLayout(1, 1));
		cartBtnsContainer.setBorder(titleCartButton);

		TitledBorder titleInfoCart = BorderFactory.createTitledBorder("Informazione sull'ordine: ");
		JPanel infoCartArea = new JPanel(new FlowLayout());
		infoCartArea.setBorder(titleInfoCart);

		// Zona elenco prodotti nel carrello
		TitledBorder titleCartProduct = BorderFactory.createTitledBorder("Prodotti nel carrello: ");
		cartList = new JPanel(new GridLayout(1, 1));
		cartList.setBorder(titleCartProduct);

		// listener tabella (Mouse + selezione)
		CartTableListener tableListener = new CartTableListener(this);
		// mouse
		cartItemsTable.addMouseListener(tableListener);
		// listener per verificare se si deseleziona una casella
		cartItemsTable.getSelectionModel().addListSelectionListener(tableListener);

		ImageIcon emptyCartIcon = new ImageIcon(this.getClass().getResource("/resources/cart_empty_new.png"));
		emptyCartIconLbl = new JLabel(emptyCartIcon);

		if (Cart.getCartNumberItems() == 0) { // carrello vuoto
			containerTable.setViewportView(emptyCartIconLbl);

		}

		adjustTable(cartItemsTable);

		cartList.add(containerTable);

		// Pulsanti azione
		removeCart = new JButton("Rimuovi articolo");
		removeCart.setEnabled(false);
		removeCart.setActionCommand(CartFrameListener.DELETE_COMMAND);
		removeCart.addActionListener(new CartFrameListener(this));

		emptyCart = new JButton("Svuota carrello");

		emptyCart.setActionCommand(ActionsCommands.DELETE_CART_COMMAND);
		emptyCart.addActionListener(new MainFrameListeners());

		backCart = new JButton("Torna al catalogo");
		backCart.setActionCommand(CartFrameListener.DISPOSE_COMMAND);
		backCart.addActionListener(new CartFrameListener(this));

		buyCart = new JButton("Termina acquisto");
		buyCart.setActionCommand(ActionsCommands.BUY_COMMAND);
		buyCart.addActionListener(new MainFrameListeners());
		buyCart.setForeground(Color.decode("#006600"));

		if (Cart.getCartNumberItems() == 0) { // empty disabilitato se il
												// carrello è vuoto
			emptyCart.setEnabled(false);
			buyCart.setEnabled(false);
			buyCart.setForeground(null);
		}

		// Mostra totale del carrello
		totalProducts = new JLabel("<html><center>N. articoli singoli: <h2>" + Cart.getCartNumberItems() + "</h2>"); // numero
																														// articoli
																														// diversi
		totalPrice = new JLabel(
				"<html><center>Totale:<h2>" + String.format("%.2f", Cart.calculateSubTotaleNotSped()) + " €</h2>");

		// Posizioni
		contentPanel.add(southCartPanel, BorderLayout.SOUTH);
		contentPanel.add(cartList, BorderLayout.CENTER);

		// pannelli
		southCartPanel.add(cartBtnsContainer);
		southCartPanel.add(infoCartArea);

		// pulsanti azione
		JPanel btnsPanel = new JPanel(new FlowLayout());
		btnsPanel.add(removeCart);
		btnsPanel.add(emptyCart);
		btnsPanel.add(backCart);
		btnsPanel.add(buyCart);
		btnsPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
		cartBtnsContainer.add(btnsPanel);

		// Informazioni ordine
		infoCartArea.add(totalProducts);
		infoCartArea.add(totalPrice);

		setTitle("Carrello");
		setSize(1000, 550);
		setIconImage(new ImageIcon(MainFrame.class.getResource("/resources/cart_icon_new.png")).getImage());
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	private void adjustTable(JTable table) {
		table.setOpaque(false);
		// fisso altezza righe
		table.setRowHeight(90);
		// selezione singola
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// colonne non resizing
		table.getTableHeader().setResizingAllowed(false);
		// colonne non mobili
		table.getTableHeader().setReorderingAllowed(false);

		table.setAutoscrolls(true);

		table.setSelectionBackground(Color.decode("#4f5d73")); // 005580

		table.setAutoCreateRowSorter(true);

		TableColumnModel tableColModel = table.getColumnModel();

		for (int i = 0; i < table.getColumnCount(); i++) {

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			centerRenderer.setVerticalAlignment(JLabel.CENTER);

			TableColumn item = tableColModel.getColumn(i);

			if (i != 1) { // escludo l'immagine
				if (i == 0) { // rendo l'Id invisibile
					item.setMinWidth(0);
					item.setMaxWidth(0);
				}

				item.setCellRenderer(centerRenderer);
			}
		}
	}

	public static void updateCartItems() {
		cartItemsTableModel.updateTableModel(Cart.getCart());

		totalProducts.setText("<html><center>N. articoli singoli:<h2>" + Cart.getCartNumberItems() + "</h2>");
		totalPrice.setText("<html><center>Totale: <h2>" + Cart.calculateSubTotaleNotSped() + " €</h2>");

		// imposto la tabella come nuova view nel carrello
		if (Cart.getCartNumberItems() > 0) // se ci sono elementi
			containerTable.setViewportView(cartItemsTable);
		else {
			containerTable.setViewportView(emptyCartIconLbl);
		}
	}

	public static void enableSelectionBtn() {
		removeCart.setEnabled(true);
		removeCart.setForeground(Color.decode("#990000"));
	}

	public static void disableSelectionBtn() {
		removeCart.setEnabled(false);
		removeCart.setForeground(null);
	}

	public static void disableEmptyBtn() {
		emptyCart.setEnabled(false);
		buyCart.setEnabled(false);
		buyCart.setForeground(null);
	}

	public static int getSelectedID() {
		return selectedID;
	}

	public static void setSelectedID(int newID) {
		selectedID = newID;
	}

	public static void setEmptyLayout() {
		containerTable.setViewportView(emptyCartIconLbl);

	}

	public JTable getTable() {
		return cartItemsTable;
	}

}
