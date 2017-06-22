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

	static Artista steveLukather = null;
	static Artista davidPaich = null;
	static Artista stevePorcaro = null;
	static Artista nek = null;
	static Band toto = null;
	static Prodotto genericCD = null;
	static Prodotto genericDVD = null;
	static String genericSongs[] = null;

	public static void initializeData() {
		try {
			steveLukather = new Artista("Steve Lukather", Genere.ROCK, "Steve Lukather", new Date(),
					new Strumento[] { new Strumento("Chitarra"), new Strumento("Voce"), new Strumento("Basso") });

			davidPaich = new Artista("David Paich", Genere.ROCK, "David Paich", new Date(), new Strumento[] {
					new Strumento("Pianoforte"), new Strumento("Organo"), new Strumento("Contrabasso") });
			stevePorcaro = new Artista("Steve Porcaro", Genere.ROCK, "Steve Porcaro", new Date(),
					new Strumento[] { new Strumento("Sintetizzatore") });
			nek = new Artista("Nek", Genere.ROCK, "Filippo Neviani", new Date(),
					new Strumento[] { new Strumento("Voce") });

			toto = new Band("Toto", Genere.ROCK, new Artista[] { steveLukather, davidPaich, stevePorcaro });

			genericCD = new Prodotto(false, "Toto XIV",
					new String[] { "Running out of time", "Burn", "Holy war", "21st Century Blues", "Orphan",
							"Unknown Soldier", "The Little Things", "Chinatown", "All the Tears that shine", "Fortune",
							"Great Expectations" },
					new ImageIcon[] { new ImageIcon(AAAMain.class.getResource("/resources/covers/totoXIVcover.jpg")) },
					new Float(12.23),
					new Band("Toto", Genere.ROCK, new Artista[] { steveLukather, davidPaich, stevePorcaro }),
					"Gran bel album", Genere.ROCK, new Artista[] { steveLukather, davidPaich, stevePorcaro }, 100);

			genericDVD = new Prodotto(true, "Toto XIV",
					new String[] { "Running out of time", "Burn", "Holy war", "21st Century Blues", "Orphan",
							"Unknown Soldier", "The Little Things", "Chinatown", "All the Tears that shine", "Fortune",
							"Great Expectations" },
					new ImageIcon[] { new ImageIcon(AAAMain.class.getResource("/resources/covers/totoXIVcover.jpg")) },
					new Float(12.23),
					new Band("Toto", Genere.ROCK, new Artista[] { steveLukather, davidPaich, stevePorcaro }),
					"Gran bel album", Genere.ROCK, new Artista[] { steveLukather, davidPaich, stevePorcaro }, 100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}