package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um login � inv�lido.
 */

public class LoginInvalidoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7369198279526248129L;

	public LoginInvalidoException() {
        super("Login inv�lido.");
    }
}