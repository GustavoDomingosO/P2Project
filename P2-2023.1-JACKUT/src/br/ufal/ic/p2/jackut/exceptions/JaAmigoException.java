package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um usu�rio j� � amigo de outro.
 */

public class JaAmigoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6463849037357719963L;

	public JaAmigoException() {
        super("Usu�rio j� est� adicionado como amigo.");
    }
}