package it.RGB.is.Tests.Classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import it.RGB.is.Classes.Prodotto;
import it.RGB.is.Classes.Cliente;
import it.RGB.is.Classes.ModConsegna;
import it.RGB.is.Classes.Pagamento;
import it.RGB.is.Classes.Vendita;
import it.RGB.is.Tests.Classes.TestData;
import it.RGB.is.Exceptions.VenditaIllegalArgumentException;

public class VenditaTest {
	
	private Vendita venditaTest = null;
	
	
	@Test (expected = VenditaIllegalArgumentException.class)
	public void testVenditaClienteNull() throws VenditaIllegalArgumentException {
		TestData.initializeData();
		new Vendita(null, new Prodotto[] { TestData.getGenericCd() }, new Integer[] { 20 }, 250, new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		
	} 
	
	@Test (expected = VenditaIllegalArgumentException.class)
	public void testVenditaEmptyProduct() throws VenditaIllegalArgumentException {
		TestData.initializeData();
		new Vendita(TestData.getGenericCliente(), new Prodotto [] {}, new Integer[] { 20 }, 250, new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		
	} 
	
	@Test (expected = VenditaIllegalArgumentException.class)
	public void testVenditaPriceWrong() throws VenditaIllegalArgumentException {
		TestData.initializeData();
		new Vendita(TestData.getGenericCliente(), new Prodotto [] { TestData.getGenericCd() }, new Integer[] { 20 }, 0, new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
	}
	@Test (expected = VenditaIllegalArgumentException.class)
	public void testVenditaIncorrectAmount() throws VenditaIllegalArgumentException {
		TestData.initializeData();
		new Vendita(TestData.getGenericCliente(), new Prodotto [] { TestData.getGenericCd(), TestData.getGenericDVD() }, new Integer[] { 20 }, 250, new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
	}
	
	
	
	//TODO
	//Test in caso ci sia (prodotti2.length != amount2.length)
	
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
		int amount = venditaTest.getAmount().length;
		assertEquals(1, amount);
		Date data = venditaTest.getDate();
		assertEquals(new Date(), data);
		assertTrue(venditaTest.getDateString().length() == 17);
		
		
		
		//TODO
		//.getProdottoString(int index)
		//.getProdotti

	}
}
