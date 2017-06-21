package it.RGB.is.Exceptions;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;

import javax.swing.JOptionPane;

public class CriticalException extends RuntimeException {
	public CriticalException() {
		super();
		criticalErrToFile(null);
	}

	public CriticalException(String message) {
		super(message);
		criticalErrToFile(message);
	}

	public void criticalErrToFile(String message) {

		if (message == null)
			JOptionPane.showMessageDialog(null,
					"<html>Errore critico nell'esecuzione del programma.<br>Il programma verrà chiuso.<br>Vedere file di report per dettagli.",
					"Errore", JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(null,
					"<html>Errore critico nell'esecuzione del programma:<br>" + message
							+ "<br>Il programma verrà chiuso." + message + "<br>Vedere file di report per dettagli.",
					"Errore", JOptionPane.ERROR_MESSAGE);

		String stackTrace = message + "\nSTACK TRACE:\n";

		// recupero lo stack trace dell'eccezione chiamata
		for (StackTraceElement item : super.getStackTrace()) {
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

		System.exit(-1);
	}
}
