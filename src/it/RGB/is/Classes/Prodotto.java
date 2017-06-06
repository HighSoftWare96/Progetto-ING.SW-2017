package it.RGB.is.Classes;

import java.io.Serializable;
import java.util.Date;

import javax.swing.ImageIcon;

public class Prodotto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8414508171294857938L;
	private boolean dvd;
	private int ID;
	private String titolo;
	private String[] titoliPezzi;
	private ImageIcon[] photos;
	private float prezzo;
	private Date dataArrivo;
	private ArtistaGenerico musicistaTitolare;
	private String descrizione;
	private Genere genere;
	private ArtistaGenerico[] partecipanti;
	private int disp;

	/**
	 * Default constructor
	 */
	public Prodotto() {
	}

	/**
	 * @param boolean
	 *            dvd
	 * @param String
	 *            titolo
	 * @param StringArray
	 *            titoliPezzi
	 * @param ImageIconArray
	 *            photos
	 * @param float
	 *            prezzo
	 * @param String
	 *            titolare
	 * @param String
	 *            descrizione
	 * @param Genere
	 *            genere
	 * @param ArtistArray
	 *            partecipanti
	 * @param int
	 *            disp
	 */
	public Prodotto(boolean dvd, String titolo, String[] titoliPezzi, ImageIcon[] photos, float prezzo,
			ArtistaGenerico titolare, String descrizione, Genere genere, ArtistaGenerico[] partecipanti, int disp) {
		this.ID = setUniqueID();
		this.dvd = dvd;
		this.titolo = titolo;
		this.titoliPezzi = titoliPezzi;
		this.photos = photos;
		this.prezzo = prezzo;
		this.dataArrivo = setData();
		this.musicistaTitolare = titolare;
		this.descrizione = descrizione;
		this.genere = genere;
		this.partecipanti = partecipanti;
		this.disp = disp;
	}

	public boolean isDVD() {
		return this.dvd;
	}

	public int getID() {
		return this.ID;
	}

	public String getTitolo() {
		return this.titolo;
	}

	public String[] getTitoliPezzi() {
		return this.titoliPezzi;
	}

	public Date getDataArrivo() {
		return this.dataArrivo;
	}

	public ArtistaGenerico getTitolare() {
		return this.musicistaTitolare;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public Genere getGenere() {
		return this.genere;
	}

	public ImageIcon[] getPhotos() {
		return photos;
	}

	public ArtistaGenerico[] getPartecipanti() {
		return this.partecipanti;
	}

	public int getDisp() {
		return this.disp;
	}

	public void setNewDisp(int nProdottiVenduti) {
		this.disp -= nProdottiVenduti;
	}

	public void setNewDispAdd(int nProdottiRestituiti) {
		this.disp += nProdottiRestituiti;
	}

	public ImageIcon getCover() {
		return this.photos[0];
	}

	public String getSongsTitles() {
		String result = "";
		for (String item : this.titoliPezzi)
			result += item + ", ";
		return result.substring(0, result.length() - 2);
	}

	public String getPrezzoString() {
		return String.format("%.2f", this.prezzo);
	}

	public float getPrezzo() {
		return this.prezzo;
	}

	private int setUniqueID() {
		return Catalogo.getUniqueID();
	}

	private Date setData() {
		return new Date();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Prodotto) {
			Prodotto other = (Prodotto) obj;
			return (other.getTitolo().equals(this.getTitolo()));
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.titolo.hashCode();
	}

}