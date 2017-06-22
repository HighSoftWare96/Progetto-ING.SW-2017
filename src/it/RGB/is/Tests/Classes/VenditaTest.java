package it.RGB.is.Tests.Classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import it.RGB.is.Classes.Vendita;
import it.RGB.is.Tests.Classes.TestData;
import it.RGB.is.Exceptions.VenditaIllegalArgumentException;

public class VenditaTest {
	
	private Vendita venditaTest;

	@Test (expected = VenditaIllegalArgumentException.class)
	public void testVenditaNull() {
		TestData.initializeData();
		new Vendita(null,null, null, 0, new Date(), "", null, null);
		
	}
	
	@Test
	public void testVenditaConstructor() {
		TestData.initializeData();
		venditaTest = TestData.getGenericVendita();
		
		String pagamento = venditaTest.getPagamento();
		assertEquals("Bonifico bancario", pagamento);
		String consegna = venditaTest.getConsegna();
		assertEquals("Corriere 24H", consegna);
		assertTrue(venditaTest.getProdottiLength() == 20);
		assertTrue(venditaTest.getPrezzoTotale() == 250);
		String ip = venditaTest.getIP();
		assertEquals("localhost", ip);
		Date data = venditaTest.getDate();
		assertEquals(new Date(), data);
			
	}
}
