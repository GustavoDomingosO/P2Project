package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um usu�rio j� faz parte de uma comunidade.
 */
public class JaFazParteComunidade extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7899859511781748705L;

	public JaFazParteComunidade() {
        super("Usuario j� faz parte dessa comunidade.");
    }
}
