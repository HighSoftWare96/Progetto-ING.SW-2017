package it.RGB.is.Tests.Classes;

import org.junit.Assert;
import org.junit.Test;

import it.RGB.is.Classes.Artista;
import it.RGB.is.Classes.ArtistaGenerico;
import it.RGB.is.Classes.Band;
import it.RGB.is.Classes.Genere;

public class ArtistaTest {
	
	private ArtistaGenerico absArtista = TestData.steveLukather;
	private ArtistaGenerico absArtistaWithArtName = TestData.nek;
	private ArtistaGenerico absBand = TestData.toto;
	
	
	@Test
	public void testArtista() {
		
		Assert.assertTrue(absArtista.toString().contains("Chitarra"));
		Assert.assertTrue(absArtista.toString().contains("Steve Lukather"));
		Assert.assertTrue(absArtista.toString().contains("Basso"));
		Assert.assertTrue(absArtista.toString().contains("Voce"));
		
		Assert.assertTrue(absArtistaWithArtName.toString().contains("Nek"));
		Assert.assertTrue(absArtistaWithArtName.toString().contains("Filippo Neviani"));
		Assert.assertTrue(absArtistaWithArtName.toString().contains("Voce"));
		Assert.assertEquals("Nek", absArtistaWithArtName.getNomeArte());
		
		Artista artistaWithArtName = (Artista) absArtistaWithArtName;
		Assert.assertEquals("Filippo Neviani", artistaWithArtName.getNomeBattesimo());
		Assert.assertTrue(artistaWithArtName.getStrumento().length > 0);
		
		Band band = (Band) absBand;
		Assert.assertTrue(band.getComponenti().length > 0);
		Assert.assertEquals(absArtista, band.getComponenti()[0]);
		Assert.assertEquals(Genere.ROCK, band.getGenerePrincipale());
		
		Assert.assertTrue(absBand.toString().contains("Toto"));
		Assert.assertTrue(absBand.toString().contains("Steve Lukather"));
		Assert.assertTrue(absBand.toString().contains("David Paich"));
	}

}
