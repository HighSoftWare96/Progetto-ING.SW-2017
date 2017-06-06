package it.RGB.is.Classes;

public enum Pagamento {
	BONIFICO, CARTA_CREDITO, PAYPAL;

	@Override
	public String toString() {
		switch (this) {
		case BONIFICO:
			return "Bonifico bancario";
		case CARTA_CREDITO:
			return "Carta di credito";
		case PAYPAL:
			return "PayPal";
		default:
			return "";
		}
	}
}