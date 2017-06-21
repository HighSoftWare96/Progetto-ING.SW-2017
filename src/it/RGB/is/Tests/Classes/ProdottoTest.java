package it.RGB.is.Tests.Classes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.swing.ImageIcon;

import org.junit.Test;

import it.RGB.is.Classes.ArtistaGenerico;
import it.RGB.is.Classes.Genere;
import it.RGB.is.Classes.Prodotto;
import it.RGB.is.Exceptions.ProdottoIllegalArgumentException;

public class ProdottoTest {

	private Prodotto genericCD = TestData.genericCD;
	private Prodotto genericDVD = TestData.genericDVD;

	@Test
	public void testProdotto() {
		assertTrue(genericCD.getDescrizione().equals("Gran bel album"));
		assertTrue(!genericCD.getTitolo().contains("DVD"));
		assertTrue(genericCD.getTitolo().contains("Toto"));
		assertTrue(genericDVD.getTitolo().contains("DVD"));
		assertTrue(genericCD.getPartecipanti().length > 0);
		assertFalse(genericCD.getPartecipanti().length == 4);
		assertFalse(genericDVD.getSongsTitles().isEmpty());
		assertTrue(genericDVD.getSongsTitles().contains("Running"));
		genericCD.setNewDisp(10);
		assertFalse(genericCD.getDisp() == 90);
		genericCD.setNewDispAdd(10);
		assertFalse(genericCD.getDisp() == 100);
		//assertTrue(genericCD.getID() == 0);
		assertFalse(genericCD.equals(genericDVD));
	}
	

	@Test(expected = ProdottoIllegalArgumentException.class) 
	public void checkProdottoNull() throws ProdottoIllegalArgumentException{
		
		//titolo
		Prodotto prodotto = new Prodotto(false, null, 
				TestData.genericSongs, new ImageIcon [] {TestData.genericCD.getCover()}, (float) 21.43, 
				(ArtistaGenerico) TestData.davidPaich, "descrizione", Genere.CLASSICA, TestData.toto.getComponenti(), 2);
		prodotto.getTitolo();
		
		//titoliPezzi
		prodotto = new Prodotto(false, "a", null,new ImageIcon [] {TestData.genericCD.getCover()}, 
				(float) 21.43, (ArtistaGenerico) TestData.davidPaich,  "descrizione", Genere.CLASSICA, 
				TestData.toto.getComponenti(), 2);
		prodotto.getTitoliPezzi();
		
		//photos
		prodotto = new Prodotto(false, "a", TestData.genericSongs, null, (float) 21.43, (ArtistaGenerico) TestData.davidPaich
				,  "descrizione", Genere.CLASSICA, TestData.toto.getComponenti(), 2);
		prodotto.getPhotos();
		
		//prezzo
		prodotto = new Prodotto(false, "a", TestData.genericSongs, new ImageIcon [] {TestData.genericCD.getCover()}
		, 0, (ArtistaGenerico) TestData.davidPaich,  "descrizione", Genere.CLASSICA, TestData.toto.getComponenti(), 2);
		prodotto.getPrezzo();
		
		//musicistaTitolare
		prodotto = new Prodotto(false, "a", TestData.genericSongs, new ImageIcon [] {TestData.genericCD.getCover()}
		, (float) 21.43, null,  "descrizione", Genere.CLASSICA, TestData.toto.getComponenti(), 2);
		prodotto.getTitolare();
		
		//descrizione
		prodotto = new Prodotto(false, "a", TestData.genericSongs, new ImageIcon [] {TestData.genericCD.getCover()}
		, (float) 21.43, (ArtistaGenerico) TestData.davidPaich, null, Genere.CLASSICA, TestData.toto.getComponenti(), 2);
		prodotto.getDescrizione();
		
		//genere
		prodotto = new Prodotto(false, "a", TestData.genericSongs, new ImageIcon [] {TestData.genericCD.getCover()}
		, (float) 21.43, (ArtistaGenerico) TestData.davidPaich,  "descrizione", null, TestData.toto.getComponenti(), 2);
		prodotto.getGenere();
		
		//partecipanti
		prodotto = new Prodotto(false, "a", TestData.genericSongs, new ImageIcon [] {TestData.genericCD.getCover()}
		, (float) 21.43, (ArtistaGenerico) TestData.davidPaich,  "descrizione", Genere.CLASSICA, null, 2);
		prodotto.getPartecipanti();
		
		//disp
		prodotto = new Prodotto(false, "a", TestData.genericSongs, new ImageIcon [] {TestData.genericCD.getCover()}
		, (float) 21.43, (ArtistaGenerico) TestData.davidPaich,  "descrizione", Genere.CLASSICA, TestData.toto.getComponenti(), 0);
		prodotto.getDisp();
		
	}

}
