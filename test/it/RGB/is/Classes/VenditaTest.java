package it.RGB.is.Classes;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class VenditaTest {

	@Test
	public void test() {
		Date date = new Date();
		Vendita vend = new Vendita(null,null, null, 0, date, "", null, null);
		
		try{
			vend.getDate();
			vend.getPagamento();
			vend.getConsegna();
			vend.getProdottiLength();
			vend.getProdottoString(0);
		}catch(Exception e){
			e.getMessage();
		}
		vend.getAmount();
		vend.getProdotti();
		vend.getPrezzoTotale();
		vend.getDateString();
		vend.getIP();
		
		
	}

}
