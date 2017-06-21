package it.RGB.is.Tests.Classes;

import java.util.Date;

import org.junit.Test;

import it.RGB.is.Classes.Artista;
import it.RGB.is.Classes.ArtistaGenerico;
import it.RGB.is.Classes.Band;
import it.RGB.is.Classes.Genere;
import it.RGB.is.Classes.Strumento;
import it.RGB.is.Exceptions.ArtistIllegalArgumentException;

public class ArtistaTest {

	private ArtistaGenerico absArtista = TestData.steveLukather;
	private ArtistaGenerico absArtistaWithArtName = TestData.nek;
	private ArtistaGenerico absBand = TestData.toto;

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
		
	}

}
