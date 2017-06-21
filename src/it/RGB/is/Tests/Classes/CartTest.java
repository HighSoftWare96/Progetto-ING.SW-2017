package it.RGB.is.Tests.Classes;

import org.junit.Test;

import it.RGB.is.Classes.Cart;
import it.RGB.is.Exceptions.CartIllegalArgumentsException;

public class CartTest {

	@Test(expected = CartIllegalArgumentsException.class) 
	public void removeItemNull() {

		
	     Cart.initialize();
	     Cart.removeItem(null, 1);
	    
	}
	
	@Test(expected = CartIllegalArgumentsException.class) 
	public void removeItemAmount() {

		
	     Cart.initialize();
	     Cart.removeItem(TestData.genericCD, -1);
	     Cart.removeItem(TestData.genericCD, 0);
	}
	
	@Test(expected = CartIllegalArgumentsException.class) 
	public void addItemNull() {

		
	     Cart.initialize();
	     Cart.addItem(null, 1);
	    
	}
	@Test(expected = CartIllegalArgumentsException.class) 
	public void addItemAmount() {

		
	     Cart.initialize();
	     Cart.addItem(TestData.genericCD, -1);
	     Cart.addItem(TestData.genericCD, 0);
	     Cart.addItem(TestData.genericCD, 110);
	} 	
}
