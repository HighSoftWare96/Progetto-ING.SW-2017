package it.RGB.is.GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

public class MainTableListener implements MouseListener {

	private static MainPanelProducts mainPanel;

	public MainTableListener(MainPanelProducts mainPanelIn) {
		mainPanel = mainPanelIn;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// singolo click
		if (e.getClickCount() == 1) {
			getSelected();
		}

		// doppio click su una riga
		if (e.getClickCount() == 2) {
			new PrdDetailsFrame(GUIMain.getFrame(), MainPanelSidebar.getSelectedID());
		}
	}

	public static void getSelected(){
		MainPanelSidebar.enableSelectionBtn();
		JTable tableView = mainPanel.getTable();
		int selectedID = (int) tableView.getValueAt(tableView.getSelectedRow(), 0);
		MainPanelSidebar.setSelectedID(selectedID);
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
