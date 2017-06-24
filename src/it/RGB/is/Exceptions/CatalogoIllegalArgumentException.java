package it.RGB.is.Exceptions;

public class CatalogoIllegalArgumentException extends CriticalException {
	
	private static final long serialVersionUID = 1L;

	public CatalogoIllegalArgumentException(){
		super();
	}
	
	public CatalogoIllegalArgumentException(String message){
		super(message);
	}

}
