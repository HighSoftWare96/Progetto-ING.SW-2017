package it.RGB.is.Classes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vendita implements Serializable {

	private static final long serialVersionUID = -6971244944884772121L;
	private Cliente acquirente;
	private Prodotto[] prodotti;
	private Integer[] amount;
	private float prezzoTotale;
	private Date data;
	private String indirizzoIP;
	private Pagamento modPag;
	private ModConsegna modConsegna;

	public Vendita(Cliente acquirente, Prodotto[] prodotti, Integer[] amount, float prezzoTotale, Date data,
			String indirizzoIP, Pagamento modPag, ModConsegna modconsegna) {
		this.acquirente = acquirente;
		this.prodotti = prodotti;
		this.amount = amount;
		this.prezzoTotale = prezzoTotale;
		this.data = data;
		this.indirizzoIP = indirizzoIP;
		this.modPag = modPag;
		this.modConsegna = modconsegna;
	}

	public Prodotto[] getProdotti() {
		return prodotti;
	}

	public Integer[] getAmount() {
		return this.amount;
	}

	public float getPrezzoTotale() {
		return this.prezzoTotale;
	}

	public String getDateString() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
		return formatter.format(this.data);
	}

	public Date getDate() {
		return this.data;
	}

	public String getIP() {
		return this.indirizzoIP;
	}

	public String getPagamento() {
		return this.modPag.toString();
	}

	public String getConsegna() {
		return this.modConsegna.toString();
	}

	public int getProdottiLength() {
		return prodotti.length;
	}

	public String getProdottoString(int index) {
		return prodotti[index].getTitolo();
	}
}
