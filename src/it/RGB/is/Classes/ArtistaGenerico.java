package it.RGB.is.Classes;

import java.io.Serializable;
import java.util.Date;

import it.RGB.is.Exceptions.IllegalArtistArguments;

/**
 * 
 */
public abstract class ArtistaGenerico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4811175080810999429L;
	private String nomeArte;
	private Genere generePrincipale;

	public ArtistaGenerico(String nomeArte, Genere genere) throws IllegalArtistArguments {
		this.nomeArte = nomeArte;
		this.generePrincipale = genere;
	}

	protected void checkCorrectData(String nomeArte, Genere genere) throws IllegalArtistArguments {
		if (nomeArte == null || genere == null || nomeArte.equals(""))
			throw new IllegalArtistArguments();
	}

	public String getNomeArte() {
		return this.nomeArte;
	}

	public Genere getGenerePrincipale() {
		return this.generePrincipale;
	}

	@Override
	public abstract String toString();

}