package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um login ou senha s�o inv�lidos.
 */

public class LoginSenhaInvalidosException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5515674924833685905L;

	public LoginSenhaInvalidosException() {
        super("Login ou senha inv�lidos.");
    }
}