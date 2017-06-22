package it.RGB.is.Exceptions;

public class NoGenPrefFoundException extends Exception {
	public NoGenPrefFoundException() {
		super("Spiacenti, nessun suggerimento trovato!\n");
	}
}
