package it.RGB.is.Classes;

import java.util.Date;

import it.RGB.is.Exceptions.ArtistIllegalArgumentException;

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
	 * 
	 * @throws ArtistIllegalArgumentException
	 */
	public Artista(String nomeArte, Genere genere, String nomeBattesimo, Date dataNascita, Strumento[] strumenti)
			throws ArtistIllegalArgumentException {
		super(nomeArte, genere);

		checkCorrectData(nomeArte, genere, nomeBattesimo, dataNascita, strumenti);

		this.nomeBattesimo = nomeBattesimo;
		this.dataNascita = dataNascita;
		this.strumenti = strumenti;
	}

	protected void checkCorrectData(String nomeArte, Genere genere, String nomeBattesimo2, Date dataNascita2,
			Strumento[] strumenti2) throws ArtistIllegalArgumentException {
		// metodo ereditato di controllo errori
		super.checkCorrectData(nomeArte, genere);
		if (nomeBattesimo2 == null || nomeBattesimo2.equals("") || dataNascita2 == null || strumenti2.length == 0)
			throw new ArtistIllegalArgumentException();
	}

	/**
	 * @return
	 */
	public String getNomeBattesimo() {
		return this.nomeBattesimo;
	}

	/**
	 * @return
	 */
	public Date getDataNascita() {
		return this.dataNascita;
	}

	/**
	 * @return
	 */
	public Strumento[] getStrumento() {
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