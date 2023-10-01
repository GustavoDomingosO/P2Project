package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando uma conta com o mesmo nome j� existe.
 */

public class ContaJaExisteException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2301984184636486098L;

	public ContaJaExisteException() {
        super("Conta com esse nome j� existe.");
    }
}