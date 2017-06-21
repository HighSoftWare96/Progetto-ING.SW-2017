package it.RGB.is.Classes;

import java.io.Serializable;

import it.RGB.is.Exceptions.ArtistIllegalArgumentException;

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

	public ArtistaGenerico(String nomeArte, Genere genere) throws ArtistIllegalArgumentException {
		this.nomeArte = nomeArte;
		this.generePrincipale = genere;
	}

	protected void checkCorrectData(String nomeArte, Genere genere) throws ArtistIllegalArgumentException {
		if (nomeArte == null || genere == null || nomeArte.equals(""))
			throw new ArtistIllegalArgumentException();
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