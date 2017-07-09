package it.RGB.is.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameListeners implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		String generator = e.getActionCommand();

		switch (generator) {

		// MainPanelButtons: modificata la combo box di ricerca
		case ActionsCommands.SEARCH_COMBO_BOX:
			Controller.searchCombo();
			break;

		case ActionsCommands.SEARCH_COMMAND:
			Controller.searchCommand();
			break;

		case ActionsCommands.SUGGESTIONS_COMMAND:
			Controller.suggestionCommand();
			break;

		case ActionsCommands.DISCOUNT_SHOW_COMMAND:
			Controller.discountCommand();
			break;
		case ActionsCommands.DELETE_SEARCH_COMMAND:
			// restore del catalogo nella tabella
			Controller.deleteSearch();
			break;

		case ActionsCommands.LOG_IN_COMMAND:
			LoginFrame login = new LoginFrame(GUIMain.getFrame());
			login.setVisible(true);
			break;

		case ActionsCommands.LOG_OUT_COMMAND:
			// la classe del menu si preoccupa di impostare tutto a log out
			Controller.setLoggedOut();
			break;

		case ActionsCommands.ABOUT_COMMAND:
			new About(GUIMain.getFrame());
			break;

		case ActionsCommands.CART_VIEW_COMMAND:
			CartFrame.getInstance(GUIMain.getFrame()).setVisible(true);
			break;

		case ActionsCommands.ADD_ITEM_TO_CART:
			Controller.addItem(generator);
			break;
		case ActionsCommands.USER_DETAILS_COMMAND:
			new UsrDetailsFrame(GUIMain.getFrame());
			break;

		case ActionsCommands.DELETE_CART_COMMAND:
			Controller.deleteCart();
			break;

		case ActionsCommands.INFO_PRODUCT_COMMAND:
			new PrdDetailsFrame(GUIMain.getFrame(), MainPanelSidebar.getSelectedID());
			break;

		case ActionsCommands.BUY_COMMAND:
			Controller.buy();
			break;

		default:
			break;
		}
	}

}
