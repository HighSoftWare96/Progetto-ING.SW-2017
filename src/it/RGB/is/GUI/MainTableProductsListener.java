package it.RGB.is.GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainTableProductsListener implements ListSelectionListener, MouseListener {

	private MainPanelProducts mainPanel;

	public MainTableProductsListener(MainPanelProducts frame) {
		this.mainPanel = frame;
	}

	// CAMBIO della selezione (probabile DEselezione)
	@Override
	public void valueChanged(ListSelectionEvent e) {
		Controller.deselectedProductsTable(this.mainPanel.getTable());
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

	// CLICK: probabile selezione
	@Override
	public void mousePressed(MouseEvent e) {
		// click col sinistro
		if (SwingUtilities.isLeftMouseButton(e)) {
			// singolo click
			if (e.getClickCount() == 1) {
				Controller.selectedProductsTable(mainPanel.getTable());
			}

			// doppio click su una riga
			if (e.getClickCount() == 2) {
				new PrdDetailsFrame(GUIMain.getFrame(), MainPanelSidebar.getSelectedID());
			}
		} else if (SwingUtilities.isRightMouseButton(e)) {
			JTable referredTable = mainPanel.getTable();
			// recupero l'indice della riga in cui siè fatto il right click
			int selectedRow = referredTable.rowAtPoint(e.getPoint());

			// click legittimo
			if (selectedRow > -1) {
				// seleziono quella riga ( stesso comportamento della selezione
				// con left click)
				referredTable.setRowSelectionInterval(selectedRow, selectedRow);

				// Menu a comparsa faccio comparire il menu con le coordinate
				// date dall'evento del mouse
				new MainTableProductsPopMenu().show(mainPanel, e.getXOnScreen(), e.getYOnScreen() - 40);
				;
			}
		} else {
			// in caso elimino la selezione delle righe
			mainPanel.getTable().clearSelection();
		}
	}

}
