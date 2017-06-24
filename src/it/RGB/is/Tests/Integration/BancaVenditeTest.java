/**
 * 
 */
package it.RGB.is.Tests.Integration;

import org.junit.Test;

import it.RGB.is.Classes.BancaVendite;
import it.RGB.is.Exceptions.LightBancaVenditeException;

public class BancaVenditeTest {

	@Test (expected = LightBancaVenditeException.class)
	public void testAddItem() throws LightBancaVenditeException {
		BancaVendite.addItem(null);
	}	
}