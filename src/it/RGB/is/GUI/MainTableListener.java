package it.RGB.is.GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class MainTableListener implements MouseListener {

	private MainPanelProducts mainPanel;

	public MainTableListener(MainPanelProducts mainPanel) {
		this.mainPanel = mainPanel;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// singolo click
		if (e.getClickCount() == 1) {
			MainPanelSidebar.enableSelectionBtn();
			TableModel tableModel = mainPanel.getTableModel();
			JTable tableView = mainPanel.getTable();
			int selectedID = (int) tableModel.getValueAt(tableView.getSelectedRow(), 0);
			MainPanelSidebar.setSelectedID(selectedID);
		}

		// doppio click su una riga
		if (e.getClickCount() == 2) {
			new PrdDetailsFrame(GUIMain.getFrame(), MainPanelSidebar.getSelectedID());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		return;
	}

}
