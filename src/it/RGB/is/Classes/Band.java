package it.RGB.is.Classes;

import it.RGB.is.Exceptions.ArtistIllegalArgumentException;

public class Band extends ArtistaGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2180832719975689255L;
	private Artista[] componenti;

	/**
	 * Default constructor
	 * 
	 * @throws ArtistIllegalArgumentException
	 */
	public Band(String nomeArte, Genere genere, Artista[] componenti) throws ArtistIllegalArgumentException {
		super(nomeArte, genere);
		this.componenti = componenti;
		checkCorrectData(nomeArte, genere, componenti);
	}

	protected void checkCorrectData(String nomeArte, Genere genere, Artista[] componenti)
			throws ArtistIllegalArgumentException {
		super.checkCorrectData(nomeArte, genere);
		if (componenti == null || componenti.length == 0)
			throw new ArtistIllegalArgumentException();
	}

	public Artista[] getComponenti() {
		// TODO implement here
		return this.componenti;
	}

	public String getNome() {
		return super.getNomeArte();
	}

	@Override
	public String toString() {
		String allComponentNames = "";

		for (Artista item : componenti) {
			allComponentNames += item.toString() + "<br>";
		}
		if (allComponentNames.length() > 0) {
			allComponentNames = allComponentNames.substring(0, allComponentNames.length() - 2); // RIMOZIONE
																								// ultima
																								// virgola
			return super.getNomeArte() + ": " + allComponentNames;
		} else {
			return super.getNomeArte();
		}
	}

}