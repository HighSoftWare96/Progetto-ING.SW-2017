package it.RGB.is.Classes;

import static org.junit.Assert.*;

import org.junit.Test;

public class StrumentoTest {

	@Test
	public void test() {
		Strumento bo = new Strumento("");
		bo.getNomeStrumento();
		bo.toString();
	}

}
