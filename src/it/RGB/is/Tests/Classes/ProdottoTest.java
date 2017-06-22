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

	private Prodotto genericCD = null;
	private Prodotto genericDVD = null;

	@Test
	public void testProdotto() {
		genericCD = TestData.getGenericCd();		
		genericDVD = TestData.getGenericDVD();		

		assertTrue(genericCD.getDescrizione().equals("Gran bel album"));
		assertTrue(!genericCD.getTitolo().contains("DVD"));
		assertTrue(genericCD.getTitolo().contains("Toto"));
		assertTrue(genericDVD.getTitolo().contains("DVD"));
		assertTrue(genericCD.getPartecipanti().length > 0);
		assertFalse(genericCD.getPartecipanti().length == 4);
		assertFalse(genericDVD.getSongsTitles().isEmpty());
		assertTrue(genericDVD.getSongsTitles().contains("Running"));
		assertTrue(genericCD.getDataArrivo().equals(genericDVD.getDataArrivo()));
		assertFalse(genericCD.getID() == genericDVD.getID());
		genericCD.setNewDisp(10);
		assertTrue(genericCD.getDisp() == 90);
		assertTrue(genericCD.getPrezzoString().equals(genericDVD.getPrezzoString()));
		assertTrue((float) genericCD.getPrezzo() == ((float) 12.23));
		assertTrue(genericCD.getTitolare().toString().equals(genericDVD.getTitolare().toString()));
		assertTrue(genericCD.getGenere().equals(Genere.ROCK));
		assertFalse(genericDVD.getGenere().equals(Genere.JAZZ));
		//manca controllo sulle foto e sui pezzi
		
		genericCD.setNewDispAdd(10);
		assertTrue(genericCD.getDisp() == 100);
		
		assertFalse(genericCD.equals(genericDVD));
	}

	@Test(expected = ProdottoIllegalArgumentException.class)
	public void checkProdottoNull() throws ProdottoIllegalArgumentException {

		// titolo
		Prodotto prodotto = new Prodotto(false, null, TestData.getGenericSongs(),
				new ImageIcon[] { TestData.getGenericCd().getCover() }, (float) 21.43, (ArtistaGenerico) TestData.getArtist2(),
				"descrizione", Genere.CLASSICA, TestData.getBand().getComponenti(), 2);

		// titoliPezzi
		prodotto = new Prodotto(false, "a", null, new ImageIcon[] { TestData.getGenericCd().getCover() }, (float) 21.43,
				(ArtistaGenerico) TestData.getArtist2(), "descrizione", Genere.CLASSICA, TestData.getBand().getComponenti(),
				2);

		// photos
		prodotto = new Prodotto(false, "a", TestData.getGenericSongs(), null, (float) 21.43,
				(ArtistaGenerico) TestData.getArtist2(), "descrizione", Genere.CLASSICA, TestData.getBand().getComponenti(),
				2);

		// prezzo
		prodotto = new Prodotto(false, "a", TestData.getGenericSongs(), new ImageIcon[] { TestData.getGenericCd().getCover() }, 0,
				(ArtistaGenerico) TestData.getArtist2(), "descrizione", Genere.CLASSICA, TestData.getBand().getComponenti(),
				2);

		// musicistaTitolare
		prodotto = new Prodotto(false, "a", TestData.getGenericSongs(), new ImageIcon[] { TestData.getGenericCd().getCover() },
				(float) 21.43, null, "descrizione", Genere.CLASSICA, TestData.getBand().getComponenti(), 2);

		// descrizione
		prodotto = new Prodotto(false, "a", TestData.getGenericSongs(), new ImageIcon[] { TestData.getGenericCd().getCover() },
				(float) 21.43, (ArtistaGenerico) TestData.getArtist2(), null, Genere.CLASSICA,
				TestData.getBand().getComponenti(), 2);

		// genere
		prodotto = new Prodotto(false, "a", TestData.getGenericSongs(), new ImageIcon[] { TestData.getGenericCd().getCover() },
				(float) 21.43, (ArtistaGenerico) TestData.getArtist2(), "descrizione", null,
				TestData.getBand().getComponenti(), 2);

		// partecipanti
		prodotto = new Prodotto(false, "a", TestData.getGenericSongs(), new ImageIcon[] { TestData.getGenericCd().getCover() },
				(float) 21.43, (ArtistaGenerico) TestData.getArtist2(), "descrizione", Genere.CLASSICA, null, 2);

		// disp
		prodotto = new Prodotto(false, "a", TestData.getGenericSongs(), new ImageIcon[] { TestData.getGenericCd().getCover() },
				(float) 21.43, (ArtistaGenerico) TestData.getArtist2(), "descrizione", Genere.CLASSICA,
				TestData.getBand().getComponenti(), 0);

	}
	
	@Test
	public void testHashCode(){
		genericCD = TestData.getGenericCd();
		genericDVD = TestData.getGenericDVD();
		Prodotto genericCD2 = TestData.getGenericCd();
		
		assertTrue(genericCD.hashCode() == genericCD2.hashCode());
		assertFalse(genericCD.hashCode() == genericDVD.hashCode());
	}

}
