package it.RGB.is.Tests.Integration;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.RGB.is.Classes.Cart;
import it.RGB.is.Classes.ModConsegna;
import it.RGB.is.Exceptions.CartIllegalArgumentsException;
import it.RGB.is.Exceptions.LightCartException;
import it.RGB.is.Tests.Unit.TestData;

public class CartTest {

	public CartTest() {
	}

	@Test
	public void removeAllTest() {
		TestData.initializeData();
		// test rimozione tutti prodotti
		Cart.addItem(TestData.getGenericCd(), 4);
		Cart.addItem(TestData.getGenericDVD(), 2);
		Cart.removeAll();
		assertTrue(Cart.getCartNumberItems() == 0);
	}

	@Test
	public void removeTest() {
		TestData.initializeData();
		// test rimozione un prodotto alla volta
		Cart.addItem(TestData.getGenericCd(), 2);
		Cart.removeItem(TestData.getGenericCd(), 1);
		// carrell con un prodotto
		assertTrue(Cart.getCartNumberItems() == 1);

		Cart.removeItem(TestData.getGenericCd(), 1);
		// carrello vuoto
		assertTrue(Cart.getCartNumberItems() == 0);
	}

	@Test
	public void calcPriceTest() {
		TestData.initializeData();

		Cart.addItem(TestData.getGenericCd(), 1);

		// calcolo prezzo
		assertTrue((float) Cart.calculateSubTotaleNotSped() == (float) 12.23);
		assertTrue((float) Cart.getSubTotale(ModConsegna.CORRIERE_24H) != (float) 2);
		assertTrue((float) Cart.getSubTotale(ModConsegna.CORRIERE_24H) == (float) 19.23);
	}

	public void productsInCartTest() {
		TestData.initializeData();

		Cart.addItem(TestData.getGenericCd(), 1);

		// numero prodotti nel carrello
		assertTrue(Cart.getCartNumberItems() == 1);
		assertTrue(Cart.getCart().length == 1);
	}

	@Test
	public void IDFromProductTest() {
		TestData.initializeData();
		Cart.addItem(TestData.getGenericCd(), 1);
		Cart.addItem(TestData.getGenericDVD(), 10);

		// ID da prodotto
		assertTrue(Cart.getQuantita(TestData.getGenericCd().getID()) == 1);
		assertTrue(Cart.getQuantita(TestData.getGenericDVD().getID()) == 10);
	}

	@Test
	public void addToExistingTest() {
		TestData.initializeData();
		Cart.addItem(TestData.getGenericCd(), 1);

		// aumento quantit� di un prodotto gi� presente
		Cart.addItem(TestData.getGenericCd(), 1);
		assertTrue(Cart.getQuantita(TestData.getGenericCd().getID()) == 2);
	}

	@Test
	public void productFromIDTest() {
		TestData.initializeData();
		Cart.addItem(TestData.getGenericCd(), 1);

		// Prodotto da ID
		assertTrue(Cart.getItemByID(TestData.getGenericCd().getID()).equals(TestData.getGenericCd()));
	}

	@Test
	public void completeTest() {
		TestData.initializeData();
		// completamento acquisto
		Cart.addItem(TestData.getGenericCd(), 1);
		Cart.addItem(TestData.getGenericCd(), 1);
		Cart.buyAndRemove();
		assertTrue(Cart.getCartNumberItems() == 0);
	}

	@Test
	public void amountLengthTest() {
		TestData.initializeData();
		// get amount length
		Cart.addItem(TestData.getGenericCd(), 1);
		Cart.addItem(TestData.getGenericCd(), 1);
		assertTrue(Cart.getAmount().length == Cart.getCartNumberItems());
		assertTrue(Cart.getAmount().length == Cart.getCart().length);
	}

	@Test(expected = CartIllegalArgumentsException.class)
	public void testAddCartModeThenAvailable() {
		TestData.initializeData();
		// inserisco pi� prodotti di quanti disponibili nel catalogo
		Cart.addItem(TestData.getGenericCd(), 2000);
	}

	@Test(expected = CartIllegalArgumentsException.class)
	public void removeItemNull() {
		TestData.initializeData();
		Cart.removeItem(null, 1);
	}

	@Test(expected = CartIllegalArgumentsException.class)
	public void removeItemMoreThanPresent() {
		TestData.initializeData();
		Cart.addItem(TestData.getGenericCd(), 10);
		Cart.removeItem(TestData.getGenericCd(), 20);
	}

	@Test(expected = LightCartException.class)
	public void removeItemAmount() {
		TestData.initializeData();
		Cart.removeItem(TestData.getGenericCd(), -1);
		Cart.removeItem(TestData.getGenericCd(), 0);
	}

	@Test(expected = CartIllegalArgumentsException.class)
	public void addItemNull() {
		TestData.initializeData();
		Cart.addItem(null, 1);
	}

	@Test(expected = LightCartException.class)
	public void getItemFromNotExistingProduct() {
		TestData.initializeData();
		Cart.getItemByID(TestData.getGenericCd().getID());
	}

	@Test(expected = LightCartException.class)
	public void getAmountFromNotExistingProduct() {
		TestData.initializeData();
		Cart.getQuantita(TestData.getGenericCd().getID());
	}

	@Test(expected = CartIllegalArgumentsException.class)
	public void addItemAmount() {
		TestData.initializeData();
		Cart.addItem(TestData.getGenericCd(), -1);
		Cart.addItem(TestData.getGenericCd(), 0);
		Cart.addItem(TestData.getGenericCd(), 110);
	}

	@Test(expected = LightCartException.class)
	public void removeCartWrong() {
		TestData.initializeData();
		Cart.removeItem(TestData.getGenericCd(), 1);
	}
}
