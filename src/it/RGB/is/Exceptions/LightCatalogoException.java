package it.RGB.is.Exceptions;

public class LightCatalogoException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 1L;

	public LightCatalogoException(){
		super();
	}
	
	public LightCatalogoException(String message){
		super(message);
	}

}
