package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando n�o h� recados dispon�veis.
 */

public class SemRecadosException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2683822068123937011L;

	public SemRecadosException() {
        super("N�o h� recados.");
    }
}