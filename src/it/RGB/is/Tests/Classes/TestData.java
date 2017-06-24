package it.RGB.is.Tests.Classes;

import java.util.Date;

import javax.swing.ImageIcon;

import it.RGB.is.Classes.AAAMain;
import it.RGB.is.Classes.Artista;
import it.RGB.is.Classes.BancaUtenti;
import it.RGB.is.Classes.BancaVendite;
import it.RGB.is.Classes.Band;
import it.RGB.is.Classes.Cart;
import it.RGB.is.Classes.Catalogo;
import it.RGB.is.Classes.Cliente;
import it.RGB.is.Classes.Genere;
import it.RGB.is.Classes.ModConsegna;
import it.RGB.is.Classes.Pagamento;
import it.RGB.is.Classes.Prodotto;
import it.RGB.is.Classes.Strumento;
import it.RGB.is.Classes.Vendita;
import it.RGB.is.Exceptions.ArtistIllegalArgumentException;
import it.RGB.is.Exceptions.ProdottoIllegalArgumentException;

public class TestData {

	private static Artista steveLukather = null;
	private static Artista davidPaich = null;
	private static Artista stevePorcaro = null;
	private static Artista nek = null;
	private static Band toto = null;
	private static Prodotto genericCD = null;
	private static Prodotto genericDVD = null;
	private static Vendita genericVendita = null;
	private static Cliente genericCliente = null;
	private static String genericSongs[] = null;
	private static String genericSongsEmpty[] = null;
	private static Artista noPartecipanti[] = null;

	public static Artista getArtist1() {
		return steveLukather;
	}

	public static Artista getArtist2() {
		return davidPaich;
	}

	public static Artista getArtist3() {
		return stevePorcaro;
	}

	public static Artista getArtistArtName() {
		return nek;
	}

	public static Band getBand() {
		return toto;
	}

	public static Prodotto getGenericCd() {
		return genericCD;
	}

	public static Prodotto getGenericDVD() {
		return genericDVD;
	}

	public static String[] getGenericSongs() {
		return genericSongs;
	}

	public static String[] getGenericSongsEmpty() {
		// initializeData();
		return genericSongsEmpty;
	}

	public static Artista[] getNoPartecipanti() {
		// initializeData();
		return noPartecipanti;
	}

	public static void changeGenOfGenericVendita(Genere genere)
			throws ProdottoIllegalArgumentException, ArtistIllegalArgumentException {
		changeGenOfGenericCD(genere);
		genericVendita = new Vendita(genericCliente, new Prodotto[] { TestData.getGenericCd() }, new Integer[] { 20 },
				250, new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);
	}

	public static void changeGenOfGenericCD(Genere genere)
			throws ProdottoIllegalArgumentException, ArtistIllegalArgumentException {
		genericCD = new Prodotto(false, "Toto XIV",
				new String[] { "Running out of time", "Burn", "Holy war", "21st Century Blues", "Orphan",
						"Unknown Soldier", "The Little Things", "Chinatown", "All the Tears that shine", "Fortune",
						"Great Expectations" },
				new ImageIcon[] { new ImageIcon(AAAMain.class.getResource("/resources/covers/totoXIVcover.jpg")) },
				new Float(12.23), new Band("Toto", genere, new Artista[] { steveLukather, davidPaich, stevePorcaro }),
				"Gran bel album", genere, new Artista[] { steveLukather, davidPaich, stevePorcaro }, 100);
	}

	public static Cliente getGenericCliente() {
		return genericCliente;
	}

	public static Vendita getGenericVendita() {
		return genericVendita;
	}

	public static void initializeData() {
		try {
			Catalogo.initializeFromScratch();
			BancaUtenti.initializeFromScratch();
			BancaVendite.initializeFromScratch();
			Cart.initialize();

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
					new Float(52.23),
					new Band("Toto", Genere.FOLK, new Artista[] { steveLukather, davidPaich, stevePorcaro }),
					"Gran bel album", Genere.FOLK, new Artista[] { steveLukather, davidPaich, stevePorcaro }, 100);

			genericSongs = new String[] { "canzone 1", "canzone 2" };

			genericSongsEmpty = new String[] {};

			noPartecipanti = new Artista[] {};

			genericCliente = new Cliente("BRTGNN96T21B296N", "testUser", "ciao123", "Mario", "Rossi", "Verona",
					"000000000000", null);

			genericVendita = new Vendita(genericCliente, new Prodotto[] { TestData.getGenericCd() },
					new Integer[] { 20 }, 250, new Date(), "localhost", Pagamento.BONIFICO, ModConsegna.CORRIERE_24H);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}