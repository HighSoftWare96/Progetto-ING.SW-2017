package it.RGB.is.Tests.Classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import it.RGB.is.Classes.Prodotto;
import it.RGB.is.Classes.ModConsegna;
import it.RGB.is.Classes.Pagamento;
import it.RGB.is.Classes.Vendita;
import it.RGB.is.Tests.Classes.TestData;
import it.RGB.is.Exceptions.VenditaIllegalArgumentException;

public class VenditaTest {
	
	private Vendita venditaTest;
	
	
	@Test (expected = VenditaIllegalArgumentException.class)
	public void testVenditaClienteNull() throws VenditaIllegalArgumentException {
		TestData.initializeData();
		venditaTest = new Vendita(null, new Prodotto[] { TestData.getGenericCd() }, new Integer[] { 20 }, 250, new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		
	} 
	
	@Test (expected = VenditaIllegalArgumentException.class)
	public void testVenditaEmpty() throws VenditaIllegalArgumentException {
		TestData.initializeData();
		new Vendita(TestData.getGenericCliente(), new Prodotto [] {}, new Integer[] {20}, 250, new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		
	} 
	
	@Test
	public void testVenditaConstructor() {
		TestData.initializeData();
		venditaTest = TestData.getGenericVendita();
		
		String pagamento = venditaTest.getPagamento();
		assertEquals("Bonifico bancario", pagamento);
		String consegna = venditaTest.getConsegna();
		assertEquals("Corriere 24H", consegna);
		assertTrue(venditaTest.getProdottiLength() == 1);
		assertTrue(venditaTest.getPrezzoTotale() == 250);
		String ip = venditaTest.getIP();
		assertEquals("localhost", ip);		
		
		
		//TODO
		//.getAmount()
		//.getProdottoString(int index)
		//.getDate()
		//.getDateString()
		//.getProdotto()

	}
}
