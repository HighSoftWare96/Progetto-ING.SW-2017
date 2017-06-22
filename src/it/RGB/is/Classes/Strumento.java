package it.RGB.is.Classes;

import java.io.Serializable;

import it.RGB.is.Exceptions.StrumentoIllegalArgument;

public class Strumento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2738916795378027946L;
	private String nomeStrumento;

	public Strumento(String nome) {
		if (nome == null || nome.equals(""))
			throw new StrumentoIllegalArgument();
		
		this.nomeStrumento = nome;
	}

	public String getNomeStrumento() {
		return this.nomeStrumento;
	}

	public String toString() {
		return " [" + this.nomeStrumento + "] ";
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Strumento) && ((Strumento) obj).getNomeStrumento().equals(this.getNomeStrumento());
	}

	@Override
	public int hashCode() {
		return this.getNomeStrumento().hashCode();
	}
}