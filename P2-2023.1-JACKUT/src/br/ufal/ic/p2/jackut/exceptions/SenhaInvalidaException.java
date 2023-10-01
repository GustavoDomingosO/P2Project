package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando uma senha � inv�lida.
*/

public class SenhaInvalidaException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7439838152180381997L;

	public SenhaInvalidaException() {
        super("Senha inv�lida.");
    }
}