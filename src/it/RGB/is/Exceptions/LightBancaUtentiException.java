package it.RGB.is.Exceptions;

public class LightBancaUtentiException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public LightBancaUtentiException(){
		super();
	}
	
	public LightBancaUtentiException(String message){
		super(message);
	}
}
