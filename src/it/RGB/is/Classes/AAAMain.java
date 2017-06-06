package it.RGB.is.Classes;

import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import it.RGB.is.GUI.GUIMain;

public class AAAMain {
	public static void main(String[] args) {

		Catalogo catalogo = new Catalogo();

		Artista steveLukather = new Artista("Steve Lukather", Genere.ROCK, "Steve Lukather", new Date(),
				new Strumento[] { new Strumento("Chitarra"), new Strumento("Voce"), new Strumento("Basso") });
		Artista davidPaich = new Artista("David Paich", Genere.ROCK, "David Paich", new Date(),
				new Strumento[] { new Strumento("Pianoforte"), new Strumento("Organo"), new Strumento("Contrabasso") });
		Artista stevePorcaro = new Artista("Steve Porcaro", Genere.ROCK, "Steve Porcaro", new Date(),
				new Strumento[] { new Strumento("Sintetizzatore") });

		Prodotto totoXIV = new Prodotto(false, "Toto XIV",
				new String[] { "Running out of time", "Burn", "Holy war", "21st Century Blues", "Orphan",
						"Unknown Soldier", "The Little Things", "Chinatown", "All the Tears that shine", "Fortune",
						"Great Expectations" },
				new ImageIcon[] { new ImageIcon(AAAMain.class.getResource("/resources/covers/totoXIVcover.jpg")) }, new Float(12.23),
				new Band("Toto", Genere.ROCK, new Artista[] {}), "Gran bel album", Genere.ROCK,
				new Artista[] { steveLukather, davidPaich, stevePorcaro }, 100);
		Prodotto totoXIV2 = new Prodotto(false, "Toto XIV (Cofanetto con vinili)",
				new String[] { "Running out of time", "Burn", "Holy war", "21st Century Blues", "Orphan",
						"Unknown Soldier", "The Little Things", "Chinatown", "All the Tears that shine", "Fortune",
						"Great Expectations" },
				new ImageIcon[] { new ImageIcon(AAAMain.class.getResource("/resources/covers/totoXIVcover.jpg")) }, 125, new Band("Toto", Genere.ROCK, new Artista[] {}),
				"Gran bel album", Genere.ROCK, new Artista[] { steveLukather, davidPaich, stevePorcaro }, 100);

		BancaUtenti bancaUtenti = new BancaUtenti();

		Cliente edoardo = new Cliente("ASHDGUOASDGOLASGYDA", "EdoGimo96", "password", "Edoardo", "Righi",
				"Bussolengo City", "12312312364");
		BancaUtenti.addItem(edoardo);

		catalogo.addItem(totoXIV);
		catalogo.addItem(totoXIV2);

		Cart carrello = new Cart();

		BancaVendite vendite = new BancaVendite();

		SwingUtilities.invokeLater(new GUIMain());

	}
}
