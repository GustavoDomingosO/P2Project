package br.ufal.ic.p2.jackut.database;

public interface CoordenadorDeGetters {
	String getLoginDoId(int id);
	String getAtributo(String login, String atributo);
	String getDescricao(String nome);
	String getComunidadePeloDono(String dono);
	String getNomeDaComunidade(String nome);
}
