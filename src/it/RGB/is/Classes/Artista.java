package it.RGB.is.Classes;

import java.util.Date;

public class Artista extends ArtistaGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3001273989197795597L;
	private String nomeBattesimo;
	private Date dataNascita;
	private Strumento[] strumenti;

	/**
	 * Default constructor
	 */
	public Artista(String nomeArte, Genere genere, String nomeBattesimo, Date dataNascita, Strumento[] strumenti) {
		super(nomeArte, genere);
		this.nomeBattesimo = nomeBattesimo;
		this.dataNascita = dataNascita;
		this.strumenti = strumenti;
	}

	/**
	 * @return
	 */
	public String getNomeBattesimo() {
		// TODO implement here
		return this.nomeBattesimo;
	}

	/**
	 * @return
	 */
	public Date getDataNascita() {
		// TODO implement here
		return this.dataNascita;
	}

	/**
	 * @return
	 */
	public Strumento[] getStrumento() {
		// TODO implement here
		return this.strumenti;
	}

	public String toString() {
		String strumentiSuonati = "";

		for (Strumento item : strumenti) {
			strumentiSuonati += item.toString();
		}

		if (super.getNomeArte().equals(nomeBattesimo))
			return this.nomeBattesimo + strumentiSuonati;
		else
			return super.getNomeArte() + " (" + nomeBattesimo + ")" + strumentiSuonati;
	}

}