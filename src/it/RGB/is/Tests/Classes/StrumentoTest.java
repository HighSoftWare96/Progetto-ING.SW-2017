package it.RGB.is.Tests.Classes;

import org.junit.Test;

import it.RGB.is.Classes.Strumento;

public class StrumentoTest {

	@Test
	public void test() {
		Strumento bo = new Strumento("");
		bo.getNomeStrumento();
		bo.toString();
	}

}
