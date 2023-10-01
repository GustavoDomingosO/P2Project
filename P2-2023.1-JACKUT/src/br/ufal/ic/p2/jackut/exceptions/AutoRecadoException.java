package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário tenta enviar um recado para si mesmo.
 */

public class AutoRecadoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8106794310612205137L;

	public AutoRecadoException() {
        super("Usuário não pode enviar recado para si mesmo.");
    }
}