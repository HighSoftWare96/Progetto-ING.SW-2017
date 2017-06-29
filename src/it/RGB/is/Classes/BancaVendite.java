package it.RGB.is.Classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;

import it.RGB.is.Exceptions.CriticalException;
import it.RGB.is.Exceptions.LightBancaVenditeException;

public class BancaVendite {

	private static BancaVendite uniqueInstance = null;
	private static ArrayList<Vendita> strutturaVendite;
	private final static File venditeFile = new File("music_store_file//vendite.dat");

	private BancaVendite() {
		if (venditeFile.exists()) {
			// recupero i dati
			try {
				// file di input
				FileInputStream inputFile = new FileInputStream(venditeFile);
				// creazione dello stream di byte da ricevere
				ObjectInputStream streamInput = new ObjectInputStream(inputFile);
				// recupero l'array list dal file e lo salvo nell'arraylist
				// attuale del programma
				strutturaVendite = (ArrayList<Vendita>) streamInput.readObject();
				streamInput.close();
			} catch (Exception e) {
				throw new CriticalException("BancaVendite IO: inizializzione dati da file");
			}
		} else // creo il catalogo da zero
			strutturaVendite = new ArrayList<>();
	}

	// SOLO PER TESTING !!!
	public static void initializeFromScratch() {
		strutturaVendite = new ArrayList<>();
	}

	public static void initialize() throws CriticalException {
		// SINGLETON + PROXY?
		if (uniqueInstance == null)
			uniqueInstance = new BancaVendite();
	}

	public static void addItem(Vendita vendita) throws LightBancaVenditeException {
		if (vendita == null) {
			throw new LightBancaVenditeException("Aggiunta vendita fallita (null pointer).");
		}
		strutturaVendite.add(vendita);
	}

	public static void saveVendite() throws CriticalException {

		if (!venditeFile.exists()) { // se il file non esiste lo creo
			try {
				venditeFile.getParentFile().mkdirs();
				venditeFile.createNewFile();
			} catch (IOException e) {
				throw new CriticalException("BancaVendite IO: salvataggio su file");
			}
		}

		try {
			FileOutputStream outToFile;
			ObjectOutputStream byteStreamToSave;

			// creo un file di output (stesso di prima)
			outToFile = new FileOutputStream(venditeFile);

			// creo uno stream di output che punta al file
			byteStreamToSave = new ObjectOutputStream(outToFile);

			// scrivo l'oggetto nel file in modo da poterlo recuperare la
			// prossima volta
			byteStreamToSave.writeObject((Object) strutturaVendite);
			byteStreamToSave.close();
			outToFile.close();

		} catch (IOException e) {
			throw new CriticalException("BancaVendite IO: salvataggio su file");
		}

	}

}