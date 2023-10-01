package br.ufal.ic.p2.jackut.database;

public interface CoordenadorDeInsercao {
	void insereNoCadastro(String login, String senha, String nome);
	void insereNovaComunidade(String nome, String descricao, String dono);
	void insereNovoMembroNaComunidade(String nome, String membro);
	void insereMensagemUsuario(String usuario, String mensagem);
	void insereAtributo(String login, String atributo, String valor);
	void insereIdolo(String usuario, String idolo);
	void inserePaquera(String usuario, String paquera);
	void insereInimigo(String usuario, String inimigo);
	void insereRecado(String login, String destinatario, String recado);
	void insereAmizade(String login, String amigo);
}
