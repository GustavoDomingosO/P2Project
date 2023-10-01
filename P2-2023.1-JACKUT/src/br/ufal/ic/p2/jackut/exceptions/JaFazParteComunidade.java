package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário já faz parte de uma comunidade.
 */
public class JaFazParteComunidade extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7899859511781748705L;

	public JaFazParteComunidade() {
        super("Usuario já faz parte dessa comunidade.");
    }
}
