package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário tenta adicionar a si mesmo como amigo.
 */

public class AutoAmizadeException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6296150307840305348L;

	public AutoAmizadeException() {
        super("Usuário não pode adicionar a si mesmo como amigo.");
    }
}