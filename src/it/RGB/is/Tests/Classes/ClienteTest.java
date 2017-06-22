package it.RGB.is.Tests.Classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import it.RGB.is.Classes.Cliente;
import it.RGB.is.Classes.ModConsegna;
import it.RGB.is.Classes.Pagamento;
import it.RGB.is.Classes.Prodotto;
import it.RGB.is.Classes.Vendita;
import it.RGB.is.Exceptions.IllegalUserRegistrationException;

public class ClienteTest {

	private Cliente clienteOnTesting;

	public ClienteTest() {
		try {
			
			Catalogo.initialize();
			BancaUtenti.initialize();
			
			TestData.initializeData();
			
			clienteOnTesting = TestData.getGenericCliente();
			
			BancaUtenti.addItem(clienteOnTesting);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(expected = IllegalUserRegistrationException.class)
	public void TestClienteEmptyUserName() throws IllegalUserRegistrationException {
		new Cliente("BRTGNN96T21B296N", "", "ciao123", "Mario", "Rossi", "Verona", "000000000000", null);
	}

	@Test(expected = IllegalUserRegistrationException.class)
	public void TestClienteWrongCF() throws IllegalUserRegistrationException {
		new Cliente("BRTGNN96T21B296", "", "ciao123", "Mario", "Rossi", "Verona", "000000000000", null);
	}

	@Test(expected = IllegalUserRegistrationException.class)
	public void TestClienteWrongCFFormat() throws IllegalUserRegistrationException {
		new Cliente("AAAAAAAAAAAAAAA", "", "ciao123", "Mario", "Rossi", "Verona", "000000000000", null);
	}

	@Test(expected = IllegalUserRegistrationException.class)
	public void TestClienteAlreadyExist() throws IllegalUserRegistrationException {
		new Cliente("BRTGNN96T21B296N", "bertonc96", "ciao123", "Mario", "Rossi", "Verona", "000000000000", null);
	}

	@Test(expected = IllegalUserRegistrationException.class)
	public void TestClienteNotPassword() throws IllegalUserRegistrationException {
		new Cliente("BRTGNN96T21B296N", "bertonc96", "", "Mario", "Rossi", "Verona", "000000000000", null);
	}

	@Test
	public void testConstructor() {
		String nome = clienteOnTesting.getNome();
		assertEquals("Nome cliente", "Mario", nome);
		String cognome = clienteOnTesting.getCognome();
		assertEquals("Cognome cliente", "Rossi", cognome);
		String cf = clienteOnTesting.getCF();
		assertEquals("CF cliente", "BRTGNN96T21B296N", cf);
		String psw = clienteOnTesting.getPassword();
		assertEquals("Psw cliente", "ciao123", psw);
		String city = clienteOnTesting.getCity();
		assertEquals("Citt� cliente", "Verona", city);
		String tel = clienteOnTesting.getTel();
		assertEquals("Tel cliente", "000000000000", tel);
		assertFalse(clienteOnTesting.canHaveDiscounts());
		// test cellulare
		assertTrue(clienteOnTesting.getCell().equals(""));
	}

	@Test
	public void testClienteWithCell() throws IllegalUserRegistrationException {
		clienteOnTesting = new Cliente("BRTGNN96T21B296N", "testUser", "ciao123", "Mario", "Rossi", "Verona",
				"000000000000", "000000000000");
		assertNotNull(clienteOnTesting.getCell());
		assertFalse(clienteOnTesting.getCell().equals(""));
	}

	@Test
	public void testAddVendita() {
		Vendita vendita = null;
		try {
			vendita = new Vendita(clienteOnTesting, new Prodotto[] { TestData.getGenericCd() }, new Integer[] { 20 },
					TestData.getGenericCd().getPrezzo() * 20, new Date(), "localhost", Pagamento.BONIFICO,
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
		Assert.assertEquals(vendita, venditeCliente[0]); // � quella che ho
															// inserito io
		Assert.assertTrue(TestData.getGenericCd().getPrezzo() * 20 == venditeCliente[0].getPrezzoTotale()); // �
		// quella
		// che
		// ho
		// inserito
		// io
	}

	@Test
	public void testEquals() throws IllegalUserRegistrationException {
		Cliente clienteForEquals = new Cliente("BRTGNN96T21B296N", "testUserEq", "ciao123", "Mario", "Rossi", "Verona",
				"000000000000", null);
		Cliente clienteForEquals2 = new Cliente("BRTGNN96T21B296N", "testUserEq", "oqwuw123", "Mario2", "3Rossi",
				"Verona", "000000000000", null);
		Assert.assertTrue(clienteForEquals.equals(clienteForEquals2));
		Assert.assertTrue(clienteForEquals.hashCode() == clienteForEquals2.hashCode());
		Assert.assertFalse(clienteForEquals.hashCode() == clienteOnTesting.hashCode());
	}

	@Test
	public void testDiscounts() {
		Vendita vendita = new Vendita(clienteOnTesting, new Prodotto[] { TestData.getGenericCd() },
				new Integer[] { 20 }, 250, new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		clienteOnTesting.addVendita(vendita);
		clienteOnTesting.addVendita(vendita);
		clienteOnTesting.addVendita(vendita);
		Assert.assertFalse(clienteOnTesting.canHaveDiscounts());

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -13); // tredici mesi fa
		Date aYearAgo = c.getTime();

		vendita = new Vendita(clienteOnTesting, new Prodotto[] { TestData.getGenericCd() }, new Integer[] { 20 }, 251,
				aYearAgo, "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		clienteOnTesting.addVendita(vendita);
		clienteOnTesting.addVendita(vendita);
		clienteOnTesting.addVendita(vendita);
		Assert.assertFalse(clienteOnTesting.canHaveDiscounts());

		vendita = new Vendita(clienteOnTesting, new Prodotto[] { TestData.getGenericCd() }, new Integer[] { 20 }, 251,
				aYearAgo, "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		clienteOnTesting.addVendita(vendita);
		clienteOnTesting.addVendita(vendita);
		vendita = new Vendita(clienteOnTesting, new Prodotto[] { TestData.getGenericCd() }, new Integer[] { 20 }, 251,
				new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		clienteOnTesting.addVendita(vendita);
		Assert.assertFalse(clienteOnTesting.canHaveDiscounts());

		vendita = new Vendita(clienteOnTesting, new Prodotto[] { TestData.getGenericCd() }, new Integer[] { 20 }, 251,
				new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
		clienteOnTesting.addVendita(vendita);
		clienteOnTesting.addVendita(vendita);
		clienteOnTesting.addVendita(vendita);
		Assert.assertTrue(clienteOnTesting.canHaveDiscounts());

	}

	@Test
	public void testClientePreferredGen() {
		Vendita genericVendita = TestData.getGenericVendita();

	}

}
