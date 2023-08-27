package br.ufal.ic.p2.jackut.user;

public class Usuario {
	private String login;
	private String senha;
	private String nome;
	
	public Usuario(String login, String senha, String nome) {
		this.setLogin(login);
		this.setSenha(senha);
		this.setNome(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
