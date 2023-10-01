package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário não está cadastrado.
 */

public class UsuarioNaoCadastradoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5833681161302337455L;

	public UsuarioNaoCadastradoException() {
        super("Usuário não cadastrado.");
    }
}