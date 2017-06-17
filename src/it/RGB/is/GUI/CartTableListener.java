package it.RGB.is.GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CartTableListener implements ListSelectionListener, MouseListener {

	private CartFrame mainPanel;

	public CartTableListener(CartFrame frame) {
		this.mainPanel = frame;
	}

	// CAMBIO della selezione (probabile DEselezione)
	@Override
	public void valueChanged(ListSelectionEvent e) {
		Controller.deselectedCartTable(mainPanel.getTable());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	//CLICK: probabile selezione
	@Override
	public void mousePressed(MouseEvent e) {
		// singolo click
		if (e.getClickCount() == 1) {
			Controller.selectedCartTable(mainPanel.getTable());
		}
	}


}
