package it.RGB.is.Classes;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Test;

public class ArtistaTest {

	@Test
	public void testArtista() {
		Artista artista2 = new Artista("bibi", Genere.CLASSICA, "mario barconi", new Date(), new Strumento[] {new Strumento("basso alto")});
		
		assertNotNull(artista2);
		

		assertEquals("bibi", artista2.getNomeArte());
		assertEquals("mario barconi", artista2.getNomeBattesimo());
		assertEquals(new Strumento("basso alto"), artista2.getStrumento()[0]);
		
		assertNotNull(artista2.getDataNascita());
		
	}

}
