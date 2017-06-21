package it.RGB.is.Exceptions;

public class CartIllegalArgumentsException extends CriticalException {
	
	private static final long serialVersionUID = 1L;

	public CartIllegalArgumentsException(){
		super();
	}
	
	public CartIllegalArgumentsException(String message) {
		super(message);
	
	}
}
