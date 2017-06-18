package it.RGB.is.GUI;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class MainTableProductsPopMenu extends JPopupMenu{

	private static final long serialVersionUID = 1L;
	private JMenuItem cartAddBtn = new JMenuItem("Aggiungi al carrello", new ImageIcon(this.getClass().getResource("/resources/buy_small.png")));
	private JMenuItem detailsBtn = new JMenuItem("Dettagli", new ImageIcon(this.getClass().getResource("/resources/info_small.png")));
	
	public MainTableProductsPopMenu() {
		super();
		
		
		cartAddBtn.setActionCommand(ActionsCommands.ADD_ITEM_TO_CART);
		cartAddBtn.addActionListener(new MainFrameListeners());
		
		detailsBtn.setActionCommand(ActionsCommands.INFO_PRODUCT_COMMAND);
		detailsBtn.addActionListener(new MainFrameListeners());
		
		this.add(cartAddBtn);
		this.add(detailsBtn);
		this.setVisible(true);
	}
}
