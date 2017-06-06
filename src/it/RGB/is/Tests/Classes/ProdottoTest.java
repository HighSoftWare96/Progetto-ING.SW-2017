package it.RGB.is.Tests.Classes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.RGB.is.Classes.Prodotto;

public class ProdottoTest {

	private Prodotto genericCD = TestData.genericCD;
	private Prodotto genericDVD = TestData.genericDVD;
	
	@Test
	public void testProdotto() {
		assertTrue(!genericCD.getTitolo().contains("DVD"));
		assertTrue(genericCD.getTitolo().contains("Toto"));
		assertTrue(genericDVD.getTitolo().contains("DVD"));
		assertTrue(genericCD.getPartecipanti().length > 0);
		genericCD.setNewDisp(10);
		assertTrue(genericCD.getDisp() == 90);
		genericCD.setNewDispAdd(10);
		assertTrue(genericCD.getDisp() == 100);
		assertTrue(genericCD.getID() == 0);
		assertFalse(genericCD.equals(genericDVD));
	}

}
