package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando uma senha é inválida.
*/

public class SenhaInvalidaException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7439838152180381997L;

	public SenhaInvalidaException() {
        super("Senha inválida.");
    }
}