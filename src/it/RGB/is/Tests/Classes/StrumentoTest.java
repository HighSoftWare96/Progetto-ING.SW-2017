package it.RGB.is.Tests.Classes;

import static org.junit.Assert.*;

import org.junit.Test;

import it.RGB.is.Classes.Strumento;
import it.RGB.is.Exceptions.StrumentoIllegalArgument;

public class StrumentoTest {

	private Strumento strumentoOnTesting = new Strumento("Chitarra");

	@Test(expected = StrumentoIllegalArgument.class)
	public void testStrumentoNull() {
		Strumento bo = new Strumento("");
	}

	@Test
	public void genericTestStrumento() {
		assertTrue(strumentoOnTesting.getNomeStrumento().equals("Chitarra"));
		assertTrue(strumentoOnTesting.toString().contains("Chitarra"));
	}

	@Test
	public void testStrumentoEquals() {
		Strumento other = new Strumento("Chitarra");
		assertEquals(strumentoOnTesting, other);
		assertTrue(strumentoOnTesting.hashCode() == other.hashCode());

		other = new Strumento("Violino");
		assertNotEquals(strumentoOnTesting, other);
		assertFalse(strumentoOnTesting.hashCode() == other.hashCode());
	}

}
