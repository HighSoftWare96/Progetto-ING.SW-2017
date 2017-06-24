package it.RGB.is.Exceptions;

public class LightBancaVenditeException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 1L;

	public LightBancaVenditeException(){
		super();
	}
	
	public LightBancaVenditeException(String message){
		super(message);
	}
}
