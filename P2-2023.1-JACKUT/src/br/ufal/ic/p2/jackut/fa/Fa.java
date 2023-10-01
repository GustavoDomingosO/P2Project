package br.ufal.ic.p2.jackut.fa;

import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.relacionamentos.Relacionamentos;

public abstract class Fa implements Relacionamentos{
	
	@Override
	public void adicionarRelacionamento(String id, String relacionado, DatabaseManager database)throws NaoPodeRealizarAcaoComInimigo, UsuarioNaoCadastradoException, NaoPodeSerFaDeSiMesmo, JaEhIdolo  {
		String usuario = database.getLoginDoId(Integer.parseInt(id));
		
		if(!database.checaLoginExiste(relacionado)) {
			throw new UsuarioNaoCadastradoException();
		}
		
		if(usuario.equals(relacionado)) {
			throw new NaoPodeSerFaDeSiMesmo();
		}
		
		if (database.checaSeUsuarioEhInimigo(usuario, relacionado) || database.checaSeUsuarioEhInimigo(relacionado, usuario)){
			throw new NaoPodeRealizarAcaoComInimigo(database.getAtributo(relacionado, "nome"));
		}
		
		if (!EhRelacionamento(usuario, relacionado,database)) {
			database.insereIdolo(usuario, relacionado);
		} else {
			throw new JaEhIdolo();
		}
	}
	@Override
	public boolean EhRelacionamento(String login, String relacionado, DatabaseManager database) {
		if (database.checaSeUsuarioEhFa(login, relacionado)) {
			return true;
		} else {
			return false;			
		}
	}
	
	@Override
	public String getRelacionamentos(String login, DatabaseManager database) {
		return database.listaFas(login);
	}
	/*public static boolean ehFa(String login, String idolo, DatabaseManager database) {
		if (database.checaSeUsuarioEhFa(login, idolo)) {
			return true;
		} else {
			return false;			
		}
	}*/
	
/*	public static void adicionarIdolo(String id, String idolo, DatabaseManager database) throws JaEhIdolo, UsuarioNaoCadastradoException, NaoPodeSerFaDeSiMesmo, NaoPodeRealizarAcaoComInimigo {
		String usuario = database.getLoginDoId(Integer.parseInt(id));
		
		if(!database.checaLoginExiste(idolo)) {
			throw new UsuarioNaoCadastradoException();
		}
		
		if(usuario.equals(idolo)) {
			throw new NaoPodeSerFaDeSiMesmo();
		}
		
		if (database.checaSeUsuarioEhInimigo(usuario, idolo) || database.checaSeUsuarioEhInimigo(idolo, usuario)){
			throw new NaoPodeRealizarAcaoComInimigo(database.getAtributo(idolo, "nome"));
		}
		
		if (!ehFa(usuario, idolo,database)) {
			database.insereIdolo(usuario, idolo);
		} else {
			throw new JaEhIdolo();
		}
		
	}*/
	
	/*public static String getFas(String login, DatabaseManager database) {
		return database.listaFas(login);
	}
	*/
}
