package it.RGB.is.Classes;

import java.awt.Toolkit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import it.RGB.is.Exceptions.IllegalUserRegistration;

public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1278855704341243367L;
	private String codiceFiscale;
	private String nomeUtente;
	private String password;
	private String nome;
	private String cognome;
	private String city;
	private String numeroTel;
	private String numeroCell = ""; // opzionale

	private ArrayList<Vendita> acquisti;

	// costruttore SENZA numeroCell
	public Cliente(String codiceFiscale, String nomeUtente, String password, String nome, String cognome, String city,
			String numeroTel, String numeroCell) throws IllegalUserRegistration {

		checkCorrectData(codiceFiscale, nomeUtente, password, nome, cognome, city, numeroTel, numeroCell);

		this.codiceFiscale = codiceFiscale;
		this.nomeUtente = nomeUtente;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.city = city;
		this.numeroTel = numeroTel;

		if (numeroCell != null && !numeroCell.equals(""))
			this.numeroCell = numeroCell;

		this.acquisti = new ArrayList<>();
	}

	private void checkCorrectData(String codiceFiscale, String nomeUtente, String password, String nome, String cognome,
			String city, String numeroTel, String numeroCell) throws IllegalUserRegistration {

		// controllo validità parametri
		if (codiceFiscale == null || nomeUtente == null || password == null || nome == null || cognome == null
				|| city == null || numeroTel == null || codiceFiscale.equals("") || nomeUtente.equals("")
				|| password.equals("") || nome.equals("") || cognome.equals("") || city.equals("")
				|| numeroTel.equals(""))
			throw new IllegalUserRegistration(IllegalUserRegistration.MSG_EMPTY_FIELDS);

		else if (nomeUtente.contains(" ")) {
			throw new IllegalUserRegistration(IllegalUserRegistration.MSG_USRNM_SPACES);
		} else if (!codiceFiscale.equals(codiceFiscale.toUpperCase())
				|| !codiceFiscale.matches("^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$")) {
			throw new IllegalUserRegistration(IllegalUserRegistration.MSG_CF_SINTAX);
		} else if (!numeroTel.matches("[0-9]+")) {
			throw new IllegalUserRegistration(IllegalUserRegistration.MSG_TEL_ERROR);
		} else if (numeroCell != null) {
			if (!numeroCell.matches("[0-9]+") && !numeroCell.equals(""))
				throw new IllegalUserRegistration(IllegalUserRegistration.MSG_CELL_ERROR);
		} else if (password.length() <= 5) {
			throw new IllegalUserRegistration(IllegalUserRegistration.MSG_PSW_LOW);
		} else if (BancaUtenti.userNameExists(nomeUtente)) {
			throw new IllegalUserRegistration(IllegalUserRegistration.MSG_USRNM_USED);
		}
	}

	public void addVendita(Vendita vendita) {
		acquisti.add(vendita);
	}

	public String getCF() {

		return this.codiceFiscale;
	}

	public String getUsername() {

		return this.nomeUtente;
	}

	public String getPassword() {

		return this.password;
	}

	public String getNome() {

		return this.nome;
	}

	public String getCognome() {

		return this.cognome;
	}

	public String getCity() {

		return this.city;
	}

	public String getTel() {

		return this.numeroTel;
	}

	public String getCell() {
		return this.numeroCell;
	}

	public int getVenditeCount() {
		return acquisti.size();
	}

	public Genere calculateGeneriPref() {
		int[] arrayGenere = new int[Genere.values().length];

		for (Vendita v : getVendite())
			for (Prodotto p : v.getProdotti())
				switch (p.getGenere()) {
				case JAZZ:
					arrayGenere[0]++;
					break;
				case ROCK:
					arrayGenere[1]++;
					break;
				case CLASSICA:
					arrayGenere[2]++;
					break;
				case LATIN:
					arrayGenere[3]++;
					break;
				case FOLK:
					arrayGenere[4]++;
					break;
				case WORLD_MUSIC:
					arrayGenere[5]++;
					break;
				case POP:
					arrayGenere[6]++;
					break;
				default:
					break;
				}

		// prendo l'indice maggiore
		int maxIndex = 0;
		for (int i = 1; i < arrayGenere.length; i++) {
			int newnumber = arrayGenere[i];
			if ((newnumber > arrayGenere[maxIndex])) {
				maxIndex = i;
			}
		}
		// ritorno il genere corrispondente
		if (maxIndex == 0)
			return Genere.JAZZ;
		else if (maxIndex == 1)
			return Genere.ROCK;
		else if (maxIndex == 2)
			return Genere.CLASSICA;
		else if (maxIndex == 3)
			return Genere.LATIN;
		else if (maxIndex == 4)
			return Genere.FOLK;
		else if (maxIndex == 5)
			return Genere.WORLD_MUSIC;
		else
			return Genere.POP;
	}

	public boolean canHaveDiscounts() {
		int counter = 0;

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, -1); // un anno in meno rispetto adesso
		Date aYearAgo = c.getTime();

		for (Vendita item : this.acquisti) {
			if (item.getPrezzoTotale() > 250 && item.getDate().compareTo(aYearAgo) >= 0) // entro
																							// l'anno
																							// 3
																							// acquisti
																							// da
																							// almeno
																							// 250
																							// euro
				counter++;
		}
		return counter >= 3;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Cliente) {
			Cliente other = (Cliente) obj;
			// se hanno lo stesso username
			return this.getUsername().equals(other.getUsername());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.nomeUtente.hashCode();
	}

	public Vendita[] getVendite() {
		return this.acquisti.toArray(new Vendita[acquisti.size()]);
	}

}