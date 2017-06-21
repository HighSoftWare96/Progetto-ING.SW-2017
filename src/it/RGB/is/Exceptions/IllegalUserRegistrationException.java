package it.RGB.is.Exceptions;

public class IllegalUserRegistrationException extends Exception {

	private static final long serialVersionUID = 1L;
	public static final String MSG_EMPTY_FIELDS = "Riempire tutti i campi obbligatori!";
	public static final String MSG_USRNM_SPACES = "Nome utente scorretto";
	public static final String MSG_CF_SINTAX = "Codice fiscale non corretto";
	public static final String MSG_TEL_ERROR = "Numero di telefono non corretto";
	public static final String MSG_CELL_ERROR = "Numero di cellulare non corretto";
	public static final String MSG_USRNM_USED = "Nome utente già utilizzato";
	public static final String MSG_PSW_LOW = "Password poco sicura! (# caratteri > 5)";
	public static final String MSG_PSWS_NMATCH = "Le password inserite non corrispondono";
	
	public IllegalUserRegistrationException() {
		super();
	}

	public IllegalUserRegistrationException(String error) {
		super(error);
	}

}
