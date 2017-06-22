package it.RGB.is.Tests.Classes;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import it.RGB.is.Classes.BancaUtenti;
import it.RGB.is.Classes.Catalogo;
import it.RGB.is.Classes.Cliente;
import it.RGB.is.Classes.ModConsegna;
import it.RGB.is.Classes.Pagamento;
import it.RGB.is.Classes.Prodotto;
import it.RGB.is.Classes.Vendita;
import it.RGB.is.Exceptions.IllegalUserRegistrationException;
import it.RGB.is.Exceptions.VenditaIllegalArgumentException;

public class ClienteTest {
	private Cliente clienteOnTesting;

	public ClienteTest() {
		try {
			BancaUtenti.initialize();
			clienteOnTesting = new Cliente("BRTGNN96T21B296N", "bertonc96H", "ciao123", "Giovanni", "Bertoncelli",
					"Verona", "000000000000", null);
			BancaUtenti.addItem(clienteOnTesting);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testConstructor() {
		String nome = clienteOnTesting.getNome();
		Assert.assertEquals("Nome cliente", "Giovanni", nome);
		String cognome = clienteOnTesting.getCognome();
		Assert.assertEquals("Cognome cliente", "Bertoncelli", cognome);
		String cf = clienteOnTesting.getCF();
		Assert.assertEquals("CF cliente", "BRTGNN96T21B296N", cf);
		String psw = clienteOnTesting.getPassword();
		Assert.assertEquals("Psw cliente", "ciao123", psw);
		String city = clienteOnTesting.getCity();
		Assert.assertEquals("Città cliente", "Verona", city);
		String tel = clienteOnTesting.getTel();
		Assert.assertEquals("Tel cliente", "000000000000", tel);
		Assert.assertFalse(clienteOnTesting.canHaveDiscounts());
	}

	@Test
	public void testAddVendita() {
		Vendita vendita = null;
		try {
			vendita = new Vendita(clienteOnTesting, new Prodotto[] { TestData.genericCD }, new Integer[] { 20 },
					TestData.genericCD.getPrezzo() * 20, new Date(), "localhost", Pagamento.BONIFICO,
					ModConsegna.CORRIERE_24H);
		} catch (Exception e) {
			e.printStackTrace();
		}
		clienteOnTesting.addVendita(vendita);
		Vendita[] venditeCliente = clienteOnTesting.getVendite();
		Assert.assertNotNull(vendita);
		Assert.assertNotNull(venditeCliente);
		Assert.assertEquals(1, venditeCliente.length); // ho una sola vendita
		Assert.assertEquals(1, venditeCliente[0].getProdottiLength()); // con un
																		// solo
																		// prodotto
		Assert.assertEquals(vendita, venditeCliente[0]); // è quella che ho
															// inserito io
		Assert.assertTrue(TestData.genericCD.getPrezzo() * 20 == venditeCliente[0].getPrezzoTotale()); // è
																										// quella
																										// che
																										// ho
																										// inserito
																										// io
	}

	@Test (expected = VenditaIllegalArgumentException.class)
	public void testAddVenditaZero() {
		
		Vendita vendita = new Vendita(clienteOnTesting, new Prodotto[] {}, new Integer[] {}, 0, new Date(), "localhost",
				Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		clienteOnTesting.addVendita(vendita);
		Vendita[] venditeCliente = clienteOnTesting.getVendite();
		Assert.assertNotNull(vendita);
		Assert.assertNotNull(venditeCliente);
		Assert.assertEquals(1, venditeCliente.length); // ho una sola vendita
		Assert.assertEquals(0, venditeCliente[0].getProdottiLength()); // con un
																		// solo
																		// prodotto
		Assert.assertTrue(venditeCliente[0].getProdotti().length == 0); // nessun
																		// prodotto
		Assert.assertNotNull(venditeCliente[0]); // è quella che ho inserito io
		Assert.assertTrue(0 == venditeCliente[0].getPrezzoTotale()); // è quella
																		// che
																		// ho
																		// inserito
																		// io
	}

	@Test
	public void testEquals() throws IllegalUserRegistrationException {
		Cliente clienteForEquals = new Cliente("BRTGNN96T21B296N", "bertonc96", "ciao123", "Giovanni", "Bertoncelli",
				"Verona", "000000000000", null);
		Assert.assertTrue(clienteForEquals.equals(clienteOnTesting));
		Assert.assertTrue(clienteForEquals.hashCode() == clienteOnTesting.hashCode());
		clienteForEquals = new Cliente("BRTGNN96T21B2N", "bertonc96", "ciao123", "Giovanni", "Bertoncelli", "Verona",
				"000000000000", null);
		Assert.assertTrue(clienteForEquals.equals(clienteOnTesting));
		Assert.assertTrue(clienteForEquals.hashCode() == clienteOnTesting.hashCode());
		clienteForEquals = new Cliente("BRTGNN96T21B296N", "bertonc95", "ciao123", "Giovanni", "Bertoncelli", "Verona",
				"000000000000", null);
		Assert.assertFalse(clienteForEquals.equals(clienteOnTesting));
		Assert.assertFalse(clienteForEquals.hashCode() == clienteOnTesting.hashCode());
	}

	@Test
	public void testDiscounts() {
		Vendita vendita = new Vendita(clienteOnTesting, new Prodotto[] { TestData.genericCD }, new Integer[] { 20 },
				250, new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		clienteOnTesting.addVendita(vendita);
		clienteOnTesting.addVendita(vendita);
		clienteOnTesting.addVendita(vendita);
		Assert.assertFalse(clienteOnTesting.canHaveDiscounts());

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -13); // tredici mesi fa
		Date aYearAgo = c.getTime();

		vendita = new Vendita(clienteOnTesting, new Prodotto[] { TestData.genericCD }, new Integer[] { 20 }, 251,
				aYearAgo, "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		clienteOnTesting.addVendita(vendita);
		clienteOnTesting.addVendita(vendita);
		clienteOnTesting.addVendita(vendita);
		Assert.assertFalse(clienteOnTesting.canHaveDiscounts());

		vendita = new Vendita(clienteOnTesting, new Prodotto[] { TestData.genericCD }, new Integer[] { 20 }, 251,
				aYearAgo, "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		clienteOnTesting.addVendita(vendita);
		clienteOnTesting.addVendita(vendita);
		vendita = new Vendita(clienteOnTesting, new Prodotto[] { TestData.genericCD }, new Integer[] { 20 }, 251,
				new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		clienteOnTesting.addVendita(vendita);
		Assert.assertFalse(clienteOnTesting.canHaveDiscounts());

		vendita = new Vendita(clienteOnTesting, new Prodotto[] { TestData.genericCD }, new Integer[] { 20 }, 251,
				new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		clienteOnTesting.addVendita(vendita);
		clienteOnTesting.addVendita(vendita);
		clienteOnTesting.addVendita(vendita);
		Assert.assertTrue(clienteOnTesting.canHaveDiscounts());

	}

}
