package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando não há recados disponíveis.
 */

public class SemRecadosException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2683822068123937011L;

	public SemRecadosException() {
        super("Não há recados.");
    }
}