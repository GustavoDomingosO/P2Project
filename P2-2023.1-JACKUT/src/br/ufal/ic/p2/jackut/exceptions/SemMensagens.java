package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando não existem mensagens na comunidade.
 */

public class SemMensagens extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4289400915908122028L;

	public SemMensagens() {
        super("Não há mensagens.");
    }
}
