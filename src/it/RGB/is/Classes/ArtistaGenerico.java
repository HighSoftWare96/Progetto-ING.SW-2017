package it.RGB.is.Classes;

import java.io.Serializable;

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

	/**
	 * Default constructor
	 */
	public ArtistaGenerico(String nomeArte, Genere genere) {
		this.nomeArte = nomeArte;
		this.generePrincipale = genere;
	}

	public String getNomeArte() {
		// TODO implement here
		return this.nomeArte;
	}

	public Genere getGenerePrincipale() {
		// TODO implement here
		return this.generePrincipale;
	}

	@Override
	public abstract String toString();

}