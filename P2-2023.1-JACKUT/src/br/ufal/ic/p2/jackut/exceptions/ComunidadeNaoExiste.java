package br.ufal.ic.p2.jackut.exceptions;
/**
 * Exce��o lan�ada quando uma comunidade n�o existe.
 */
public class ComunidadeNaoExiste extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4648473850043257962L;

	public ComunidadeNaoExiste() {
        super("Comunidade n�o existe.");
    }
}
