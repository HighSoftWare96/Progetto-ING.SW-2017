package it.RGB.is.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import it.RGB.is.Classes.BancaUtenti;
import it.RGB.is.Classes.BancaVendite;
import it.RGB.is.Classes.Cart;
import it.RGB.is.Classes.Cliente;
import it.RGB.is.Classes.ModConsegna;
import it.RGB.is.Classes.Pagamento;
import it.RGB.is.Classes.Vendita;
import it.RGB.is.Exceptions.LightBancaVenditeException;

public class BuyListeners implements ActionListener {
	public static final String CANCEL_COMMAND = "Cancel";
	public static final String AVANTI_COMMAND = "Avanti";
	public static final String INDIETRO_COMMAND = "Indietro";
	public static final String FINAL_COMMAND = "End";

	private ShippingFrame frame;

	public BuyListeners(ShippingFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String generator = e.getActionCommand();

		switch (generator) {
		case CANCEL_COMMAND:
			BuyCheckListeners.clear();
			frame.dispose();
			break;
		case AVANTI_COMMAND:
			// aggiorno i valori selezionati
			frame.setPagamentoSelezionato();
			frame.setConsegnaSelezionata();

			if (frame.getStatus() == 0) { // spedizione
				frame.proceed();
				if (frame.getPagamentoSelezionato() == Pagamento.CARTA_CREDITO)
					frame.activateCardOpt();
				else if (frame.getPagamentoSelezionato() == Pagamento.PAYPAL)
					frame.activatePPOpt();
			} else if (frame.getPagamentoSelezionato() == Pagamento.CARTA_CREDITO && frame.checkPaymentsData()) {
				frame.proceed();
			} else if (frame.getPagamentoSelezionato() == Pagamento.PAYPAL && frame.checkPaymentsDataPP()) {
				frame.proceed();
			} else if (frame.getPagamentoSelezionato() == Pagamento.BONIFICO)
				frame.proceed();

			break;
		case INDIETRO_COMMAND:
			frame.turnBack();
			// aggiorno i valori selezionati
			frame.setPagamentoSelezionato();
			frame.setConsegnaSelezionata();

			// abilito field selezionati
			if (frame.getPagamentoSelezionato() == Pagamento.CARTA_CREDITO)
				frame.activateCardOpt();
			else if (frame.getPagamentoSelezionato() == Pagamento.PAYPAL)
				frame.activatePPOpt();
			break;

		case FINAL_COMMAND:
			ImageIcon camionIcon = new ImageIcon(this.getClass().getResource("/resources/end_order.gif"));
			String dataArrivoString = "";

			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // lo imposto a oggi

			if (frame.getConsegnaSelezionata() == ModConsegna.CORRIERE_24H)
				c.add(Calendar.DAY_OF_MONTH, 2);
			else if (frame.getConsegnaSelezionata() == ModConsegna.POSTA)
				c.add(Calendar.DAY_OF_MONTH, 15);
			else if (frame.getConsegnaSelezionata() == ModConsegna.GRATIS)
				c.add(Calendar.DAY_OF_MONTH, 5);

			Date arrivalDate = c.getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			dataArrivoString = formatter.format(arrivalDate);

			JOptionPane
					.showMessageDialog(frame,
							"<html><center><h1>Grazie!</h1>La ringraziamo di aver scelto <b>Music Store by RGB</b>.<br> Il suo ordine sarà consegnato intorno al "
									+ dataArrivoString,
							"Ordine completato!", JOptionPane.INFORMATION_MESSAGE, camionIcon);

			// salvataggio della vendita
			Vendita current = new Vendita(BancaUtenti.getLoggedInUser(), Cart.getCart(), Cart.getAmount(),
					Cart.getSubTotale(frame.getConsegnaSelezionata()), new Date(), "localhost",
					frame.getPagamentoSelezionato(), frame.getConsegnaSelezionata());

			try {
				// aggiungo alla banca vendite
				BancaVendite.addItem(current);
			} catch (LightBancaVenditeException e1) {
				e1.printStackTrace();
			} 

			// aggiungo alla lista dell'utente
			Cliente clienteLoggato = BancaUtenti.getLoggedInUser();
			clienteLoggato.addVendita(current);

			// svuoto il carrello senza aggiornare il catalogo...
			Cart.buyAndRemove();

			CartFrame.updateCartItems();
			Controller.flushCart();

			frame.dispose();
			break;
		}

	}

}
