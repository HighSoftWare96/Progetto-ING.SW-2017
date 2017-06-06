package it.RGB.is.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.RGB.is.Classes.Cart;
import it.RGB.is.Classes.Prodotto;

public class CartFrameListener implements ActionListener {

	public static final String DISPOSE_COMMAND = "Exit";
	public static final String DELETE_COMMAND = "Elimina";
	public static final String DELETE_CART_COMMAND = "Svuota";

	private CartFrame cartFrame;

	public CartFrameListener(CartFrame cartFrame) {
		this.cartFrame = cartFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String generator = e.getActionCommand();
		switch (generator) {
		case DISPOSE_COMMAND:
			cartFrame.dispose();
			break;
		case DELETE_COMMAND:
			AmountPicker amountSelect = new AmountPicker(cartFrame, Cart.getItemByID(CartFrame.getSelectedID()),
					generator.toString(), 0, Cart.getQuantita(CartFrame.getSelectedID()));

			if (amountSelect.getSelectedValue() > 0) {
				int amountSelected = amountSelect.getSelectedValue();
				Prodotto prodotto = Cart.getItemByID(CartFrame.getSelectedID());

				// aggiorno carrello
				Controller.updateDeleteCart(amountSelected, prodotto);
			}
			break;
		}
	}
}
