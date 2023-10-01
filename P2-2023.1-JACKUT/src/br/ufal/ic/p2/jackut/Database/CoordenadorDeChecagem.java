package br.ufal.ic.p2.jackut.database;

public interface CoordenadorDeChecagem {
	boolean checaLoginExiste(String login);
	boolean checaSeIdDeUsuarioExiste(int id);
	boolean checaComunidadeExiste(String nome);
	boolean checaAmizade(String login, String amigo);
	boolean checaSeUsuarioEhFa(String usuario, String idolo);
	boolean checaSeUsuarioEhPaquera(String usuario,String paquera);
	boolean checaSeUsuarioEhInimigo(String usuario,String inimigo);
}
