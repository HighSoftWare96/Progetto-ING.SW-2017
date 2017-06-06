package it.RGB.is.Classes;

import static org.junit.Assert.*;

import org.junit.Test;

public class CatalogoTest {

	@Test
	public void test() {
		try{
			Catalogo.addItem(null, 123);
			Catalogo.getCatalog();
			Catalogo.searchByID(0);
			Catalogo.searchByID(-12);
			Catalogo.searchByGen(null);
			Catalogo.searchByKey(null,  "");
			Catalogo.searchByPrice(-12, 0);
		}catch (Exception e){
			e.getMessage();
		}
		
		
		
		
		
		
	}

}
