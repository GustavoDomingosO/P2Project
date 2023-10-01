package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário já é amigo de outro.
 */

public class JaAmigoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6463849037357719963L;

	public JaAmigoException() {
        super("Usuário já está adicionado como amigo.");
    }
}