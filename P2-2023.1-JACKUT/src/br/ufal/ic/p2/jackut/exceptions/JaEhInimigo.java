package br.ufal.ic.p2.jackut.exceptions;

public class JaEhInimigo extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5599326000563949345L;

	public JaEhInimigo() {
        super("Usuário já está adicionado como inimigo.");
    }
}
