package it.RGB.is.Classes;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import it.RGB.is.GUI.GUIMain;

public class AAAMain {
	public static void main(String[] args) {

		// Data initializing
		initializeStore();

		// GUI initializing
		SwingUtilities.invokeLater(new GUIMain());

	}

	private static void initializeStore() {

		Catalogo.initialize();
		BancaUtenti.initialize();
		Cart.initialize();
		BancaVendite.initialize();

		// data structures

		// data testing
		Artista steveLukather = new Artista("Steve Lukather", Genere.ROCK, "Steve Lukather", new Date(),
				new Strumento[] { new Strumento("Chitarra"), new Strumento("Voce"), new Strumento("Basso") });
		Artista davidPaich = new Artista("David Paich", Genere.ROCK, "David Paich", new Date(),
				new Strumento[] { new Strumento("Pianoforte"), new Strumento("Organo"), new Strumento("Contrabasso") });
		Artista stevePorcaro = new Artista("Steve Porcaro", Genere.ROCK, "Steve Porcaro", new Date(),
				new Strumento[] { new Strumento("Sintetizzatore") });
		Artista katyPerry = new Artista("Katy Perry", Genere.POP, "Katheryn Elizabeth Hudson", new Date(),
				new Strumento[] { new Strumento("Voce") });

		Prodotto totoXIV = new Prodotto(false, "Toto XIV",
				new String[] { "Running out of time", "Burn", "Holy war", "21st Century Blues", "Orphan",
						"Unknown Soldier", "The Little Things", "Chinatown", "All the Tears that shine", "Fortune",
						"Great Expectations" },
				new ImageIcon[] { new ImageIcon(AAAMain.class.getResource("/resources/covers/totoXIVcover.jpg")) },
				new Float(12.23), new Band("Toto", Genere.ROCK, new Artista[] {}), "Gran bel album", Genere.ROCK,
				new Artista[] { steveLukather, davidPaich, stevePorcaro }, 100);
		Prodotto totoXIV2 = new Prodotto(false, "Toto XIV (Cofanetto con vinili)",
				new String[] { "Running out of time", "Burn", "Holy war", "21st Century Blues", "Orphan",
						"Unknown Soldier", "The Little Things", "Chinatown", "All the Tears that shine", "Fortune",
						"Great Expectations" },
				new ImageIcon[] { new ImageIcon(AAAMain.class.getResource("/resources/covers/totoXIVcover.jpg")) }, 125,
				new Band("Toto", Genere.ROCK, new Artista[] {}), "Gran bel album", Genere.ROCK,
				new Artista[] { steveLukather, davidPaich, stevePorcaro }, 100);
		Prodotto prism = new Prodotto(true, "Prism",
				new String[] { "Roar", "Legendary Lovers", "Birthday", "Unconditionally (feat. Juicy J)",
						"This Is How We Do","International Smile", "Ghost", "Love Me", "This Moment",
						"Double Rainbow", "By The Grace Of God" },
				new ImageIcon[] { new ImageIcon(AAAMain.class.getResource("/resources/covers/prismcover.jpg")) },
				new Band("Katy Perry", Genere.POP, new Artista[]{}), "18 ottobre 2013", Genere.POP,
				new Artista[] { katyPerry}, 100);


		try {
			Cliente edoardo = new Cliente("ASHDGUOASDGOLASGYDA", "EdoGimo96", "password", "Edoardo", "Righi",
					"Bussolengo City", "12312312364", null);
			BancaUtenti.addItem(edoardo);
		} catch (Exception e) {

		}

		Catalogo.addItem(totoXIV);
		Catalogo.addItem(totoXIV2);
		Catalogo.addItem(prism)
	}

	public static void criticalErrorPrintToFile(String message, StackTraceElement[] errors) {

		JOptionPane.showMessageDialog(null, "<html>Errore critico di IO delle strutture dati.<br>" + message
				+ "<br>Vedere file di report per dettagli.", "Errore", JOptionPane.ERROR_MESSAGE);

		String stackTrace = message + "\nSTACK TRACE:\n";

		for (StackTraceElement item : errors) {
			stackTrace += item.toString();
		}

		try {

			// file output
			String fileNameToFormat = "music_store_file//errors//error_dump_";
			String completeFileName = "music_store_file//errors//error_dump_0.txt";
			int counter = 1;
			File file = new File(completeFileName);

			// incremento e cambio il nome del file finch� ne trovo un file
			// uguale

			while (file.exists()) {
				completeFileName = fileNameToFormat + counter + ".txt";
				counter++;
				file = new File(completeFileName);
			}

			// creazione directories
			file.getParentFile().mkdirs();

			PrintWriter outputToFile = new PrintWriter(file, "UTF-8");
			outputToFile.println(new Date() + "\n" + stackTrace);
			outputToFile.close();

		} catch (Exception e) {
		}
	}
}
