package br.ufal.ic.p2.jackut.inimigo;

import br.ufal.ic.p2.jackut.database.DatabaseManager;

public class InimigoConcreto extends Inimigo {
	public InimigoConcreto(){
		super();
	}

	@Override
	public boolean EhRelacionamento(String login, String relacionado, DatabaseManager database) {
		// Desnecess�rio, mas est� aqui caso seja necess�rio no futuro.
		return false;
	}

	@Override
	public String getRelacionamentos(String login, DatabaseManager database) {
		// Desnecess�rio, mas est� aqui caso seja necess�rio no futuro.
		return null;
	}
}
