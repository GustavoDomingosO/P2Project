package br.ufal.ic.p2.jackut.database;

public interface CoordenadorDeListagem {
	
	String listaAmigos(String login);
	String listaFas(String idolo);
	String listaPaqueras(String usuario);
	String listaMembrosDaComunidade(String nome);
	String listaComunidadeDoMembro(String membro);
}
