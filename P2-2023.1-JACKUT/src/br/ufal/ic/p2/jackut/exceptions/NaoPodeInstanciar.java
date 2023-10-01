package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando tenta-se instanciar uma classe que não deve ser instanciada.
 */

public class NaoPodeInstanciar extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5203684435544933543L;

	public NaoPodeInstanciar() {
        super("Esta classe não deve ser instanciada.");
    }
}