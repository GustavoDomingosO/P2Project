package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando uma comunidade com esse nome já existe.
 */

public class ComunidadeJaExiste extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8106694410612205137L;

	public ComunidadeJaExiste() {
        super("Comunidade com esse nome já existe.");
    }
	
}
