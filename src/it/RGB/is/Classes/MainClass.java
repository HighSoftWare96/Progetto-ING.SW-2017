package it.RGB.is.Classes;

import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import it.RGB.is.Exceptions.ArtistIllegalArgumentException;
import it.RGB.is.Exceptions.CriticalException;
import it.RGB.is.Exceptions.ProdottoIllegalArgumentException;
import it.RGB.is.GUI.GUIMain;

public class MainClass {

	private static boolean insertOldData = true;

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

			try {
				// data testing
				Artista steveLukather = new Artista("Steve Lukather", Genere.ROCK, "Steve Lukather", new Date(),
						new Strumento[] { new Strumento("Chitarra"), new Strumento("Voce"), new Strumento("Basso") });
				Artista davidPaich = new Artista("David Paich", Genere.ROCK, "David Paich", new Date(),
						new Strumento[] { new Strumento("Pianoforte"), new Strumento("Organo"),
								new Strumento("Contrabasso") });
				Artista stevePorcaro = new Artista("Steve Porcaro", Genere.ROCK, "Steve Porcaro", new Date(),
						new Strumento[] { new Strumento("Sintetizzatore") });

				Prodotto totoXIV = new Prodotto(false, "Toto XIV",
						new String[] { "Running out of time", "Burn", "Holy war", "21st Century Blues", "Orphan",
								"Unknown Soldier", "The Little Things", "Chinatown", "All the Tears that shine",
								"Fortune", "Great Expectations" },
						new ImageIcon[] {
								new ImageIcon(MainClass.class.getResource("/resources/covers/totoXIVcover.jpg")) },
						new Float(12.23),
						new Band("Toto", Genere.ROCK, new Artista[] { steveLukather, davidPaich, stevePorcaro }),
						"Gran bel album", Genere.ROCK, new Artista[] { steveLukather, davidPaich, stevePorcaro }, 100);

				Artista katyPerry = new Artista("Katy Perry", Genere.POP, "Katheryn Elizabeth Hudson", new Date(),
						new Strumento[] { new Strumento("Voce") });

				Prodotto prism = new Prodotto(false, "Prism",
						new String[] { "Roar", "Legendary Lovers", "Birthday", "Unconditionally (feat. Juicy J)",
								"This Is How We Do", "International Smile", "Ghost", "Love Me", "This Moment",
								"Double Rainbow", "By The Grace Of God" },
						new ImageIcon[] {
								new ImageIcon(MainClass.class.getResource("/resources/covers/prismcover.jpg")) },
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
						new ImageIcon[] {
								new ImageIcon(MainClass.class.getResource("/resources/covers/ahfodcover.jpg")),
								new ImageIcon(MainClass.class.getResource("/resources/covers/ahfodcover_back.jpg")) },
						new Float(14.99),
						new Band("Coldplay", Genere.POP,
								new Artista[] { chrisMartin, jonnyBuckland, guyBerryman, willChampion }),
						"18 ottobre 2013", Genere.POP,
						new Artista[] { chrisMartin, jonnyBuckland, guyBerryman, willChampion }, 50);

				Artista peterGabriel = new Artista("Peter Gabriel", Genere.POP, "Peter Brian Gabriel", new Date(),
						new Strumento[] { new Strumento("Voce"), new Strumento("Sintetizzatore") });

				Prodotto secretWorldLive = new Prodotto(true, "Secret World Live",
						new String[] { "Come Talk to Me", "Steam", "Across the River", "Slow Marimbas",
								"Shaking the Tree", "Red Rain", "Blood of Eden", "Kiss That Frog",
								"Washing of the Water", "Solsbury Hill", "Digging in the Dirt", "Sledgehammer",
								"Secret World", "Don't Give Up", "In your Eyes" },
						new ImageIcon[] {
								new ImageIcon(MainClass.class.getResource("/resources/covers/secretworldcover.jpg")) },
						new Float(24.70), peterGabriel, "Remastered 2002", Genere.WORLD_MUSIC,
						new Artista[] { peterGabriel }, 12);

				Artista tommyEmmanuel = new Artista("Tommy Emmanuel", Genere.POP, "William Thomas Emmanuel", new Date(),
						new Strumento[] { new Strumento("Chitarra"), new Strumento("Voce") });

				Prodotto centerStageAlbum = new Prodotto(true, "Center Stage",
						new String[] { "The Finger Lakes", "Papa George", "Train to Duesseldorf", "I Go To Rio",
								"Nine Pound Hammer", "Old Town", "And So It Goes", "Jolly Swagman", "Sukiyaki",
								"Happy Hours", "Ruby's Eyes", "Beatles Medley", "Mombasa", "Workin' Man Blues",
								"Georgia on My Mind", "House of the Risin' Sun", "Amazing Grace", "Story of Little Boy",
								"Tall Fiddler", "Cowboy's Dream", "Morning Aire", "Initiation", "Lenny Bro'",
								"Questions" },
						new ImageIcon[] { new ImageIcon(MainClass.class.getResource("/resources/covers/cscover.jpg")) },
						new Float(19.99), tommyEmmanuel, "Released in 2008", Genere.FOLK,
						new Artista[] { tommyEmmanuel }, 32);

				Artista tylerBates = new Artista("Tyler Bates", Genere.POP, "Tyler Bates", new Date(),
						new Strumento[] { new Strumento("Compositore") });

				Prodotto guardianGalaxy2 = new Prodotto(false, "Guardiani della Galassia Vol. 2 (OST)",
						new String[] { "Electric Light Orchestra – Mr. Blue Sky", "Sweet – Fox on the Run",
								"Aliotta Haynes Jeremiah – Lake Shore Drive", "Fleetwood Mac – The Chain",
								"Sam Cooke – Bring It On Home to Me", "Glen Campbell – Southern Nights",
								"George Harrison – My Sweet Lord", "Looking Glass – Brandy You're a Fine Girl",
								"Jay and the Americans – Come a Little Bit Closer", "Silver – Wham Bang Shang-A Lang",
								"Cheap Trick – Surrender", "Cat Stevens – Father and Son", "Parliament – Flashlight",
								"The Sneepers feat. David Hasselhoff – Guardians Inferno" },
						new ImageIcon[] {
								new ImageIcon(MainClass.class.getResource("/resources/covers/guardianGcover.jpg")) },
						new Float(19.99), tylerBates, "Artisti vari", Genere.ROCK, new Artista[] { tylerBates }, 32);

				Artista johnColtrane = new Artista("John Coltrane", Genere.JAZZ, "John William Coltrane", new Date(),
						new Strumento[] { new Strumento("Sassofono") });
				Artista leeMorgan = new Artista("Lee Morgan", Genere.JAZZ, "Lee Morgan", new Date(),
						new Strumento[] { new Strumento("Tromba") });
				Artista curtisFuller = new Artista("Curtis Fuller", Genere.JAZZ, "Curtis DuBois Fuller", new Date(),
						new Strumento[] { new Strumento("Trombone") });
				Artista kennyDrew = new Artista("Kenny Drew", Genere.JAZZ, "Kenneth Sidney Drew", new Date(),
						new Strumento[] { new Strumento("Pianoforte") });
				Artista paulChambers = new Artista("Curtis Fuller", Genere.JAZZ, "Paul Laurence Dunbar Chambers Jr.",
						new Date(), new Strumento[] { new Strumento("Basso") });
				Artista phillyJoeJones = new Artista("Philly Joe Jones", Genere.JAZZ, "Joseph Rudolph Jones",
						new Date(), new Strumento[] { new Strumento("Batteria") });

				Prodotto blueTrain = new Prodotto(false, "Blue Train",
						new String[] { "Blue Train", "Moment's Notice", "Locomotion", "I'm Old Fashioned", "Lazy Bird",
								"Blue Train (alt. take)", "Lazy Bird (alt. take)" },
						new ImageIcon[] {
								new ImageIcon(MainClass.class.getResource("/resources/covers/bluetrain.jpg")) },
						new Float(8.99), johnColtrane, "Album del 1957", Genere.JAZZ, new Artista[] { johnColtrane,
								leeMorgan, curtisFuller, kennyDrew, paulChambers, phillyJoeJones },
						80);

				Artista manuChao = new Artista("Manu Chao", Genere.LATIN, "Manu Chao", new Date(),
						new Strumento[] { new Strumento("Voce"), new Strumento("Chitarra") });

				Prodotto esperanza = new Prodotto(false, "Próxima estación: Esperanza",
						new String[] { "Merry Blues", "Bixo", "El Dorado 1997", "Promiscuity", "La Primavera",
								"Me gustas tú", "Denia", "Mi Vida", "Trapped by Love", "Le Rendez Vous", "Mr. Bobby",
								"Papito", "La Chinita", "La Marea", "Homens", "La Vacaloca", "Infinita Tristeza" },
						new ImageIcon[] {
								new ImageIcon(MainClass.class.getResource("/resources/covers/esperanzacover.jpg")) },
						new Float(6.99), manuChao, "Album del 2001", Genere.LATIN, new Artista[] { manuChao }, 100);

				Artista hansZimmer = new Artista("Hans Zimmer", Genere.POP, "Hans Florian Zimmer", new Date(),
						new Strumento[] { new Strumento("Compositore") });

				Prodotto interstellar = new Prodotto(false, "Interstellar (OST)",
						new String[] { "Dreaming of the Crash", "Cornfield Cha1se", "Dust", "Day One", "Stay",
								"Message from Home", "The Wormhole", "Mountains", "Afraid of Time",
								"A Place Among the Stars", "Running Out", "I'm Going Home", "Coward", "Detach",
								"S.T.A.Y.", "Where We're Going" },
						new ImageIcon[] {
								new ImageIcon(MainClass.class.getResource("/resources/covers/interstcover.jpg")) },
						new Float(14.99), hansZimmer, "Soundtrack del film Interstellar (girato da Cristopher Nolan)",
						Genere.CLASSICA, new Artista[] { hansZimmer }, 44);
				
				Prodotto inception = new Prodotto(false, "Inception: Music from the Motion Picture", 
						new String[] {"Half Remembered Dream", "We Built Our Own World", "Dream Is Collapsing",
								"Radical Notion", "Old Souls", "528491", "Mombasa", "One Simple Idea", 
								"Dream Within A Dream", "Waiting For A Train", "Paradox", "Time"}, 
						new ImageIcon[] {
								new ImageIcon(MainClass.class.getResource("/resources/covers/inception.jpg")) },
						new Float(12.99), hansZimmer, "Soundtrack del film Inception (girato da Cristopher Nolan)",
						Genere.CLASSICA, new Artista[] { hansZimmer }, 30);
				
				Artista herbieHancock = new Artista("Herbie Hancock", Genere.JAZZ, "Herbert Jeffrey Hancock", new Date(),
						new Strumento[] { new Strumento("Pianoforte"), new Strumento("Clavinet"), new Strumento("Sintetizzatore")});
				Artista bennieMaupin = new Artista("Bennie Maupin", Genere.JAZZ, "Bennie Maupin", new Date(),
						new Strumento[] { new Strumento("Sassofono"), new Strumento("Clarinetto Basso"), new Strumento("Flauto contralto")});
				Artista paulJackson =  new Artista("Paul Jackson", Genere.JAZZ, "Paul Jackson", new Date(),
						new Strumento[] { new Strumento("Basso elettrico")});
				Artista harveyMason =  new Artista("Harvey Mason", Genere.JAZZ, "Harvey Williams Mason", new Date(),
						new Strumento[] { new Strumento("Batteria")});
				Artista billSummers =  new Artista("Bill Summers", Genere.JAZZ, "Bill Summers", new Date(),
						new Strumento[] { new Strumento("Conga"), new Strumento("Tamburello"), new Strumento("Cabassa")});
				
				Prodotto headHunters = new Prodotto(false, "Head Hunters",
						new String[] { "Chameleon", "Watermelon Man", "Sly", "Vein Melter" },
						new ImageIcon[] {
								new ImageIcon(MainClass.class.getResource("/resources/covers/headhunterscover.jpg")) },
						new Float(5.99), herbieHancock, "Uno degli album più venduti della storia della musica jazz.",
						Genere.JAZZ, new Artista[] { herbieHancock, bennieMaupin, paulJackson, harveyMason, billSummers }, 12);

				Catalogo.addItem(totoXIV);
				Catalogo.addItem(prism);
				Catalogo.addItem(ahfodColdPlay);
				Catalogo.addItem(secretWorldLive);
				Catalogo.addItem(centerStageAlbum);
				Catalogo.addItem(guardianGalaxy2);
				Catalogo.addItem(blueTrain);
				Catalogo.addItem(esperanza);
				Catalogo.addItem(interstellar);
				Catalogo.addItem(inception);
				Catalogo.addItem(headHunters);

			} catch (ArtistIllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, "Errore nell'inizializzazione di alcuni artisti!",
						"Errore init dati", JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			} catch (ProdottoIllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, "Errore nell'inizializzazione di alcuni prodotti!",
						"Errore init dati", JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			}

			try {
				Cliente edoardo = new Cliente("ASHDGUOASDGOLASGYDA", "EdoGimo96", "password", "Edoardo", "Righi",
						"Bussolengo City", "12312312364", null);
				BancaUtenti.addItem(edoardo);
			} catch (Exception e) {

			}
		}
	}
}
