package br.ufal.ic.p2.jackut.exceptions;

public class NaoPodeSerPaqueraDeSiMesmo extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5781999205409950651L;

	public NaoPodeSerPaqueraDeSiMesmo() {
        super("Usu�rio n�o pode ser paquera de si mesmo.");
    }
}
