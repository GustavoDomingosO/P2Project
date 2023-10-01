package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um login é inválido.
 */

public class LoginInvalidoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7369198279526248129L;

	public LoginInvalidoException() {
        super("Login inválido.");
    }
}