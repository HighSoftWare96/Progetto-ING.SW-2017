package it.RGB.is.Classes;

public enum ModConsegna {
	CORRIERE_24H, POSTA, GRATIS;

	@Override
	public String toString() {
		switch (this) {
		case CORRIERE_24H:
			return "Corriere 24H";
		case POSTA:
			return "Posta Ordinaria";
		case GRATIS:
			return "Gratuita";
		default:
			return "";
		}
	}
}