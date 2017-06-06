package it.RGB.is.Classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * 
 */
public class BancaVendite {

	private static ArrayList<Vendita> strutturaVendite;
	private final static File venditeFile = new File("music_store_file//vendite.dat");

	/**
	 * Default constructor
	 */
	public BancaVendite() {
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
				e.printStackTrace();
			}
		} else // creo il catalogo da zero
			strutturaVendite = new ArrayList<>();
	}

	/**
	 * @param Vendita
	 */
	public static void addItem(Vendita vendita) {
		// TODO implement here
		strutturaVendite.add(vendita);
	}

	public static void saveVendite() {

		if (!venditeFile.exists()) { // se il file non esiste lo creo
			try {
				venditeFile.getParentFile().mkdirs();
				venditeFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
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
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}