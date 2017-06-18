package it.RGB.is.Classes;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import it.RGB.is.GUI.GUIMain;

public class AAAMain {

	private static boolean insertOldData = false;

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
		if (insertOldData) {

			// data testing
			Artista steveLukather = new Artista("Steve Lukather", Genere.ROCK, "Steve Lukather", new Date(),
					new Strumento[] { new Strumento("Chitarra"), new Strumento("Voce"), new Strumento("Basso") });
			Artista davidPaich = new Artista("David Paich", Genere.ROCK, "David Paich", new Date(), new Strumento[] {
					new Strumento("Pianoforte"), new Strumento("Organo"), new Strumento("Contrabasso") });
			Artista stevePorcaro = new Artista("Steve Porcaro", Genere.ROCK, "Steve Porcaro", new Date(),
					new Strumento[] { new Strumento("Sintetizzatore") });

			Prodotto totoXIV = new Prodotto(false, "Toto XIV",
					new String[] { "Running out of time", "Burn", "Holy war", "21st Century Blues", "Orphan",
							"Unknown Soldier", "The Little Things", "Chinatown", "All the Tears that shine", "Fortune",
							"Great Expectations" },
					new ImageIcon[] { new ImageIcon(AAAMain.class.getResource("/resources/covers/totoXIVcover.jpg")) },
					new Float(12.23),
					new Band("Toto", Genere.ROCK, new Artista[] { steveLukather, davidPaich, stevePorcaro }),
					"Gran bel album", Genere.ROCK, new Artista[] { steveLukather, davidPaich, stevePorcaro }, 100);

			Artista katyPerry = new Artista("Katy Perry", Genere.POP, "Katheryn Elizabeth Hudson", new Date(),
					new Strumento[] { new Strumento("Voce") });

			Prodotto prism = new Prodotto(false, "Prism",
					new String[] { "Roar", "Legendary Lovers", "Birthday", "Unconditionally (feat. Juicy J)",
							"This Is How We Do", "International Smile", "Ghost", "Love Me", "This Moment",
							"Double Rainbow", "By The Grace Of God" },
					new ImageIcon[] { new ImageIcon(AAAMain.class.getResource("/resources/covers/prismcover.jpg")) },
					new Float(6.99), katyPerry, "18 ottobre 2013", Genere.POP, new Artista[] { katyPerry }, 100);

			Artista chrisMartin = new Artista("Chris Martin", Genere.POP, "Christopher Anthony John Martin ",
					new Date(), new Strumento[] { new Strumento("Voce"), new Strumento("Pianoforte") });
			Artista jonnyBuckland = new Artista("Jonny Buckland", Genere.POP, "Jonathan Mark Buckland", new Date(),
					new Strumento[] { new Strumento("Chitarra") });
			Artista guyBerryman = new Artista("Guy Berryman", Genere.POP, "Guy Rupert Berryman", new Date(),
					new Strumento[] { new Strumento("Basso") });
			Artista willChampion = new Artista("Will Champion", Genere.POP, "William Champion", new Date(),
					new Strumento[] { new Strumento("Batteria"), new Strumento("Voce") });

			Prodotto ahfodColdPlay = new Prodotto(false, "A Head Full of Dreams",
					new String[] { "A Head Full of Dreams", "Birds", "Hymn for the Weekend", "Everglow",
							"Adventure of a Lifetime", "Fun", "Kaleidoscope", "Army of One", "Amazing Day",
							"Colour Spectrum", "Up&Up" },
					new ImageIcon[] { new ImageIcon(AAAMain.class.getResource("/resources/covers/ahfodcover.jpg")),
							new ImageIcon(AAAMain.class.getResource("/resources/covers/ahfodcover_back.jpg")) },
					new Float(14.99),
					new Band("Coldplay", Genere.POP,
							new Artista[] { chrisMartin, jonnyBuckland, guyBerryman, willChampion }),
					"18 ottobre 2013", Genere.POP,
					new Artista[] { chrisMartin, jonnyBuckland, guyBerryman, willChampion }, 50);

			Artista peterGabriel = new Artista("Peter Gabriel", Genere.POP, "Peter Brian Gabriel", new Date(),
					new Strumento[] { new Strumento("Voce"), new Strumento("Sintetizzatore") });

			Prodotto secretWorldLive = new Prodotto(true, "Secret World Live",
					new String[] { "Come Talk to Me", "Steam", "Across the River", "Slow Marimbas", "Shaking the Tree",
							"Red Rain", "Blood of Eden", "Kiss That Frog", "Washing of the Water", "Solsbury Hill",
							"Digging in the Dirt", "Sledgehammer", "Secret World", "Don't Give Up", "In your Eyes" },
					new ImageIcon[] {
							new ImageIcon(AAAMain.class.getResource("/resources/covers/secretworldcover.jpg")) },
					new Float(24.70), peterGabriel, "Remastered 2002", Genere.WORLD_MUSIC,
					new Artista[] { peterGabriel }, 12);

			Artista tommyEmmanuel = new Artista("Tommy Emmanuel", Genere.POP, "William Thomas Emmanuel", new Date(),
					new Strumento[] { new Strumento("Chitarra"), new Strumento("Voce") });

			Prodotto centerStageAlbum = new Prodotto(true, "Center Stage",
					new String[] { "The Finger Lakes", "Papa George", "Train to Duesseldorf", "I Go To Rio",
							"Nine Pound Hammer", "Old Town", "And So It Goes", "Jolly Swagman", "Sukiyaki",
							"Happy Hours", "Ruby's Eyes", "Beatles Medley", "Mombasa", "Workin' Man Blues",
							"Georgia on My Mind", "House of the Risin' Sun", "Amazing Grace", "Story of Little Boy",
							"Tall Fiddler", "Cowboy's Dream", "Morning Aire", "Initiation", "Lenny Bro'", "Questions" },
					new ImageIcon[] { new ImageIcon(AAAMain.class.getResource("/resources/covers/cscover.jpg")) },
					new Float(19.99), tommyEmmanuel, "Released in 2008", Genere.FOLK, new Artista[] { tommyEmmanuel },
					32);

			Artista tylerBates = new Artista("Tyler Bates", Genere.POP, "Tyler Bates", new Date(),
					new Strumento[] { new Strumento("Compositore")});

			Prodotto guardianGalaxy2 = new Prodotto(false, "Guardiani della Galassia Vol. 2 (OSD)",
					new String[] { "Electric Light Orchestra – Mr. Blue Sky", "Sweet – Fox on the Run",
							"Aliotta Haynes Jeremiah – Lake Shore Drive", "Fleetwood Mac – The Chain",
							"Sam Cooke – Bring It On Home to Me", "Glen Campbell – Southern Nights",
							"George Harrison – My Sweet Lord", "Looking Glass – Brandy You're a Fine Girl",
							"Jay and the Americans – Come a Little Bit Closer", "Silver – Wham Bang Shang-A Lang",
							"Cheap Trick – Surrender", "Cat Stevens – Father and Son", "Parliament – Flashlight",
							"The Sneepers feat. David Hasselhoff – Guardians Inferno" },
					new ImageIcon[] { new ImageIcon(AAAMain.class.getResource("/resources/covers/guardianGcover.jpg")) },
					new Float(19.99), tylerBates, "Artisti vari", Genere.ROCK, new Artista[] { tylerBates },
					32);

			try {
				Cliente edoardo = new Cliente("ASHDGUOASDGOLASGYDA", "EdoGimo96", "password", "Edoardo", "Righi",
						"Bussolengo City", "12312312364", null);
				BancaUtenti.addItem(edoardo);
			} catch (Exception e) {

			}

			Catalogo.addItem(totoXIV);
			Catalogo.addItem(prism);
			Catalogo.addItem(ahfodColdPlay);
			Catalogo.addItem(secretWorldLive);
			Catalogo.addItem(centerStageAlbum);
			Catalogo.addItem(guardianGalaxy2);
		}
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

			// incremento e cambio il nome del file finchï¿½ ne trovo un file
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
