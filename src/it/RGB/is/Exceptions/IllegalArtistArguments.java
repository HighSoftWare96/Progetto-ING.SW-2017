package it.RGB.is.Exceptions;

public class IllegalArtistArguments extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalArtistArguments(){
		super();
	}
	
	public IllegalArtistArguments(String message) {
		super(message);
	}
}
