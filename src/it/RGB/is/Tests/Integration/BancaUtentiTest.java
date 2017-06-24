/**
 * 
 */
package it.RGB.is.Tests.Integration;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

import it.RGB.is.Classes.BancaUtenti;
import it.RGB.is.Classes.Cliente;
import it.RGB.is.Exceptions.BancaUtentiIllegalArgumentException;
import it.RGB.is.GUI.Controller;
import it.RGB.is.Tests.Classes.TestData;

/**
 * @author Edoardo
 *
 */
public class BancaUtentiTest {

	private static HashSet<Cliente> strutturaUtenti;
	private static Cliente clienteLoggato = null;
	
	private void newUser(){
		try {
			Cliente user = new Cliente("RGHDRD96D15B296T", "user", "password", "User", "Test",
					"City", "12312312364", null);
			BancaUtenti.addItem(user);
		} catch (Exception e) {
		}
	}

	@Test (expected = BancaUtentiIllegalArgumentException.class)
	public void testAddItem() throws BancaUtentiIllegalArgumentException {
		BancaUtenti.addItem(null);
	}

	@Test
	public void testCheckLogIn() {

		TestData.initializeData();
		newUser();
		assertTrue(BancaUtenti.checkLogIn("user", "password"));
	}

	@Test
	public void testSetLoggedOut() {
		
		TestData.initializeData();
		BancaUtenti.setLoggedOut();
		assertTrue(BancaUtenti.getLoggedInUser() == null);
	}

	@Test
	public void testGetLoggedInUser() {
		
		TestData.initializeData();
		try{
			Controller.setLoggedIn(TestData.getGenericCliente());
		
			assertTrue(BancaUtenti.getLoggedInUser().equals(TestData.getGenericCliente()));
		
		} catch (NullPointerException e){
		}
	}

	@Test
	public void testUserNameExists() {
		
		TestData.initializeData();
		newUser();
		assertTrue(BancaUtenti.userNameExists("user"));
		assertFalse(BancaUtenti.userNameExists("nomeInesistente"));
	}
}