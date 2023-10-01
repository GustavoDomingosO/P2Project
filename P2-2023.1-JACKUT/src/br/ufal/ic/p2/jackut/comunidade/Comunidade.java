package br.ufal.ic.p2.jackut.comunidade;

import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.*;

public abstract class Comunidade {
	
	public static void criarComunidade(String sessao, String nome, String descrição, DatabaseManager database) throws ComunidadeJaExiste {		
		if(!database.checaComunidadeExiste(nome)) {
			String dono = database.getLoginDoId(Integer.parseInt(sessao));
			database.insereNovaComunidade(nome, descrição, dono);
			database.insereNovoMembroNaComunidade(nome, dono);
		}
		else {
			throw new ComunidadeJaExiste();
		}
	}
	
	public static String getDescricaoComunidade(String nome, DatabaseManager database) throws ComunidadeNaoExiste {
		if(database.checaComunidadeExiste(nome)) {
			return database.getDescricao(nome);
		} 
		else {
			throw new ComunidadeNaoExiste();
		}
	}
	
	public static String getDonoComunidade(String nome, DatabaseManager database) throws ComunidadeNaoExiste {
		if(database.checaComunidadeExiste(nome)) {
			return database.getNomeDaComunidade(nome);
		}
		else {
			throw new ComunidadeNaoExiste();
		}
	}
	
	public static String getMembrosComunidade(String nome, DatabaseManager database) throws ComunidadeNaoExiste {
		
		if(database.checaComunidadeExiste(nome)) {
			return database.listaMembrosDaComunidade(nome);
		}
		else {
			throw new ComunidadeNaoExiste();	
		}
	}
	
	public static String getComunidades(String usuario, DatabaseManager database) throws UsuarioNaoCadastradoException {
		if (usuario.isBlank()) {
			throw new UsuarioNaoCadastradoException();
		}
		
		return database.listaComunidadeDoMembro(usuario);
	}
	
	public static void adicionarComunidade(String sessao, String nomeComunidade, DatabaseManager database) throws JaFazParteComunidade, ComunidadeNaoExiste, UsuarioNaoCadastradoException {

		if (sessao.isBlank()) {
			throw new UsuarioNaoCadastradoException();
		}
		
		String membro = database.getLoginDoId(Integer.parseInt(sessao));

		String listaComunidadesString = database.listaComunidadeDoMembro(membro);
		
		if(!database.checaComunidadeExiste(nomeComunidade)) {
			throw new ComunidadeNaoExiste();
		}

		if(!listaComunidadesString.contains(nomeComunidade)) {
			database.insereNovoMembroNaComunidade(nomeComunidade, membro);
		}
		
		else {
			throw new JaFazParteComunidade();
		}	
	}
	
}
