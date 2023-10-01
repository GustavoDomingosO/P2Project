package br.ufal.ic.p2.jackut.exceptions;

public class JaEhIdolo extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7444560844981517381L;

	public JaEhIdolo() {
        super("Usuário já está adicionado como ídolo.");
    }
}
