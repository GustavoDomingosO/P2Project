package br.ufal.ic.p2.jackut.database;

interface CoordenadorDeLimpeza {
	void zerarSistema();
	void deletaSistema();
	void removeUsuario(String login);
	void removeTodosOsMembrosDaComunidade(String nome);
}
