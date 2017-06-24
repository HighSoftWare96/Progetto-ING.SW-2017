package it.RGB.is.Tests.Unit;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import it.RGB.is.Classes.Prodotto;
import it.RGB.is.Classes.ModConsegna;
import it.RGB.is.Classes.Pagamento;
import it.RGB.is.Classes.Vendita;
import it.RGB.is.Exceptions.VenditaIllegalArgumentException;
import it.RGB.is.Tests.Unit.TestData;

public class VenditaTest {
	
	private Vendita venditaTest = null;
	
	public VenditaTest() {
		TestData.initializeData();
		venditaTest = TestData.getGenericVendita();
	}
		
	@Test (expected = VenditaIllegalArgumentException.class)
	public void testVenditaClienteNull() throws VenditaIllegalArgumentException {
		new Vendita(null, new Prodotto[] { TestData.getGenericCd() }, new Integer[] { 20 }, 250, new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		
	} 
	
	@Test (expected = VenditaIllegalArgumentException.class)
	public void testVenditaEmptyProduct() throws VenditaIllegalArgumentException {
		new Vendita(TestData.getGenericCliente(), new Prodotto [] {}, new Integer[] { 20 }, 250, new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		
	} 
	
	@Test (expected = VenditaIllegalArgumentException.class)
	public void testVenditaPriceWrong() throws VenditaIllegalArgumentException {
		new Vendita(TestData.getGenericCliente(), new Prodotto [] { TestData.getGenericCd() }, new Integer[] { 20 }, 0, new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
	}
	
	@Test (expected = VenditaIllegalArgumentException.class)
	public void testVenditaIncorrectAmount() throws VenditaIllegalArgumentException {
		new Vendita(TestData.getGenericCliente(), new Prodotto [] { TestData.getGenericCd(), TestData.getGenericDVD() }, new Integer[] { 20 }, 250, new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
	}
	
	@Test
	public void testVenditaConstructor() {
		
		assertFalse(venditaTest.getProdotti().equals(null));
		int amount = venditaTest.getAmount().length;
		assertEquals(1, amount);
		assertTrue(venditaTest.getPrezzoTotale() == 250);
		assertTrue(venditaTest.getDateString().length() == 17);
		Date data = venditaTest.getDate();
		assertEquals(new Date().toString(), data.toString());
		String ip = venditaTest.getIP();
		assertEquals("localhost", ip);	
		String pagamento = venditaTest.getPagamento();
		assertEquals("Bonifico bancario", pagamento);
		String consegna = venditaTest.getConsegna();
		assertEquals("Corriere 24H", consegna);
		assertTrue(venditaTest.getProdottiLength() == 1);
		assertEquals("Toto XIV", venditaTest.getProdottoString(0));	

	}
}
