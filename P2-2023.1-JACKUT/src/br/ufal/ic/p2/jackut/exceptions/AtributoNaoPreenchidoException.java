package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um atributo não está preenchido.
 */

public class AtributoNaoPreenchidoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8152051661114500475L;

	public AtributoNaoPreenchidoException() {
        super("Atributo não preenchido.");
    }
}