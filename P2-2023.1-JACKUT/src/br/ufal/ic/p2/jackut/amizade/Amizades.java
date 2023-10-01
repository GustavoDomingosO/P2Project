package br.ufal.ic.p2.jackut.amizade;

import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.relacionamentos.Relacionamentos;


/**
 * Classe abstrata que gerencia a funcionalidade de fazer amizades.
 */

public abstract class Amizades implements Relacionamentos{

	/**
     * Construtor protegido para evitar a instanciação direta da classe.
     *
     * @throws NaoPodeInstanciar Esta exceção é lançada ao tentar instanciar a classe.
     */
	
	protected Amizades() {
		
	}
	
    /**
     * Adiciona um novo amigo para um usuário.
     *
     * @param id O ID do usuário.
     * @param relacionado O login do amigo a ser adicionado.
     * @param database O banco de dados do sistema.
     * @throws AutoAmizadeException Quando se tenta adicionar a si mesmo como amigo.
     * @throws PedidoRepetidoAmizadeException Quando já existe um pedido de amizade pendente.
     * @throws JaAmigoException Quando o usuário já é amigo do outro usuário.
     * @throws UsuarioNaoCadastradoException Quando o usuário não está cadastrado.
     * @throws NaoPodeRealizarAcaoComInimigo 
     */	
	@Override
	public void adicionarRelacionamento(String id, String relacionado, DatabaseManager database) 
			throws AutoAmizadeException, PedidoRepetidoAmizadeException, JaAmigoException, UsuarioNaoCadastradoException, 
			NaoPodeRealizarAcaoComInimigo {
		if (!id.equals("") && database.checaLoginExiste(relacionado)) {
			
			String login = database.getLoginDoId(Integer.parseInt(id));
			
			if(login.equals(relacionado)) {
				throw new AutoAmizadeException();
			}
			
			if(!login.isBlank()) {
				
				if(database.checaAmizade(login, relacionado) && !database.checaAmizade(relacionado, login)) {
					throw new PedidoRepetidoAmizadeException();
					
				}  else if (database.checaSeUsuarioEhInimigo(login, relacionado) || database.checaSeUsuarioEhInimigo(relacionado, login)){
					
					throw new NaoPodeRealizarAcaoComInimigo(database.getAtributo(relacionado, "nome"));
	
				} else if (!database.checaAmizade(login, relacionado)) {
					
					database.insereAmizade(login, relacionado);
					
				} else if (database.checaAmizade(login, relacionado) && database.checaAmizade(relacionado, login)) {
					
					throw new JaAmigoException();		
				}
			}
		}
		else {
			throw new UsuarioNaoCadastradoException();
		}
	}
	
    /**
     * Verifica se dois usuários são amigos.
     *
     * @param login O login do primeiro usuário.
     * @param amigo O login do segundo usuário.
     * @param database O banco de dados do sistema.
     * @return true se são amigos, false caso contrário.
     */
	@Override
	public boolean EhRelacionamento(String login, String relacionado, DatabaseManager database) {
		return database.checaAmizade(login, relacionado) && database.checaAmizade(relacionado, login);
	}
	
	
    /**
     * Obtém uma lista de amigos de um usuário.
     *
     * @param login O login do usuário.
     * @param database O banco de dados do sistema.
     * @return Uma lista de amigos.
     */
	@Override	
	public String getRelacionamentos(String login, DatabaseManager database) {
		return database.listaAmigos(login);
	}

}
