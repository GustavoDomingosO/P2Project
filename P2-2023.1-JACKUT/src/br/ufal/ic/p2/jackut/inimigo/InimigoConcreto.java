package br.ufal.ic.p2.jackut.inimigo;

import br.ufal.ic.p2.jackut.database.DatabaseManager;

public class InimigoConcreto extends Inimigo {
	public InimigoConcreto(){
		super();
	}

	@Override
	public boolean EhRelacionamento(String login, String relacionado, DatabaseManager database) {
		// Desnecessário, mas está aqui caso seja necessário no futuro.
		return false;
	}

	@Override
	public String getRelacionamentos(String login, DatabaseManager database) {
		// Desnecessário, mas está aqui caso seja necessário no futuro.
		return null;
	}
}
