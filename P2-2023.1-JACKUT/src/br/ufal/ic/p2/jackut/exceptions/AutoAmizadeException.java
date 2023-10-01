package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar a si mesmo como amigo.
 */

public class AutoAmizadeException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6296150307840305348L;

	public AutoAmizadeException() {
        super("Usu�rio n�o pode adicionar a si mesmo como amigo.");
    }
}