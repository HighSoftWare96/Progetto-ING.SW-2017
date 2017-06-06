package it.RGB.is.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyCheckListeners implements ActionListener {

	private ShippingFrame frame;

	public static final String RADIO_COMMAND = "Radio command";
	public static final String RADIO_BON_COMMAND = "Radio card bonifico";
	public static final String RADIO_CARD_COMMAND = "Radio card";
	public static final String RADIO_PP_COMMAND = "Paypal opt";

	private static boolean pagamentoIsSelected = false;
	private static boolean spedizioneIsSelected = false;

	public BuyCheckListeners(ShippingFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String generator = e.getActionCommand();
		switch (generator) {
		case RADIO_COMMAND:
			spedizioneIsSelected = true;
			frame.enableProceed();
			frame.setConsegnaSelezionata();
			break;
		case RADIO_BON_COMMAND:
			pagamentoIsSelected = true;
			frame.setPagamentoSelezionato();
			frame.enableProceed();
			frame.noOpt();
			break;
		case RADIO_CARD_COMMAND:
			pagamentoIsSelected = true;
			frame.setPagamentoSelezionato();
			frame.activateCardOpt();
			frame.enableProceed();
			break;
		case RADIO_PP_COMMAND:
			pagamentoIsSelected = true;
			frame.setPagamentoSelezionato();
			frame.enableProceed(); // TODO
			frame.activatePPOpt();
			break;
		default:
			break;
		}

	}

	public static boolean spedizioneIsSelected() {
		return spedizioneIsSelected;
	}

	public static boolean pagamentoIsSelected() {
		return pagamentoIsSelected;
	}

	public static void clear() {
		spedizioneIsSelected = false;
		pagamentoIsSelected = false;

	}
}
