package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando tenta-se instanciar uma classe que n�o deve ser instanciada.
 */

public class NaoPodeInstanciar extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5203684435544933543L;

	public NaoPodeInstanciar() {
        super("Esta classe n�o deve ser instanciada.");
    }
}