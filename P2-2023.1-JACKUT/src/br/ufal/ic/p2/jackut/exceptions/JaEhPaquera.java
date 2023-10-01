package br.ufal.ic.p2.jackut.exceptions;

public class JaEhPaquera extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7995684761414412632L;

	public JaEhPaquera() {
        super("Usuário já está adicionado como paquera.");
    }
}
