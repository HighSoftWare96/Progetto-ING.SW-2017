package it.RGB.is.Exceptions;

public class ArtistIllegalArgumentException extends Exception {

	private static final long serialVersionUID = 1L;

	public ArtistIllegalArgumentException(){
		super();
	}
	
	public ArtistIllegalArgumentException(String message) {
		super(message);
	}
}
