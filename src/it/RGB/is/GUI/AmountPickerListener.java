package it.RGB.is.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmountPickerListener implements ActionListener {

	public static final String DISPOSE_COMMAND = "Annulla";
	public static final String OK_COMMAND = "Ok";

	private AmountPicker ap;

	public AmountPickerListener(AmountPicker ap) {
		super();
		this.ap = ap;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String generator = e.getActionCommand();
		switch (generator) {
		case DISPOSE_COMMAND:
			ap.setValueSelected(-1);
			ap.dispose();
			break;
		case OK_COMMAND:
			// imposto il valore
			ap.setValueSelected((int) ap.spinner.getValue());
			ap.dispose();
			break;
		default:
			break;
		}
	}

}
