package it.RGB.is.Classes;

public class Band extends ArtistaGenerico {

	private Artista[] componenti;

	/**
	 * Default constructor
	 */
	public Band(String nomeArte, Genere genere, Artista[] componenti) {
		super(nomeArte, genere);
		this.componenti = componenti;
	}

	public Artista[] getComponenti() {
		// TODO implement here
		return this.componenti;
	}

	@Override
	public String toString() {
		String allComponentNames = "";

		for (Artista item : componenti) {
			allComponentNames += item.toString() + ", ";
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