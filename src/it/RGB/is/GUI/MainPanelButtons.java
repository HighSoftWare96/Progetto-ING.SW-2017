package it.RGB.is.GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import it.RGB.is.Classes.Genere;
import it.RGB.is.Classes.SearchMod;

public class MainPanelButtons extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JComboBox<SearchMod> comboBoxSearch;
	private static JComboBox<Genere> genereBoxSearch;

	private static JTextField searchField;
	private static JLabel fromLbl;
	private static JSpinner fromPrice;
	private static JLabel toLbl;
	private static JSpinner toPrice;

	private static JButton suggestedOpt;
	private static JButton discountsOpt;

	private static JButton updateViewSearchBtn;
	private static JButton deleteSearch;
	private static JLabel euroLabel;

	public MainPanelButtons() {
		super(new GridLayout(1, 2));

		TitledBorder title = BorderFactory.createTitledBorder("Ricerca avanzata: ");
		JPanel searchArea = new JPanel(new FlowLayout());
		searchArea.setBorder(title);

		JLabel searchText = new JLabel("Ricerca per: ");

		comboBoxSearch = new JComboBox<>(SearchMod.values());
		comboBoxSearch.setActionCommand(ActionsCommands.SEARCH_COMBO_BOX);
		comboBoxSearch.addActionListener(new MainFrameListeners());

		genereBoxSearch = new JComboBox<>(Genere.values());
		genereBoxSearch.setVisible(false);

		searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(150, 25));
		searchField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
					updateViewSearchBtn.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		euroLabel = new JLabel(" €");
		euroLabel.setVisible(false);

		fromLbl = new JLabel("Da: ");
		fromLbl.setVisible(false);
		fromPrice = new JSpinner(new SpinnerNumberModel(0, 0, 10000, 1));
		fromPrice.setVisible(false);

		toLbl = new JLabel("A: ");
		toLbl.setVisible(false);
		toPrice = new JSpinner(new SpinnerNumberModel(0, 0, 10000, 1));
		toPrice.setVisible(false);

		ImageIcon searchIcon = new ImageIcon(this.getClass().getResource("/resources/search.png"));
		updateViewSearchBtn = new JButton(searchIcon);
		updateViewSearchBtn.setActionCommand(ActionsCommands.SEARCH_COMMAND);
		updateViewSearchBtn.addActionListener(new MainFrameListeners());

		updateViewSearchBtn.setPreferredSize(new Dimension(32, 32));

		ImageIcon deleteIcon = new ImageIcon(this.getClass().getResource("/resources/exit_search.png"));
		deleteSearch = new JButton(deleteIcon);
		deleteSearch.setActionCommand(ActionsCommands.DELETE_SEARCH_COMMAND);
		deleteSearch.addActionListener(new MainFrameListeners());

		deleteSearch.setPreferredSize(new Dimension(32, 32));
		deleteSearch.setVisible(false);

		searchArea.add(searchText);
		searchArea.add(comboBoxSearch);
		searchArea.add(genereBoxSearch);
		searchArea.add(searchField);

		searchArea.add(fromLbl);
		searchArea.add(fromPrice);
		searchArea.add(toLbl);
		searchArea.add(toPrice);
		searchArea.add(euroLabel);

		searchArea.add(updateViewSearchBtn);

		searchArea.add(deleteSearch);

		TitledBorder titleOptional = BorderFactory.createTitledBorder("Area cliente: ");
		JPanel optionalPanel = new JPanel(new FlowLayout());
		optionalPanel.setBorder(titleOptional);

		suggestedOpt = new JButton("Visualizza articoli suggeriti");
		suggestedOpt.setEnabled(false);
		suggestedOpt.setActionCommand(ActionsCommands.SUGGESTIONS_COMMAND);
		suggestedOpt.addActionListener(new MainFrameListeners());

		discountsOpt = new JButton("I miei sconti");
		discountsOpt.setActionCommand(ActionsCommands.DISCOUNT_SHOW_COMMAND);
		discountsOpt.addActionListener(new MainFrameListeners());
		discountsOpt.setEnabled(false);

		optionalPanel.add(suggestedOpt);
		optionalPanel.add(discountsOpt);

		add(searchArea);
		add(optionalPanel);

		setVisible(true);

	}

	public static JComboBox<SearchMod> getComboBoxSearch() {
		return MainPanelButtons.comboBoxSearch;
	}

	public static JComboBox<Genere> getGenereBoxSearch() {
		return MainPanelButtons.genereBoxSearch;
	}

	public static JTextField getSearchField() {
		return MainPanelButtons.searchField;
	}

	public static JSpinner getFromPrice() {
		return MainPanelButtons.fromPrice;
	}

	public static JSpinner getToPrice() {
		return MainPanelButtons.toPrice;
	}

	public static void enableSearch(boolean enable) {
		updateViewSearchBtn.setEnabled(!enable);
		deleteSearch.setVisible(enable);
		//per far si che bisogna cancellare la ricerca per modificare i Box o scrivere nel FIeld
		searchField.setEnabled(!enable);
		comboBoxSearch.setEnabled(!enable);
		genereBoxSearch.setEnabled(!enable);
		fromPrice.setEnabled(!enable);
		toPrice.setEnabled(!enable);
	}

	public static void displayGenSearchLayout() {
		genereBoxSearch.setVisible(true);
		searchField.setVisible(false);
		fromLbl.setVisible(false);
		fromPrice.setVisible(false);
		toLbl.setVisible(false);
		toPrice.setVisible(false);
		euroLabel.setVisible(false);
	}

	public static void restoreDefaultLayout() {
		genereBoxSearch.setVisible(false);
		searchField.setVisible(true);
		fromLbl.setVisible(false);
		fromPrice.setVisible(false);
		toLbl.setVisible(false);
		toPrice.setVisible(false);
		euroLabel.setVisible(false);
	}

	public static void displayPriceSearchLayout() {
		searchField.setVisible(false);
		fromLbl.setVisible(true);
		fromPrice.setVisible(true);
		toLbl.setVisible(true);
		toPrice.setVisible(true);
		euroLabel.setVisible(true);
		genereBoxSearch.setVisible(false);
	}

	public static void setLoggedIn() {
		suggestedOpt.setEnabled(true);
		discountsOpt.setEnabled(true);
	}

	public static void setLoggedOut() {
		suggestedOpt.setEnabled(false);
		discountsOpt.setEnabled(false);
	}

	public static void flushSearch() {
		enableSearch(false);
		fromPrice.setValue(0);
		toPrice.setValue(0);
		searchField.setText("");
	}
}
