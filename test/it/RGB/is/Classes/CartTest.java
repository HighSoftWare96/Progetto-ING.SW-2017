package it.RGB.is.Classes;

import org.junit.Test;

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
