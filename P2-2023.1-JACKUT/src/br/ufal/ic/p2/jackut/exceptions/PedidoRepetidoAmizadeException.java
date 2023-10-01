package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um pedido de amizade j� foi enviado.
 */

public class PedidoRepetidoAmizadeException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4023926327940150453L;

	public PedidoRepetidoAmizadeException() {
        super("Usu�rio j� est� adicionado como amigo, esperando aceita��o do convite.");
    }
}