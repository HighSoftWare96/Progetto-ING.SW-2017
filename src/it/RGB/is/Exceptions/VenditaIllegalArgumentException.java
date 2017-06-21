package it.RGB.is.Exceptions;

public class VenditaIllegalArgumentException extends CriticalException {

	private static final long serialVersionUID = 1L;

	public VenditaIllegalArgumentException() {
		super();
	}
	
	public VenditaIllegalArgumentException(String message) {
		super(message);
	}
}
