package it.RGB.is.Tests.Classes;

import org.junit.Test;

import it.RGB.is.Classes.Cart;
import it.RGB.is.Classes.ModConsegna;
import it.RGB.is.Classes.Prodotto;

public class CartTest {		//??????????????????????

	@Test
	public void test() {
		Cart.getSubTotale(null);
		Cart.removeAll();
		try{
			Cart.removeItem(null, 0);
		}catch (Exception e){
			e.getMessage();
		}
		Cart.buyAndRemove();
		String[] string= new String[10];
		Prodotto pr = new Prodotto(false, "", string, null, 0, null, "", null, null, 0);
		Cart.addItem(pr, 5);
		Cart.removeItem(pr, 20);
		Cart.getSubTotale(ModConsegna.CORRIERE_24H);
		Cart.getCartNumberItems();
		Cart.getItemByID(0);
		Cart.getItemByID(-123);
	}

}
