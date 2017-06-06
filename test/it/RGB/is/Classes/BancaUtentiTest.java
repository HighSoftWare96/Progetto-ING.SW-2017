/**
 * 
 */
package it.RGB.is.Classes;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

/**
 * @author Edoardo
 *
 */
public class BancaUtentiTest {

	private static HashSet<Cliente> strutturaUtenti;
	private static Cliente clienteLoggato = null;

	
	/**
	 * Test method for {@link it.RGB.is.Classes.BancaUtenti#addItem(it.RGB.is.Classes.Cliente)}.
	 */
	@Test
	public void testAddItem() {
		BancaUtenti.addItem(null);
	}

	/**
	 * Test method for {@link it.RGB.is.Classes.BancaUtenti#checkLogIn(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testCheckLogIn() {

		BancaUtenti.checkLogIn("", "");
	}

	/**
	 * Test method for {@link it.RGB.is.Classes.BancaUtenti#setLoggedOut()}.
	 */
	@Test
	public void testSetLoggedOut() {
		BancaUtenti.setLoggedOut();
	}

	/**
	 * Test method for {@link it.RGB.is.Classes.BancaUtenti#getLoggedInUser()}.
	 */
	@Test
	public void testGetLoggedInUser() {
		
	}

	/**
	 * Test method for {@link it.RGB.is.Classes.BancaUtenti#userNameExists(java.lang.String)}.
	 */
	@Test
	public void testUserNameExists() {
		BancaUtenti.userNameExists("");
	}

	/**
	 * Test method for {@link it.RGB.is.Classes.BancaUtenti#saveUtenti()}.
	 */
	@Test
	public void testSaveUtenti() {
		BancaUtenti.saveUtenti();
	}

	/**
	 * Test method for {@link java.lang.Object#Object()}.
	 */
	@Test
	public void testObject() {

	}

	/**
	 * Test method for {@link java.lang.Object#getClass()}.
	 */
	@Test
	public void testGetClass() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
	 */
	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#clone()}.
	 */
	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	public void testToString() {
		BancaUtenti banca= new BancaUtenti();
		banca.toString();
		}

	/**
	 * Test method for {@link java.lang.Object#notify()}.
	 */
	@Test
	public void testNotify() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#notifyAll()}.
	 */
	@Test
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#wait(long)}.
	 */
	@Test
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#wait(long, int)}.
	 */
	@Test
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#wait()}.
	 */
	@Test
	public void testWait() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#finalize()}.
	 */
	@Test
	public void testFinalize() {
		fail("Not yet implemented");
	}

}
