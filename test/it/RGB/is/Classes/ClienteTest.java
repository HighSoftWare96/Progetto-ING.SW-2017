package it.RGB.is.Classes;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClienteTest {

	@Test
	public void test() {
		Cliente cliente= new Cliente("", "", "", "", "", "", "");
		cliente.getCell();
		
		cliente.calculateGeneriPref();
		cliente.equals(null);
		cliente.getVendite();
		
		
		//??????????????????????
		Vendita vendita = new Vendita(null, null, null, 0, null, "", null, null);
		cliente.addVendita(vendita);
		cliente.getVendite().toString();
		cliente.toString();
		
	}

}
