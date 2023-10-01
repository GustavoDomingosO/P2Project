package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um login ou senha são inválidos.
 */

public class LoginSenhaInvalidosException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5515674924833685905L;

	public LoginSenhaInvalidosException() {
        super("Login ou senha inválidos.");
    }
}