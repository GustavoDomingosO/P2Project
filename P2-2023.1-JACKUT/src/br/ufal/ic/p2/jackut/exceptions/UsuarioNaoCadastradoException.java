package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um usu�rio n�o est� cadastrado.
 */

public class UsuarioNaoCadastradoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5833681161302337455L;

	public UsuarioNaoCadastradoException() {
        super("Usu�rio n�o cadastrado.");
    }
}