package it.RGB.is.GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

public class MainTableListener implements MouseListener {

	private static MainPanelProducts mainPanel;

	public MainTableListener(MainPanelProducts mainPanelIn) {
		mainPanel = mainPanelIn;
	}
	
	public MainTableListener(CartFrame cartFrame){
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// singolo click
		if (e.getClickCount() == 1) {
			Controller.tableValueChange(mainPanel.getTable(), mainPanel);
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
