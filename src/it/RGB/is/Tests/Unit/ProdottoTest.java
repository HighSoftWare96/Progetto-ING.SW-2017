package it.RGB.is.Tests.Unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.swing.ImageIcon;

import org.junit.Test;

import it.RGB.is.Classes.ArtistaGenerico;
import it.RGB.is.Classes.BancaUtenti;
import it.RGB.is.Classes.Catalogo;
import it.RGB.is.Classes.Genere;
import it.RGB.is.Classes.Prodotto;
import it.RGB.is.Exceptions.ProdottoIllegalArgumentException;

public class ProdottoTest {

	private Prodotto genericCD = null;
	private Prodotto genericDVD = null;

	public ProdottoTest() {
		TestData.initializeData();
	}
	
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
		assertTrue(genericDVD.getSongsTitles().toString().contains("Running"));
		assertTrue(genericCD.getDataArrivo().equals(genericDVD.getDataArrivo()));
		assertFalse(genericCD.getID() == genericDVD.getID());
		// controllo disponibilità
		genericCD.removeDisp(10);
		assertTrue(genericCD.getDisp() == 90); 
		assertFalse(genericCD.getPrezzoString().equals(genericDVD.getPrezzoString()));
		assertTrue((float) genericCD.getPrezzo() == ((float) 12.23));
		assertTrue(genericCD.getTitolare().toString().equals(genericDVD.getTitolare().toString()));
		assertTrue(genericCD.getGenere().equals(Genere.ROCK)); 
		assertFalse(genericDVD.getGenere().equals(Genere.JAZZ));
		//manca controllo sulle foto e sui pezzi
		
		genericCD.addDisp(10);
		assertTrue(genericCD.getDisp() == 100);
		
		assertFalse(genericCD.equals(genericDVD));
	} 

	@Test(expected = ProdottoIllegalArgumentException.class)
	public void checkProdottoTitoloNull() throws ProdottoIllegalArgumentException {

		// titolo
		TestData.initializeData();
		Catalogo.initialize();
		BancaUtenti.initialize();
		genericCD = new Prodotto(false, null, TestData.getGenericSongs(),
				new ImageIcon[] { TestData.getGenericCd().getCover() }, (float) 21.43, (ArtistaGenerico) TestData.getArtist2(),
				"descrizione", Genere.CLASSICA, TestData.getBand().getComponenti(), 2);
	}
	
	@Test(expected = ProdottoIllegalArgumentException.class)
	public void checkProdottoPezziNull() throws ProdottoIllegalArgumentException {

		// titoliPezzi
		TestData.initializeData();
		Catalogo.initialize();
		BancaUtenti.initialize();
		genericCD = new Prodotto(false, "Titolo", TestData.getGenericSongsEmpty(), new ImageIcon[] { TestData.getGenericCd().getCover() }, (float) 21.43,
				(ArtistaGenerico) TestData.getArtist2(), "descrizione", Genere.CLASSICA, TestData.getBand().getComponenti(),
				2);
	}
	
	@Test(expected = ProdottoIllegalArgumentException.class)
	public void checkProdottoPhotosNull() throws ProdottoIllegalArgumentException {

		// photos
		TestData.initializeData();
		Catalogo.initialize();
		BancaUtenti.initialize();
		genericCD = new Prodotto(false, "Titolo", TestData.getGenericSongs(), null, (float) 21.43,
				(ArtistaGenerico) TestData.getArtist2(), "descrizione", Genere.CLASSICA, TestData.getBand().getComponenti(),
				2);
	}
	
	@Test(expected = ProdottoIllegalArgumentException.class)
	public void checkProdottoPrezzoNull() throws ProdottoIllegalArgumentException {

		// prezzo
		TestData.initializeData();
		Catalogo.initialize();
		BancaUtenti.initialize();
		genericCD = new Prodotto(false, "a", TestData.getGenericSongs(), new ImageIcon[] { TestData.getGenericCd().getCover() }, 0,
				(ArtistaGenerico) TestData.getArtist2(), "descrizione", Genere.CLASSICA, TestData.getBand().getComponenti(),
				2);
	}
	
	@Test(expected = ProdottoIllegalArgumentException.class)
	public void checkProdottoTitolareNull() throws ProdottoIllegalArgumentException {

		// musicistaTitolare
		TestData.initializeData();
		Catalogo.initialize();
		BancaUtenti.initialize();
		genericCD = new Prodotto(false, "a", TestData.getGenericSongs(), new ImageIcon[] { TestData.getGenericCd().getCover() },
				(float) 21.43, null, "descrizione", Genere.CLASSICA, TestData.getBand().getComponenti(), 2);
	}
	
	@Test(expected = ProdottoIllegalArgumentException.class)
	public void checkProdottoGenereNull() throws ProdottoIllegalArgumentException {

		// genere
		TestData.initializeData();
		Catalogo.initialize();
		BancaUtenti.initialize();
		genericCD = new Prodotto(false, "a", TestData.getGenericSongs(), new ImageIcon[] { TestData.getGenericCd().getCover() },
				(float) 21.43, (ArtistaGenerico) TestData.getArtist2(), "descrizione", null,
				TestData.getBand().getComponenti(), 2);
	}
	
	@Test(expected = ProdottoIllegalArgumentException.class)
	public void checkProdottoPartecipantiNull() throws ProdottoIllegalArgumentException {

		// partecipanti
		TestData.initializeData();
		Catalogo.initialize();
		BancaUtenti.initialize();
		genericCD = new Prodotto(false, "a", TestData.getGenericSongs(), new ImageIcon[] { TestData.getGenericCd().getCover() },
				(float) 21.43, (ArtistaGenerico) TestData.getArtist2(), "descrizione", Genere.CLASSICA, TestData.getNoPartecipanti(), 2);
	}
	
	@Test(expected = ProdottoIllegalArgumentException.class)
	public void checkProdottoDispNull() throws ProdottoIllegalArgumentException {
		
		// disp
		TestData.initializeData();
		Catalogo.initialize();
		BancaUtenti.initialize();
		genericCD = new Prodotto(false, "a", TestData.getGenericSongs(), new ImageIcon[] { TestData.getGenericCd().getCover() },
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