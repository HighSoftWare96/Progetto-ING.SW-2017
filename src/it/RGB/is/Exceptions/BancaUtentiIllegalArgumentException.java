package it.RGB.is.Exceptions;

public class BancaUtentiIllegalArgumentException extends Exception {

	private static final long serialVersionUID = 1L;

	public BancaUtentiIllegalArgumentException(){
		super();
	}
	
	public BancaUtentiIllegalArgumentException(String message){
		super(message);
	}
}
