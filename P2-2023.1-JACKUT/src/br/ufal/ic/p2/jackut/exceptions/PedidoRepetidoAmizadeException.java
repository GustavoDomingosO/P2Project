package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um pedido de amizade já foi enviado.
 */

public class PedidoRepetidoAmizadeException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4023926327940150453L;

	public PedidoRepetidoAmizadeException() {
        super("Usuário já está adicionado como amigo, esperando aceitação do convite.");
    }
}