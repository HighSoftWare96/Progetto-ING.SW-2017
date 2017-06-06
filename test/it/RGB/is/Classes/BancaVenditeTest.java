/**
 * 
 */
package it.RGB.is.Classes;

import org.junit.Test;

/**
 * @author Edoardo
 *
 */
public class BancaVenditeTest {

	/**
	 * Test method for {@link it.RGB.is.Classes.BancaVendite#addItem(it.RGB.is.Classes.Vendita)}.
	 */
	@Test
	public void testAddItem() {
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