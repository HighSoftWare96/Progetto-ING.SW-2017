package it.RGB.is.GUI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import it.RGB.is.Classes.Catalogo;
import it.RGB.is.Classes.Genere;
import it.RGB.is.Classes.Prodotto;

public class MainTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	String[] ColName = { "ID", "Copertina", "Titolo", "Brani", "Artista/band", "Data arrivo", "Genere", "Prezzo",
			"Disp." };

	private static Prodotto[] productsData;

	public MainTableModel() {
		super();
		// TODO Auto-generated constructor stub
		productsData = Catalogo.getCatalog();
	}

	public void updateTableModel(Prodotto[] productsData) {
		MainTableModel.productsData = productsData;
		// aggiornamento dei dati inseriti
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return ColName.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return productsData.length;
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
		Prodotto item = (Prodotto) (productsData[rowIndex]);

		switch (columnIndex) {
		case 0:
			return item.getID();
		case 1:
			return item.getCover();
		case 2:
			return item.getTitolo();
		case 3:
			return "<html><p>" + item.getSongsTitles() + "</p></html>";
		case 4:
			return item.getTitolare().toString();
		case 5:
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
			return dateFormat.format(item.getDataArrivo());
		case 6:
			return item.getGenere();
		case 7:
			return String.format("%.2f €", item.getPrezzo());
		case 8:
			return item.getDisp();
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
			return String.class;
		case 5:
			return String.class;
		case 6:
			return Genere.class;
		case 7:
			return String.class;
		case 8:
			return Integer.class;
		default:
			return Object.class;
		}
	}

}
