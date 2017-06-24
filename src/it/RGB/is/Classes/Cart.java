package it.RGB.is.Classes;

import java.util.HashMap;

import it.RGB.is.Exceptions.CartIllegalArgumentsException;
import it.RGB.is.Exceptions.LightCartException;;

public class Cart {

	private static HashMap<Prodotto, Integer> strutturaDati = null; // prodotto
																	// &
																	// amount
	private static float subTotale;
	private static boolean withDiscount = false;

	public static void initialize() {
		strutturaDati = new HashMap<>();
	}

	public static float getSubTotale(ModConsegna modConsegna) {
		// se la modConsegna è null allora calcolo solo il subtotale
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

	// calcola il valore di subTotale
	private static void calculateSubTotale(float pagSpedizione) {
		if (pagSpedizione < 0)
			throw new CartIllegalArgumentsException("Calcolo subtotale fallito: costo spedizione negativo.");

		float result = pagSpedizione;

		for (Prodotto item : strutturaDati.keySet()) {
			result += (item.getPrezzo() * strutturaDati.get(item));
		}
		subTotale = result;
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

	// rimuove prodotto dal carrello
	public static void removeItem(Prodotto prodotto, int q) throws LightCartException {
		if (prodotto == null)
			throw new CartIllegalArgumentsException("Rimozione dal carrello fallita (null pointer).");
		if (q <= 0)
			throw new CartIllegalArgumentsException(
					"Rimozione dal carrello fallita: quantità da rimuovere negativa o nulla.");
		if (strutturaDati.size() == 0 || !strutturaDati.containsKey(prodotto))
			throw new LightCartException("Rimozione dal carrello fallita (carrello vuoto, o prodotto inesistente).");

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
		if (prodotto == null)
			throw new CartIllegalArgumentsException("Aggiunta al carrello fallita (null pointer).");
		if (q <= 0)
			throw new CartIllegalArgumentsException(
					"Aggiunta al carrello fallita: quantità da aggiungere negativa o nulla.");
		if (q > prodotto.getDisp())
			throw new CartIllegalArgumentsException(
					"Aggiunta al carrello fallita: quantità da aggiungere maggiore della disponibilità.");

		// se è già presente il prodotto lo aggiungo alla quantità gia presente
		if (strutturaDati.containsKey(prodotto)) {
			q += strutturaDati.get(prodotto);
		}
		strutturaDati.put(prodotto, q);
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
		throw new LightCartException();
	}

	public static int getCartNumberItems() {
		return strutturaDati.size();
	}

	public static Prodotto getItemByID(int selectedID) {
		for (Prodotto item : strutturaDati.keySet())
			if (item.getID() == selectedID)
				return item;
		throw new LightCartException();
	}

	public static boolean hasDiscount() {
		return withDiscount;
	}
}
