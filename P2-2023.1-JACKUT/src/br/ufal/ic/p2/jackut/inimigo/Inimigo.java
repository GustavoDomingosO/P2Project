package br.ufal.ic.p2.jackut.inimigo;

import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.relacionamentos.Relacionamentos;

public abstract class Inimigo implements Relacionamentos{
	
	/**
     * Construtor protegido para evitar a instanciação direta da classe.
     *
     * @throws NaoPodeInstanciar Esta exceção é lançada ao tentar instanciar a classe.
     */
	
	public Inimigo(){

	}
	
	@Override
	public void adicionarRelacionamento(String id, String relacionado, DatabaseManager database) throws JaEhInimigo, UsuarioNaoCadastradoException, NaoPodeSerInimigoDeSiMesmo {
		
		String usuario = database.getLoginDoId(Integer.parseInt(id));
		
		if (database.checaSeUsuarioEhInimigo(usuario, relacionado)) {
			
			throw new JaEhInimigo();
			
		} else if(!database.checaLoginExiste(relacionado)) {
			
			throw new UsuarioNaoCadastradoException();
			
		} else if (usuario.equals(relacionado)) {
			
			throw new NaoPodeSerInimigoDeSiMesmo();
			
		} else {
			
			database.insereInimigo(usuario, relacionado);	
			
		}


	}
}
