package it.RGB.is.GUI;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import it.RGB.is.Classes.BancaUtenti;
import it.RGB.is.Classes.Cart;
import it.RGB.is.Classes.Catalogo;
import it.RGB.is.Classes.Cliente;
import it.RGB.is.Classes.Genere;
import it.RGB.is.Classes.Prodotto;
import it.RGB.is.Classes.SearchMod;
import it.RGB.is.Exceptions.BancaUtentiIllegalArgumentException;
import it.RGB.is.Exceptions.IllegalUserRegistrationException;
import it.RGB.is.Exceptions.NoGenPrefFoundException;

public final class Controller {

	// CARTFRAMELISTENER
	public static void updateDeleteCart(int amount, Prodotto prodotto) {

		// aggiorno il model
		Cart.removeItem(prodotto, amount);

		// aggiorno la view
		CartFrame.updateCartItems();

		// restituisco i prodotti al catalogo
		Catalogo.addItem(prodotto, amount);

		// aggiorno la view del catalogo
		MainPanelProducts.updateCatalogItems();

		// se il carrello è vuoto
		if (Cart.getCartNumberItems() == 0) {
			flushCart();
		}
	}

	public static void flushCart() {
		CartFrame.setEmptyLayout();
		MainFrameMenu.disableEmptyBtn();
		CartFrame.cartEmptyBtns();
	}

	// LOGINFRAMELISTENER
	public static void login(LoginFrame loginFrame) {
		String username = loginFrame.getUserText().getText();
		String password = new String(loginFrame.getPasswordText().getPassword());

		if (BancaUtenti.checkLogIn(username, password)) {
			setLoggedIn();
			JOptionPane.showMessageDialog(loginFrame, "Benvenuto " + BancaUtenti.getLoggedInUser().getUsername() + "!",
					"Accesso avvenuto con successo", JOptionPane.INFORMATION_MESSAGE);
			loginFrame.dispose();
		} else {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(loginFrame, "Nome utente o password errati", "Login fallito",
					JOptionPane.ERROR_MESSAGE);
			loginFrame.flushInputs();
		}
	}

	public static void setLoggedIn() {

		MainFrameMenu.logOpt.setText("Log out...");
		MainFrameMenu.logOpt.setIcon(new ImageIcon(MainFrameMenu.class.getResource("/resources/logout_small.png")));
		MainFrameMenu.logOpt.setActionCommand(ActionsCommands.LOG_OUT_COMMAND);

		MainFrameMenu.usrDetails.setEnabled(true);
		MainFrameMenu.suggestedOpt.setEnabled(true);
		MainFrameMenu.discountsOpt.setEnabled(true);

		MainFrameMenu.logInUser.setText("Benvenuto " + BancaUtenti.getLoggedInUser().getUsername()); // Logged
																										// in
																										// as:
		MainFrameMenu.logInUser.setVisible(true);

		// per gli altri pannelli
		GUIMain.getFrame().setLoggedIn();
		MainPanelSidebar.setLoggedIn();
		MainPanelButtons.setLoggedIn();
	}

	public static void setLoggedIn(Cliente cliente) {
		// effettuo il log in
		BancaUtenti.checkLogIn(cliente.getUsername(), cliente.getPassword());
		setLoggedIn();
	}

	public static void setLoggedOut() {
		MainFrameMenu.logOpt.setText("Log in...");
		MainFrameMenu.logOpt.setIcon(new ImageIcon(MainFrameMenu.class.getResource("/resources/login_small.png")));
		MainFrameMenu.logOpt.setActionCommand(ActionsCommands.LOG_IN_COMMAND);

		MainFrameMenu.usrDetails.setEnabled(false);
		MainFrameMenu.suggestedOpt.setEnabled(false);
		MainFrameMenu.discountsOpt.setEnabled(false);

		MainFrameMenu.logInUser.setVisible(false);

		// per gli altri pannelli
		BancaUtenti.setLoggedOut();
		GUIMain.getFrame().setLoggedOut();
		MainPanelSidebar.setLoggedOut();
		MainPanelButtons.setLoggedOut();
	}

	// MAINFRAMELISTENER
	public static void searchCombo() {
		// recupero l'elemento selezionato
		SearchMod selected = (SearchMod) MainPanelButtons.getComboBoxSearch().getSelectedItem();

		// ricerca per Genere
		if (selected.equals(SearchMod.GENERE)) {
			MainPanelButtons.displayGenSearchLayout();
		} else if (selected.equals(SearchMod.PREZZO)) { // ricerca per
														// prezzo
			MainPanelButtons.displayPriceSearchLayout();
		} else // reset
			MainPanelButtons.restoreDefaultLayout();
	}

	public static void searchCommand() {
		SearchMod selectedSearchMod = (SearchMod) MainPanelButtons.getComboBoxSearch().getSelectedItem();
		String key = MainPanelButtons.getSearchField().getText().toLowerCase();

		switch (selectedSearchMod) {
		case TITOLARE:
		case ARTISTA:
		case TITOLO:
		case BRANI:
			if (!key.equals("")) {
				Prodotto[] results = Catalogo.searchByKey(selectedSearchMod, key);
				if (results.length == 0) {
					MainPanelButtons.flushSearch();
					JOptionPane.showMessageDialog(GUIMain.getFrame(), "Nessun elemento trovato", "Ricerca",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					MainPanelButtons.enableSearch(true);
					MainPanelProducts.updateSearch(results);
					MainFrame.getPanelProducts()
							.setResultTitle("<html>Risultati <b>(" + selectedSearchMod + ", " + key + ")</b>");
				}
			}
			break;
		case GENERE:
			MainPanelButtons.enableSearch(true);
			Genere selectedGen = (Genere) MainPanelButtons.getGenereBoxSearch().getSelectedItem();
			Prodotto[] resultsGen = Catalogo.searchByGen(selectedGen);

			if (resultsGen.length == 0) {
				MainPanelButtons.flushSearch();
				JOptionPane.showMessageDialog(GUIMain.getFrame(), "Nessun elemento trovato", "Ricerca",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				MainPanelButtons.enableSearch(true);
				MainPanelProducts.updateSearch(resultsGen);
				MainFrame.getPanelProducts().setResultTitle("<html>Risultati <b>(" + selectedGen + ")</b>");
			}
			break;
		case PREZZO:
			int fromPrice = (int) MainPanelButtons.getFromPrice().getValue();
			int toPrice = (int) MainPanelButtons.getToPrice().getValue();

			if (fromPrice <= toPrice) {
				MainPanelButtons.enableSearch(true);
				Prodotto[] resultsPrice = Catalogo.searchByPrice(fromPrice, toPrice);
				if (resultsPrice.length == 0) {
					MainPanelButtons.flushSearch();
					JOptionPane.showMessageDialog(GUIMain.getFrame(), "Nessun elemento trovato", "Ricerca",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					MainPanelButtons.enableSearch(true);
					MainPanelProducts.updateSearch(resultsPrice);
					MainFrame.getPanelProducts()
							.setResultTitle("<html>Risultati <b>(da" + fromPrice + "€ a " + toPrice + "€)</b>");
				}
			} else {
				JOptionPane.showMessageDialog(GUIMain.getFrame(), "Parametri di ricerca scorretti", "Ricerca",
						JOptionPane.ERROR_MESSAGE);
				MainPanelButtons.flushSearch();
			}
			break;
		default:
			break;

		}
	}

	public static void suggestionCommand() {
		try {
			Genere gen = null;
			gen = BancaUtenti.getLoggedInUser().calculateGeneriPref(); // potrebbe
																		// lanciare
																		// l'eccezione

			Prodotto[] resultsGen = Catalogo.searchByGen(gen);
			if (resultsGen.length > 0) {
				MainPanelProducts.updateSearch(resultsGen);
				MainFrame.getPanelProducts()
						.setResultTitle("<html><font color = \"green\"><b>Risultati suggeriti per l'utente</b>");
				MainPanelButtons.enableSearch(true);
			} else {
				JOptionPane.showMessageDialog(GUIMain.getFrame(), "Nessun elemento suggerito trovato nel catalogo",
						"Visualizza elementi suggeriti", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (NoGenPrefFoundException e) {
			JOptionPane.showMessageDialog(GUIMain.getFrame(), e.getMessage(), "Visualizza elementi suggeriti",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void discountCommand() {
		if (BancaUtenti.getLoggedInUser().canHaveDiscounts())
			JOptionPane.showMessageDialog(GUIMain.getFrame(),
					"<html><h3>Congratulazioni!</h3>Puoi ricevere uno <b>sconto del 15%</b> sui prossimi acquisti e usufruire della <b>spedizione gratuita!</b>",
					"Sconti disponibili", JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon(GUIMain.class.getResource("/resources/gift_new.png")));
		else
			JOptionPane.showMessageDialog(GUIMain.getFrame(),
					"<html>Spiacenti non sono ancora disponibili sconti per te...<br><i>Questi saranno disponibili se entro l'anno verranno effettuati almeno 3 acquisti superiori ai 250 Euro l'uno",
					"Sconti non disponibili", JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon(GUIMain.class.getResource("/resources/sad_new.png")));

	}

	public static void deleteSearch() {
		// restore del catalogo nella tabella
		MainPanelProducts.updateCatalogItems();
		MainPanelButtons.enableSearch(false);
		MainPanelButtons.flushSearch();
		MainFrame.getPanelProducts().setResultTitle(null);
	}

	public static void addItem(String generator) {
		Prodotto selectedProduct = Catalogo.searchByID(MainPanelSidebar.getSelectedID());
		AmountPicker amountP = new AmountPicker(GUIMain.getFrame(), selectedProduct, generator.toString(), 1,
				selectedProduct.getDisp());
		// massimo la disp dell'oggetto
		// ha selezionato una quantità
		if (amountP.getSelectedValue() > 0) {
			int amountSelected = amountP.getSelectedValue();
			// aggiorno il model
			Cart.addItem(selectedProduct, amountSelected);
			// aggiorno la view
			CartFrame.updateCartItems();
			// rimovo i prodotti dal catalogo
			Catalogo.removeItem(selectedProduct, amountSelected);
			// aggiorno la view del catalogo
			MainPanelProducts.updateCatalogItems();
			MainFrameMenu.enableEmptyBtn();
		}
	}

	public static void deleteCart() {
		int confirm = JOptionPane.showConfirmDialog(GUIMain.getFrame(), "Il carrello verrà svuotato: si è sicuri?",
				"Svuotamento carrello", JOptionPane.OK_CANCEL_OPTION);
		// rimuovo tutto
		if (confirm == 0) {
			Cart.removeAll();
			CartFrame.updateCartItems();
			// aggiorno la view del catalogo
			MainPanelProducts.updateCatalogItems();
			MainFrameMenu.disableEmptyBtn();
			CartFrame.cartEmptyBtns();
		}
	}

	public static void buy() {
		if (BancaUtenti.getLoggedInUser() == null) {
			LoginFrame endLogin = new LoginFrame(GUIMain.getFrame());
			endLogin.setVisible(true);
		}
		if (BancaUtenti.getLoggedInUser() != null) { // logging avvenuto con
														// successo
			new ShippingFrame(GUIMain.getFrame());
		}
	}

	// REGISTERFRAMELISTENER
	public static void registerConfirm(RegisterFrame registerFrame) {

		Cliente newUser = null;

		try {
			newUser = new Cliente(registerFrame.cFText.getText(), registerFrame.userText.getText(),
					new String(registerFrame.passwordText.getPassword()), registerFrame.nameText.getText(),
					registerFrame.surnameText.getText(), registerFrame.cityText.getText(),
					registerFrame.telText.getText(), registerFrame.cellText.getText());
			registerFrame.checkPswsMatch();
		} catch (IllegalUserRegistrationException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(GUIMain.getFrame(), e.getMessage(), "Errore registrazione",
					JOptionPane.ERROR_MESSAGE);
			registerFrame.setErrorLayout(e.getMessage());
			return;
		}

		try {
			// inserisco il nuovo utente nel database
			BancaUtenti.addItem(newUser);
		} catch (BancaUtentiIllegalArgumentException e) {

		}
		// faccio il login
		setLoggedIn(newUser);

		JOptionPane.showMessageDialog(registerFrame, "Registrazione avvenuta con successo!", "Benvenuto!",
				JOptionPane.INFORMATION_MESSAGE);
		// completamento
		registerFrame.disposeWithSuccess();

	}

	// FUNZIONI PER LA SELEZIONE DELLE TABELLE

	// Tabella principale dei PRODOTTI

	public static void deselectedProductsTable(JTable tableView) {
		// niente selezionato
		if (tableView.getSelectedRow() == -1) {
			// disabilito la selezione nella sidebar
			MainPanelSidebar.disableSelectionBtn();
		} else { // selezionato qualcosa
			selectedProductsTable(tableView);
		}
	}

	public static void selectedProductsTable(JTable tableView) {
		// è stato selezionato qualcosa imposto la sidebar
		MainPanelSidebar.enableSelectionBtn();
		int selectedID = (int) tableView.getValueAt(tableView.getSelectedRow(), 0);
		MainPanelSidebar.setSelectedID(selectedID);
	}

	// Tabella del CARRELLO

	public static void deselectedCartTable(JTable tableView) {
		// niente selezionato
		if (tableView.getSelectedRow() == -1) {
			// disabilito la selezione nel carrello
			CartFrame.disableSelectionBtn();
		} else { // selezionato qualcosa
			selectedCartTable(tableView);
		}
	}

	public static void selectedCartTable(JTable tableView) {
		// abilito e imposto l'id all'oggetto selezionato
		CartFrame.setSelectedID((int) tableView.getValueAt(tableView.getSelectedRow(), 0));
		CartFrame.enableSelectionBtn();
	}

}
