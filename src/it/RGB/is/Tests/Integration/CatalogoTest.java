package it.RGB.is.Tests.Integration;

import static org.junit.Assert.*;

import org.junit.Test;

import it.RGB.is.Classes.Catalogo;
import it.RGB.is.Classes.Genere;
import it.RGB.is.Classes.Prodotto;
import it.RGB.is.Classes.SearchMod;
import it.RGB.is.Exceptions.CatalogoIllegalArgumentException;
import it.RGB.is.Exceptions.LightCatalogoException;
import it.RGB.is.Tests.Unit.TestData;

public class CatalogoTest {

	@Test
	public void CatalogEmpty() {
		TestData.initializeData();
		assertTrue(Catalogo.getCatalog().length == 0);
	}

	@Test(expected = CatalogoIllegalArgumentException.class)
	public void CatalogoAddItemTestNull() {
		TestData.initializeData();
		Catalogo.addItem(null);
	}

	@Test
	public void CatalogoAddItemSimple() {
		TestData.initializeData();
		Catalogo.addItem(TestData.getGenericCd());
		assertTrue(Catalogo.searchByID(TestData.getGenericCd().getID()) == TestData.getGenericCd());
	}

	@Test
	public void CatalogoAddItemWithAmount() {
		TestData.initializeData();
		// aggiungo dieci elementi nel catalogo
		Catalogo.addItem(TestData.getGenericCd(), 10);
		assertTrue(Catalogo.searchByID(TestData.getGenericCd().getID()).getDisp() == 110);
		// altri venti
		Catalogo.addItem(TestData.getGenericCd(), 20);
		assertTrue(Catalogo.searchByID(TestData.getGenericCd().getID()).getDisp() == 130);
	}

	@Test(expected = LightCatalogoException.class)
	public void CatalogoAddItemNegative() {
		TestData.initializeData();
		// aggiungo dieci elementi nel catalogo
		Catalogo.addItem(TestData.getGenericCd(), 0);
		Catalogo.addItem(TestData.getGenericCd(), -1);
	}

	@Test(expected = CatalogoIllegalArgumentException.class)
	public void CatalogoAddItemNull() {
		TestData.initializeData();
		Catalogo.addItem(null, 10);
	}

	@Test
	public void CatalogoRemoveItem() {
		TestData.initializeData();
		Catalogo.addItem(TestData.getGenericCd());

		Catalogo.removeItem(TestData.getGenericCd(), 10);
		assertTrue(Catalogo.getCatalog()[0].getDisp() == 90);
		assertTrue(Catalogo.searchByID(TestData.getGenericCd().getID()).getDisp() == 90);

		Catalogo.removeItem(TestData.getGenericCd(), 88);
		assertTrue(Catalogo.getCatalog()[0].getDisp() == 2);
		assertTrue(Catalogo.searchByID(TestData.getGenericCd().getID()).getDisp() == 2);

		Catalogo.removeItem(TestData.getGenericCd(), 1);
		assertTrue(Catalogo.getCatalog()[0].getDisp() == 1);
		assertTrue(Catalogo.searchByID(TestData.getGenericCd().getID()).getDisp() == 1);
	}

	@Test(expected = LightCatalogoException.class)
	public void CatalogoRemoveItemMoreThanPresent() {
		TestData.initializeData();
		Catalogo.addItem(TestData.getGenericCd());

		Catalogo.removeItem(TestData.getGenericCd(), 150);
	}

	@Test(expected = CatalogoIllegalArgumentException.class)
	public void CatalogoRemoveItemNull() {
		TestData.initializeData();
		Catalogo.removeItem(null, 10);
	}

	@Test(expected = LightCatalogoException.class)
	public void CatalogoRemoveItemNegative() {
		TestData.initializeData();
		Catalogo.removeItem(TestData.getGenericCd(), 0);
		Catalogo.removeItem(TestData.getGenericCd(), -1);
	}

	@Test
	public void CatalogoSearchByID() {
		TestData.initializeData();
		Catalogo.addItem(TestData.getGenericDVD());
		assertTrue(Catalogo.searchByID(TestData.getGenericDVD().getID()) == TestData.getGenericDVD());
	}

	@Test
	public void CatalogoGetCatalog() {
		TestData.initializeData();

		Catalogo.addItem(TestData.getGenericCd());

		assertTrue(Catalogo.getCatalog().length == 1);

		Catalogo.addItem(TestData.getGenericDVD());

		assertTrue(Catalogo.getCatalog().length == 2);

		// o è il CD o è il DVD
		for (Prodotto item : Catalogo.getCatalog()) {
			assertTrue(item.equals(TestData.getGenericCd()) || item.equals(TestData.getGenericDVD()));
		}
	}

	@Test
	public void CatalogoTestSearchKey() {
		TestData.initializeData();
		Catalogo.addItem(TestData.getGenericCd());
		Catalogo.addItem(TestData.getGenericDVD());

		assertTrue(Catalogo.searchByKey(SearchMod.ARTISTA, "Ciao").length == 0);
		assertEquals(2, Catalogo.searchByKey(SearchMod.ARTISTA, "Steve").length);
		assertTrue(Catalogo.searchByKey(SearchMod.BRANI, "Running").length == 2);
		assertTrue(Catalogo.searchByKey(SearchMod.TITOLO, "Toto").length == 2);
		assertTrue(Catalogo.searchByKey(SearchMod.TITOLARE, "Toto").length == 2);
	}

	@Test
	public void CatalogoTestSearchGen() {
		TestData.initializeData();
		Catalogo.addItem(TestData.getGenericCd());
		Catalogo.addItem(TestData.getGenericDVD());

		assertTrue(Catalogo.searchByGen(Genere.FOLK)[0] == TestData.getGenericDVD());
		assertTrue(Catalogo.searchByGen(Genere.ROCK)[0] == TestData.getGenericCd());
		assertTrue(Catalogo.searchByGen(Genere.POP).length == 0);
	}

	@Test
	public void CatalogoTestSearchPrice() {
		TestData.initializeData();
		Catalogo.addItem(TestData.getGenericCd());
		Catalogo.addItem(TestData.getGenericDVD());

		assertTrue(Catalogo.searchByPrice(10, 20)[0] == TestData.getGenericCd());
		assertTrue(Catalogo.searchByPrice(40, 60)[0] == TestData.getGenericDVD());
		assertTrue(Catalogo.searchByPrice(70, 100).length == 0);
	}
}
