package br.ufal.ic.p2.jackut.paquera;

import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.recados.Recados;
import br.ufal.ic.p2.jackut.relacionamentos.*;

public abstract class Paquera implements Relacionamentos{
	/**
     * Construtor protegido para evitar a instanciação direta da classe.
     *
     * @throws NaoPodeInstanciar Esta exceção é lançada ao tentar instanciar a classe.
     */
	
	protected Paquera() {
	}
	@Override
	public void adicionarRelacionamento(String id, String relacionado, DatabaseManager database) throws NaoPodeRealizarAcaoComInimigo, UsuarioNaoCadastradoException, NaoPodeSerPaqueraDeSiMesmo, JaEhPaquera {
		String usuario = database.getLoginDoId(Integer.parseInt(id));
		
		if(!database.checaLoginExiste(relacionado)) {
			throw new UsuarioNaoCadastradoException();
		}
		
		if(usuario.equals(relacionado)) {
			throw new NaoPodeSerPaqueraDeSiMesmo();
		}
		
		if (database.checaSeUsuarioEhInimigo(usuario, relacionado) || database.checaSeUsuarioEhInimigo(relacionado, usuario)){
			throw new NaoPodeRealizarAcaoComInimigo(database.getAtributo(relacionado, "nome"));
		}
		
		
		if (!EhRelacionamento(usuario, relacionado,database)) {
			
			database.inserePaquera(usuario, relacionado);
			
			
			if (database.checaSeUsuarioEhPaquera(relacionado, usuario)) {
				String nomePaquera = database.getAtributo(relacionado, "nome");
				String nomeUsuario = database.getAtributo(usuario, "nome");
				String recado1 = nomePaquera + " é seu paquera - Recado do Jackut.";
				String recado2 = nomeUsuario + " é seu paquera - Recado do Jackut.";
				Recados.recadoDoSistema(1, usuario, recado1, database);	
				Recados.recadoDoSistema(1, relacionado, recado2, database);		
			}
		} else {
			throw new JaEhPaquera();
		}
	}
	//A função puxa id 
	@Override
	public boolean EhRelacionamento(String login, String relacionamento, DatabaseManager database) {
		String usuario = login;
		if (database.checaSeUsuarioEhPaquera(usuario, relacionamento)) {
			return true;
		} else {
			return false;			
		}
	}
	//A função puxa id 
	@Override
	public String getRelacionamentos(String login, DatabaseManager database) {
		String usuario = login;
		return database.listaPaqueras(usuario);
	}
	
}
