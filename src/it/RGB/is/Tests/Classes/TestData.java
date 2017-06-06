package it.RGB.is.Tests.Classes;

import java.util.Date;

import javax.swing.ImageIcon;

import it.RGB.is.Classes.AAAMain;
import it.RGB.is.Classes.Artista;
import it.RGB.is.Classes.Band;
import it.RGB.is.Classes.Genere;
import it.RGB.is.Classes.Prodotto;
import it.RGB.is.Classes.Strumento;

public class TestData {

	static Artista steveLukather = new Artista("Steve Lukather", Genere.ROCK, "Steve Lukather", new Date(),
			new Strumento[] { new Strumento("Chitarra"), new Strumento("Voce"), new Strumento("Basso") });
	static Artista davidPaich = new Artista("David Paich", Genere.ROCK, "David Paich", new Date(),
			new Strumento[] { new Strumento("Pianoforte"), new Strumento("Organo"), new Strumento("Contrabasso") });
	static Artista stevePorcaro = new Artista("Steve Porcaro", Genere.ROCK, "Steve Porcaro", new Date(),
			new Strumento[] { new Strumento("Sintetizzatore") });
	static Artista nek = new Artista("Nek", Genere.ROCK, "Filippo Neviani", new Date(),
			new Strumento[] { new Strumento("Voce") });

	static Band toto = new Band("Toto", Genere.ROCK, new Artista[]{steveLukather, davidPaich, stevePorcaro});
	
	static Prodotto genericCD = new Prodotto(false, "Toto XIV",
			new String[] { "Running out of time", "Burn", "Holy war", "21st Century Blues", "Orphan", "Unknown Soldier",
					"The Little Things", "Chinatown", "All the Tears that shine", "Fortune", "Great Expectations" },
			new ImageIcon[] { new ImageIcon(AAAMain.class.getResource("/resources/covers/totoXIVcover.jpg")) },
			new Float(12.23), new Band("Toto", Genere.ROCK, new Artista[] {}), "Gran bel album", Genere.ROCK,
			new Artista[] { steveLukather, davidPaich, stevePorcaro }, 100);
	
	static Prodotto genericDVD = new Prodotto(true, "Toto XIV",
			new String[] { "Running out of time", "Burn", "Holy war", "21st Century Blues", "Orphan", "Unknown Soldier",
					"The Little Things", "Chinatown", "All the Tears that shine", "Fortune", "Great Expectations" },
			new ImageIcon[] { new ImageIcon(AAAMain.class.getResource("/resources/covers/totoXIVcover.jpg")) },
			new Float(12.23), new Band("Toto", Genere.ROCK, new Artista[] {}), "Gran bel album", Genere.ROCK,
			new Artista[] { steveLukather, davidPaich, stevePorcaro }, 100);

}
