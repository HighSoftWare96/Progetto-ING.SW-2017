package it.RGB.is.Exceptions;

public class NoPrefFoundException extends Exception {
	public NoPrefFoundException() {
		super("Spiacenti, nessun suggerimento trovato!\n");
	}
}
