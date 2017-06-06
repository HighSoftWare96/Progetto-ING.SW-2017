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

/**
 * 
 */
public class BancaUtenti implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8289777747322280430L;
	private final static File utentiFile = new File("music_store_file//utenti.dat");
	private static HashSet<Cliente> strutturaUtenti;
	private static Cliente clienteLoggato = null;

	/**
	 * Default constructor
	 */
	public BancaUtenti() {
		if (utentiFile.exists()) {
			// recupero i dati
			try {
				// file di input
				FileInputStream inputFile = new FileInputStream(utentiFile);
				// creazione dello stream di byte da ricevere
				ObjectInputStream streamInput = new ObjectInputStream(inputFile);
				// recupero l'array list dal file e lo salvo nell'arraylist
				// attuale del programma
				strutturaUtenti = (HashSet<Cliente>) streamInput.readObject();
				streamInput.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else // creo il catalogo da zero
			strutturaUtenti = new HashSet<>();

	}

	public Iterator<Cliente> iterator() {
		// TODO implement here
		return strutturaUtenti.iterator();
	}

	public static void addItem(Cliente cliente) {
		// TODO implement here
		strutturaUtenti.add(cliente);
	}

	public static boolean checkLogIn(String username, String password) {
		for (Cliente item : strutturaUtenti) {
			if (item.getUsername().equals(username) && item.getPassword().equals(password)) {
				clienteLoggato = item;
				return true;
			}
		}
		return false;
	}

	public static void setLoggedOut() {
		clienteLoggato = null;
	}

	public static Cliente getLoggedInUser() {
		return clienteLoggato;
	}

	public static boolean userNameExists(String newUserName) {
		for (Cliente item : strutturaUtenti) {
			if (item.getUsername().equals(newUserName))
				return true;
		}
		return false;
	}

	public static void saveUtenti() {

		if (!utentiFile.exists()) { // se il file non esiste lo creo
			try {
				utentiFile.getParentFile().mkdirs();
				utentiFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			FileOutputStream outToFile;
			ObjectOutputStream byteStreamToSave;

			// creo un file di output (stesso di prima)
			outToFile = new FileOutputStream(utentiFile);

			// creo uno stream di output che punta al file
			byteStreamToSave = new ObjectOutputStream(outToFile);

			// scrivo l'oggetto nel file in modo da poterlo recuperare la
			// prossima volta
			byteStreamToSave.writeObject((Object) strutturaUtenti);
			byteStreamToSave.close();
			outToFile.close();

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}