package br.ufal.ic.p2.jackut.exceptions;

public class NaoPodeRealizarAcaoComInimigo extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8277452620897377690L;

	public NaoPodeRealizarAcaoComInimigo(String inimigo) {
        super("Fun��o inv�lida: " + inimigo +" � seu inimigo.");
    }
}
