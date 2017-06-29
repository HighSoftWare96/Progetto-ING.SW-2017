package it.RGB.is.Tests.Unit;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import it.RGB.is.Classes.Artista;
import it.RGB.is.Classes.ArtistaGenerico;
import it.RGB.is.Classes.Band;
import it.RGB.is.Classes.Genere;
import it.RGB.is.Classes.Strumento;
import it.RGB.is.Exceptions.ArtistIllegalArgumentException;

public class ArtistaTest {

	private ArtistaGenerico absArtista = null;
	private ArtistaGenerico absArtistaWithArtName = null;
	private ArtistaGenerico absBand = null;

	public ArtistaTest() {
	}

	// test error handling costruttore di ArtistaGenerico (null)
	@Test(expected = ArtistIllegalArgumentException.class)
	public void testArtistaGenericoNull() throws ArtistIllegalArgumentException {
		
		new Artista(null, null, "Test", new Date(), new Strumento[] { new Strumento("Chitarra") });
	}
 
	// test error handling costruttore di Artista
	@Test(expected = ArtistIllegalArgumentException.class)
	public void testArtistaNull() throws ArtistIllegalArgumentException {

		new Artista("Test", Genere.ROCK, null, null, null);
	}

	// test error handling costruttore di Artista (empty)
	@Test(expected = ArtistIllegalArgumentException.class)
	public void testArtistaEmpty() throws ArtistIllegalArgumentException {

		new Artista("Test", Genere.ROCK, null, null, new Strumento[] {});
	}

	// test error handling costruttore di Band (null)
	@Test(expected = ArtistIllegalArgumentException.class)
	public void testBandNull() throws ArtistIllegalArgumentException {

		new Band("Test", Genere.ROCK, null);
	}

	// test error handling costruttore di Band (empty)
	@Test(expected = ArtistIllegalArgumentException.class)
	public void testBandEmpty() throws ArtistIllegalArgumentException {

		new Artista("Test", Genere.ROCK, null, null, new Strumento[] {});
	}

	@Test
	public void testArtistaSemplice() {
		TestData.initializeData();
		absArtista = TestData.getArtist1();
		absArtistaWithArtName = TestData.getArtistArtName();
		absBand = TestData.getBand();
		assertTrue(absArtista.getGenerePrincipale().equals(Genere.ROCK));
		// stesso nome arte e nascita un solo nome stampato
		assertTrue(absArtista.getNomeArte().equalsIgnoreCase("Steve Lukather"));
		assertTrue(absArtista.toString().contains("Chitarra"));
		assertTrue(absArtista.toString().contains("Steve Lukather"));
	}

	@Test
	public void testArtistaConSecondoNome() {
		TestData.initializeData();
		absArtista = TestData.getArtist1();
		absArtistaWithArtName = TestData.getArtistArtName();
		absBand = TestData.getBand();
		assertTrue(absArtistaWithArtName.toString().contains("Nek"));
		assertTrue(absArtistaWithArtName.toString().contains("Filippo"));

	}

	@Test
	public void testBand() {
		TestData.initializeData();
		absArtista = TestData.getArtist1();
		absArtistaWithArtName = TestData.getArtistArtName();
		absBand = TestData.getBand();
		assertTrue(absBand.getGenerePrincipale().equals(Genere.ROCK));
		assertTrue(absBand.toString().contains("Steve Lukather"));
		assertTrue(absBand.toString().contains("Steve Lukather"));
	}

}
