/**
 * 
 */
package it.RGB.is.Tests.Integration;

import org.junit.Test;

import it.RGB.is.Classes.BancaVendite;
import it.RGB.is.Exceptions.LightBancaVenditeException;

/**
 * @author Edoardo
 *
 */
public class BancaVenditeTest {

	/**
	 * Test method for
	 * {@link it.RGB.is.Classes.BancaVendite#addItem(it.RGB.is.Classes.Vendita)}.
	 * 
	 * @throws LightBancaVenditeException
	 */
	@Test
	public void testAddItem() throws LightBancaVenditeException {
		BancaVendite.addItem(null);
	}

	/**
	 * Test method for {@link it.RGB.is.Classes.BancaVendite#saveVendite()}.
	 */
	@Test
	public void testSaveVendite() {
		BancaVendite.saveVendite();
	}
}