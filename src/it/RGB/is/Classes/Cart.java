package it.RGB.is.Classes;

import java.util.HashMap;;

public class Cart {

	private static HashMap<Prodotto, Integer> strutturaDati = new HashMap<>();; // prodotto
																				// +
	// quantità
	private static float subTotale;
	private static boolean withDiscount = false;

	public static float getSubTotale(ModConsegna modConsegna) {
		if (modConsegna == null)
			calculateSubTotale(0);
		else {
			switch (modConsegna) {
			case CORRIERE_24H:
				calculateSubTotale(7);
				break;
			case GRATIS:
				calculateSubTotale(0);
				break;
			case POSTA:
				calculateSubTotale(5);
				break;
			}
		}

		if (BancaUtenti.getLoggedInUser() != null)
			if (BancaUtenti.getLoggedInUser().canHaveDiscounts()) {
				withDiscount = true;
				subTotale = (subTotale * 75) / 100; // sconto del 25 %
			}

		return subTotale;
	}

	// rimuove prodotto dal carrello
	public static void removeItem(Prodotto prodotto, int q) {
		// aggiornamento del carrello
		strutturaDati.put(prodotto, strutturaDati.get(prodotto) - q);

		if (strutturaDati.get(prodotto) == 0) {
			strutturaDati.remove(prodotto);
		}
	}

	public static void removeAll() {
		for (Prodotto item : strutturaDati.keySet()) {
			// aggiorno il catalogo
			Catalogo.addItem(item, strutturaDati.get(item));
		}
		strutturaDati.clear();
	}

	public static void buyAndRemove() {
		strutturaDati.clear();
	}

	// aggiunge prodotto al carrello
	public static void addItem(Prodotto prodotto, Integer q) {

		// se è già presente il prodotto lo aggiungo alla quantità gia presente
		if (strutturaDati.containsKey(prodotto)) {
			q += strutturaDati.get(prodotto);
		}
		strutturaDati.put(prodotto, q);
	}

	// calcola il subtotale senza la spedizione
	public static float calculateSubTotaleNotSped() {
		float result = 0;

		for (Prodotto item : strutturaDati.keySet()) {
			result += (item.getPrezzo() * strutturaDati.get(item));
		}

		subTotale = result;
		return subTotale;
	}

	// calcola il valore di subTotale
	private static void calculateSubTotale(float pagSpedizione) {
		float result = pagSpedizione;

		for (Prodotto item : strutturaDati.keySet()) {
			result += (item.getPrezzo() * strutturaDati.get(item));
		}

		subTotale = result;
	}

	public static Prodotto[] getCart() {
		return strutturaDati.keySet().toArray(new Prodotto[strutturaDati.size()]);
	}

	public static Integer[] getAmount() {
		return strutturaDati.values().toArray(new Integer[strutturaDati.size()]);
	}

	public static Integer getQuantita(int ID) {
		for (Prodotto item : strutturaDati.keySet())
			if (item.getID() == ID)
				return strutturaDati.get(item);
		return -1;
	}

	public static int getCartNumberItems() {
		return strutturaDati.size();
	}

	public static Prodotto getItemByID(int selectedID) {
		for (Prodotto item : strutturaDati.keySet())
			if (item.getID() == selectedID)
				return item;
		return null;
	}

	public static boolean hasDiscount() {
		return withDiscount;
	}
}
