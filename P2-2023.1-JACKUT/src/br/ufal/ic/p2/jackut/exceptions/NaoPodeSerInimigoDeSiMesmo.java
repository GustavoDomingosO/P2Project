package br.ufal.ic.p2.jackut.exceptions;

public class NaoPodeSerInimigoDeSiMesmo extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8825184582127551257L;

	public NaoPodeSerInimigoDeSiMesmo() {
        super("Usu�rio n�o pode ser inimigo de si mesmo.");
    }
}
