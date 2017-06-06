package it.RGB.is.Classes;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProdottoTest {

	@Test
	public void test() {
		Prodotto prodotto = new Prodotto(false, "",null,null,0,null,"",null,null,0);
		try{
			prodotto.getCover();
			prodotto.getSongsTitles();
			prodotto.getSongsTitles();
		}
		catch(Exception e){
			e.getMessage();
		}
		
		prodotto.getDisp();
		prodotto.getDataArrivo();
		prodotto.getDescrizione();
		prodotto.getGenere();
		prodotto.getID();
		prodotto.getPartecipanti();
		prodotto.getPhotos();
		prodotto.getPrezzo();
		prodotto.getPrezzoString();
		prodotto.isDVD();
		prodotto.getTitolo();
		prodotto.getTitoliPezzi();
		prodotto.getTitolare();
		prodotto.setNewDisp(12);
		prodotto.setNewDispAdd(12);
	
		
		
	}

}
