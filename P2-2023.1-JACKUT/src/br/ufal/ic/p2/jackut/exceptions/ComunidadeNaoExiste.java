package br.ufal.ic.p2.jackut.exceptions;
/**
 * Exceção lançada quando uma comunidade não existe.
 */
public class ComunidadeNaoExiste extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4648473850043257962L;

	public ComunidadeNaoExiste() {
        super("Comunidade não existe.");
    }
}
