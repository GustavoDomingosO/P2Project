package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um usu�rio tenta enviar um recado para si mesmo.
 */

public class AutoRecadoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8106794310612205137L;

	public AutoRecadoException() {
        super("Usu�rio n�o pode enviar recado para si mesmo.");
    }
}