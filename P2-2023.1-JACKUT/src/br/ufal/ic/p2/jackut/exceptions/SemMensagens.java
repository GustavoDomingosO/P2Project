package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando n�o existem mensagens na comunidade.
 */

public class SemMensagens extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4289400915908122028L;

	public SemMensagens() {
        super("N�o h� mensagens.");
    }
}
