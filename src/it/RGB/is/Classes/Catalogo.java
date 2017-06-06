package it.RGB.is.Classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JOptionPane;

import it.RGB.is.GUI.GUIMain;

/**
 * 
 */
public class Catalogo implements Serializable {

	private static final long serialVersionUID = 3336345341426740830L;
	private final static File catalogoFile = new File("music_store_file//catalogo.dat");
	private static HashSet<Prodotto> strutturaDati;
	private static int availableID;

	/**
	 * Default constructor
	 */
	public Catalogo() {
		if (catalogoFile.exists()) {
			// recupero i dati
			try {
				// file di input
				FileInputStream inputFile = new FileInputStream(catalogoFile);
				// creazione dello stream di byte da ricevere
				ObjectInputStream streamInput = new ObjectInputStream(inputFile);
				// recupero l'array list dal file e lo salvo nell'arraylist
				// attuale del programma
				strutturaDati = (HashSet<Prodotto>) streamInput.readObject();
				streamInput.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else // creo il catalogo da zero
			strutturaDati = new HashSet<>();
	}

	public Iterator<Prodotto> iterator() {
		// TODO implement here
		return strutturaDati.iterator();
	}

	/**
	 * @param Prodotto
	 */
	public void addItem(Prodotto prodotto) {
		// TODO implement here
		strutturaDati.add(prodotto);
	}

	public static void removeItem(Prodotto prodotto, int amount) {
		prodotto.setNewDisp(amount);
		if (prodotto.getDisp() <= 2) {
			JOptionPane.showMessageDialog(GUIMain.getFrame(),
					"Attenzione!\nProdotto: " + prodotto.getID() + ", " + prodotto.getTitolo() + " in esaurimento!",
					"Prodotto in esaurimento", JOptionPane.ERROR_MESSAGE);
			if (prodotto.getDisp() == 0)
				Catalogo.removeAll(prodotto);
		}
	}

	private static void removeAll(Prodotto prodotto) {
		strutturaDati.remove(prodotto);
	}

	/**
	 * @param Field
	 */
	public HashSet<Prodotto> searchFor(SearchMod field, Object key) {
		// TODO implement here
		return null;
	}

	public static int getUniqueID() {
		return availableID++;
	}

	public static Prodotto[] getCatalog() {
		return strutturaDati.toArray(new Prodotto[strutturaDati.size()]);
	}

	public static void saveCatalog() {

		if (!catalogoFile.exists()) { // se il file non esiste lo creo
			try {
				catalogoFile.getParentFile().mkdirs();
				catalogoFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			FileOutputStream outToFile;
			ObjectOutputStream byteStreamToSave;

			// creo un file di output (stesso di prima)
			outToFile = new FileOutputStream(catalogoFile);

			// creo uno stream di output che punta al file
			byteStreamToSave = new ObjectOutputStream(outToFile);

			// scrivo l'oggetto nel file in modo da poterlo recuperare la
			// prossima volta
			byteStreamToSave.writeObject((Object) strutturaDati);
			byteStreamToSave.close();
			outToFile.close();

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static Prodotto searchByID(int ID) {
		for (Prodotto item : strutturaDati) {
			if (item.getID() == ID)
				return item;
		}
		return null;
	}

	public static Prodotto[] searchByKey(SearchMod searchMod, String key) {
		HashSet<Prodotto> result = new HashSet<>();

		switch (searchMod) {
		case ARTISTA:
			for (Prodotto item : strutturaDati) {
				for (ArtistaGenerico artista : item.getPartecipanti())
					if (artista.toString().toLowerCase().contains(key)) // il
																		// partecipante
																		// è
						// nella lista
						result.add(item);
			}
			break;
		case TITOLARE:
			for (Prodotto item : strutturaDati) {
				if (item.getTitolare().toString().toLowerCase().contains(key))
					result.add(item);
			}
			break;
		case TITOLO:
			for (Prodotto item : strutturaDati) {
				if (item.getTitolo().toLowerCase().contains(key))
					result.add(item);
			}
			break;
		case BRANI:
			for (Prodotto item : strutturaDati) {
				if (item.getSongsTitles().toLowerCase().contains(key))
					result.add(item);
			}
			break;
		default:
			break;
		}

		return result.toArray(new Prodotto[result.size()]);
	}

	public static Prodotto[] searchByGen(Genere selectedGen) {
		HashSet<Prodotto> result = new HashSet<>();

		for (Prodotto item : strutturaDati) {
			if (item.getGenere() == selectedGen)
				result.add(item);
		}

		return result.toArray(new Prodotto[result.size()]);
	}

	public static Prodotto[] searchByPrice(int from, int to) {
		HashSet<Prodotto> result = new HashSet<>();

		for (Prodotto item : strutturaDati) {
			if (item.getPrezzo() <= to && item.getPrezzo() >= from)
				result.add(item);
		}

		return result.toArray(new Prodotto[result.size()]);
	}

	public static void addItem(Prodotto prodotto, int amountSelected) {
		prodotto.setNewDispAdd(amountSelected); // aggiungo i prodotti
		// se non c'è lo rimetto
		if (!strutturaDati.contains(prodotto)) {
			strutturaDati.add(prodotto);
		}

	}

}