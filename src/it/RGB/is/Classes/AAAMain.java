package it.RGB.is.Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

		Catalogo catalogo = null;
		BancaUtenti bancaUtenti = null;
		Cart carrello = null;
		BancaVendite vendite = null;

		// data structures
		try {
			catalogo = new Catalogo();
			bancaUtenti = new BancaUtenti();
			carrello = new Cart();
			vendite = new BancaVendite();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Errore critico nell'inizializzazione delle strutture dati. Vedere file di report per dettagli.",
					"Errore", JOptionPane.ERROR_MESSAGE);
			// TODO: rivedere
			printErrToFile(e.getStackTrace().toString());
			System.exit(-1);
		}

		// data testing
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

		Cliente edoardo = new Cliente("ASHDGUOASDGOLASGYDA", "EdoGimo96", "password", "Edoardo", "Righi",
				"Bussolengo City", "12312312364");

		BancaUtenti.addItem(edoardo);

		Catalogo.addItem(totoXIV);
		Catalogo.addItem(totoXIV2);
	}

	public static void printErrToFile(String stackTrace) {
		try {

			//directory file output
			String filePath = "music_store_file//errors/";
			File directory = new File(filePath);
			if (! directory.exists()) {
				directory.mkdir();
			}

			//file output
			String fileNameToFormat = "error_dump_";
			String completeFileName = "error_dump_1.txt";
			int counter = 2;
			File file = new File(completeFileName);


			// incremento e cambio il nome del file finch� ne trovo un file
			// uguale
			while (file.exists()) {
				completeFileName = fileNameToFormat + counter + ".txt";
				counter++;
				file = new File(completeFileName);
			}

			PrintWriter outputToFile = new PrintWriter("music_store_file//errors//" + completeFileName, "UTF-8");
			outputToFile.println(stackTrace);
			outputToFile.close();
		} catch (Exception e) {
		}
	}
}
