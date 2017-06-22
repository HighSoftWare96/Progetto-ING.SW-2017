package it.RGB.is.Tests.Classes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.RGB.is.Classes.Cart;
import it.RGB.is.Classes.ModConsegna;
import it.RGB.is.Exceptions.CartIllegalArgumentsException;

public class CartTest {
	
	public CartTest() {
		TestData.initializeData();
		Cart.initialize();
	}
	
	@Test
	public void testCart(){
		//test rimozione tutti prodotti
		Cart.addItem(TestData.getGenericCd(), 4);
		Cart.addItem(TestData.getGenericDVD(), 2);
		Cart.removeAll();
		assertTrue(Cart.getCartNumberItems() == 0);
		
		//test rimozione un prodotto alla volta
		Cart.addItem(TestData.getGenericCd(), 2);
		Cart.removeItem(TestData.getGenericCd(), 1);
		Cart.removeItem(TestData.getGenericCd(), 1);
		
		assertTrue(Cart.getCartNumberItems() == 0);
		
		Cart.addItem(TestData.getGenericCd(), 1);
		assertTrue((float) Cart.getSubTotale(ModConsegna.CORRIERE_24H) != (float) 2);
		
	}

	@Test(expected = CartIllegalArgumentsException.class) 
	public void removeItemNull() {

	     Cart.removeItem(null, 1);
	    
	}
	
	@Test(expected = CartIllegalArgumentsException.class) 
	public void removeItemAmount() {

	     Cart.removeItem(TestData.getGenericCd(), -1);
	     Cart.removeItem(TestData.getGenericCd(), 0);
	}
	
	@Test(expected = CartIllegalArgumentsException.class) 
	public void addItemNull() {

	     Cart.addItem(null, 1);
	    
	}
	@Test(expected = CartIllegalArgumentsException.class) 
	public void addItemAmount() {

	     Cart.addItem(TestData.getGenericCd(), -1);
	     Cart.addItem(TestData.getGenericCd(), 0);
	     Cart.addItem(TestData.getGenericCd(), 110);
	} 	
}
