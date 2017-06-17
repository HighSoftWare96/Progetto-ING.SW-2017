package it.RGB.is.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import it.RGB.is.Classes.Catalogo;
import it.RGB.is.Classes.Prodotto;

public class MainPanelProducts extends JPanel {

	private static final long serialVersionUID = 1L;
	private static MainTableModel productsTableModel;
	private JTable productsTable;
	private JScrollPane productsPanel;

	public MainPanelProducts() {
		super(new GridLayout(1, 1));
		TitledBorder title = BorderFactory.createTitledBorder("Prodotti:");

		productsTableModel = new MainTableModel();
		productsTable = new JTable(productsTableModel);
		productsPanel = new JScrollPane(productsTable);

		// IMPOSTO GESTORE EVENTI DELLA TABELLE (sia per la selezione sia per il mouse)
		MainTableProductsListener tableListener = new MainTableProductsListener(this);
		
		// mouse (click e doppio click)
		productsTable.addMouseListener(tableListener);
		// selezione e deselezione
		productsTable.getSelectionModel().addListSelectionListener(tableListener);
		
		adjustTable(productsTable);

		add(productsPanel);
		setBorder(title);
	}

	private void adjustTable(JTable table) {
		table.setToolTipText("Doppio click per dettagli");
		// fisso altezza righe
		table.setRowHeight(table.getRowHeight() + 82);
		// selezione singola
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// colonne non resizing
		table.getTableHeader().setResizingAllowed(false);
		// colonne non mobili
		table.getTableHeader().setReorderingAllowed(false);

		table.setAutoscrolls(true);

		table.setSelectionBackground(Color.decode("#4f5d73")); // 005580

		table.setAutoCreateRowSorter(true);

		for (int i = 0; i < productsTableModel.getColumnCount(); i++) {

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			centerRenderer.setVerticalAlignment(JLabel.CENTER);

			JLabelRenderer rendererForJLabel = new JLabelRenderer();
			rendererForJLabel.setHorizontalAlignment(JLabel.CENTER);
			rendererForJLabel.setVerticalAlignment(JLabel.CENTER);

			TableColumn item = table.getColumnModel().getColumn(i);

			switch (i) {
			case 0:
				item.setCellRenderer(centerRenderer);
				item.setMinWidth(10);
				item.setMaxWidth(30);
				break;
			case 1:
				item.setMinWidth(90);
				item.setMaxWidth(90);
				break;
			case 2:
				item.setCellRenderer(centerRenderer);
				break;
			case 3:
				item.setCellRenderer(rendererForJLabel);
				break;
			default:
				item.setCellRenderer(centerRenderer);
				break;

			}
			// ultima colonna
			if (i == productsTableModel.getColumnCount() - 1) {
				item.setMinWidth(50);
				item.setMaxWidth(50);
			}
		}
	}

	private class JLabelRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;
		JLabel lbl = new JLabel();

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			lbl.setText((String) value);
			lbl.setHorizontalAlignment(JLabel.CENTER);
			lbl.setVerticalAlignment(JLabel.TOP);

			// DEFAULT VALUE
			lbl.setForeground(Color.BLACK);
			lbl.setOpaque(false);
			lbl.setBorder(new EmptyBorder(15, 7, 0, 7));

			if (isSelected) {
				lbl.setOpaque(true);
				lbl.setBackground(Color.decode("#4f5d73")); // 005580
				lbl.setForeground(Color.WHITE);
			}

			return lbl;
		}

	}

	public static void updateCatalogItems() {
		// aggiorno il catalogo
		productsTableModel.updateTableModel(Catalogo.getCatalog());
	}

	public static void updateSearch(Prodotto[] prodotti) {
		productsTableModel.updateTableModel(prodotti);
	}

	public JTable getTable() {
		// TODO Auto-generated method stub
		return productsTable;
	}

	public MainTableModel getTableModel() {
		return productsTableModel;
	}

	public void setResultTitle(String newTitle) {
		if (newTitle == null) { // default
			TitledBorder title = BorderFactory.createTitledBorder("Prodotti: ");
			setBorder(title);
			repaint();
		} else {
			TitledBorder title = BorderFactory.createTitledBorder(newTitle);
			setBorder(title);
			repaint();
		}
	}
}
