/**
 * 
 */
package it.RGB.is.Tests.Classes;

import org.junit.Test;

import it.RGB.is.Classes.Band;

/**
 * @author Edoardo
 *
 */
public class BandTest {
	/**
	 * Test method for {@link it.RGB.is.Classes.Band#Band(java.lang.String, it.RGB.is.Classes.Genere, it.RGB.is.Classes.Artista[])}.
	 */
	@Test
	public void testBand() {
		Band band= new Band("", null, null);
		try{
			band.toString();
		}catch (Exception e){
			e.getMessage();
		}
		band.getComponenti();
	}

}
