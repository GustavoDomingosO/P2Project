package br.ufal.ic.p2.jackut.exceptions;

public class NaoPodeSerFaDeSiMesmo extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7471064033496022712L;

	public NaoPodeSerFaDeSiMesmo() {
        super("Usu�rio n�o pode ser f� de si mesmo.");
    }
}
