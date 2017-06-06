package it.RGB.is.GUI;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TablesSelectionListener implements ListSelectionListener {

	private JTable table;
	private Object frame;

	public TablesSelectionListener(JTable table, CartFrame frame) {
		// TODO Auto-generated constructor stub
		this.table = table;
		this.frame = frame;
	}

	public TablesSelectionListener(JTable table, MainPanelSidebar frame) {
		// TODO Auto-generated constructor stub
		this.table = table;
		this.frame = frame;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		Controller.tableValueChange(this.table, this.frame);
	}

}
