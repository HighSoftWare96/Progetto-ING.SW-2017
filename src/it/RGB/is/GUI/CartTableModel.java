package it.RGB.is.GUI;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import it.RGB.is.Classes.Cart;
import it.RGB.is.Classes.Genere;
import it.RGB.is.Classes.Prodotto;

public class CartTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	String[] ColName = { "ID", "Copertina", "Titolo", "Artista/band", "Genere", "Prezzo (€)", "Quantità" };
	private static Prodotto[] productsInCart = new Prodotto[0];

	public CartTableModel() {
		super();
	}

	public void updateTableModel(Prodotto[] productsData) {
		productsInCart = productsData;
		// aggiornamento dei dati inseriti
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return ColName.length;
	}

	@Override
	public int getRowCount() {
		return productsInCart.length;
	}

	@Override
	public String getColumnName(int i) {
		return ColName[i];
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// per ogni cella definisco per tutte le righe il valore
		// che si andrà a inserire
		Prodotto item = (Prodotto) (productsInCart[rowIndex]);

		switch (columnIndex) {
		case 0:
			return item.getID();
		case 1:
			return item.getCover();
		case 2:
			return item.getTitolo();
		case 3:
			return item.getTitolare().getNomeArte();
		case 4:
			return item.getGenere();
		case 5:
			return item.getPrezzo();
		case 6:
			return Cart.getQuantita(item.getID());
		}

		return null;
	}

	@Override
	public Class<?> getColumnClass(int column) {
		switch (column) {
		case 0:
			return Integer.class;
		case 1:
			return ImageIcon.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return Genere.class;
		case 5:
			return Float.class;
		case 6:
			return Integer.class;
		default:
			return Object.class;
		}
	}

}
