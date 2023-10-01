package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um atributo n�o est� preenchido.
 */

public class AtributoNaoPreenchidoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8152051661114500475L;

	public AtributoNaoPreenchidoException() {
        super("Atributo n�o preenchido.");
    }
}