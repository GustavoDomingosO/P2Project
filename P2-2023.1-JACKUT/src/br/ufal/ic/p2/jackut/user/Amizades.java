package br.ufal.ic.p2.jackut.user;

import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.CustomExceptions;
import br.ufal.ic.p2.jackut.exceptions.CustomExceptions.*;


/**
 * Classe abstrata que gerencia a funcionalidade de fazer amizades.
 */

public abstract class Amizades {

	/**
     * Construtor protegido para evitar a instanciação direta da classe.
     *
     * @throws NaoPodeInstanciar Esta exceção é lançada ao tentar instanciar a classe.
     */
	
	private Amizades() throws NaoPodeInstanciar {
		throw new CustomExceptions.NaoPodeInstanciar();
	}
	
    /**
     * Verifica se dois usuários são amigos.
     *
     * @param login O login do primeiro usuário.
     * @param amigo O login do segundo usuário.
     * @param database O banco de dados do sistema.
     * @return true se são amigos, false caso contrário.
     */
	
	public static boolean ehAmigo(String login, String amigo, DatabaseManager database){
		return database.temAmizade(login, amigo) && database.temAmizade(amigo, login);
	}
	
    /**
     * Adiciona um novo amigo para um usuário.
     *
     * @param id O ID do usuário.
     * @param amigo O login do amigo a ser adicionado.
     * @param database O banco de dados do sistema.
     * @throws AutoAmizadeException Quando se tenta adicionar a si mesmo como amigo.
     * @throws PedidoRepetidoAmizadeException Quando já existe um pedido de amizade pendente.
     * @throws JaAmigoException Quando o usuário já é amigo do outro usuário.
     * @throws UsuarioNaoCadastradoException Quando o usuário não está cadastrado.
     */	
	
	public static void adicionarAmigo(String id, String amigo, DatabaseManager database) throws AutoAmizadeException, PedidoRepetidoAmizadeException, JaAmigoException, UsuarioNaoCadastradoException{
		if (!id.equals("") && database.checaLoginExiste(amigo)) {
			
			String login = database.loginDoId(Integer.parseInt(id));
			
			if(login.equals(amigo)) {
				throw new CustomExceptions.AutoAmizadeException();
			}
			
			if(!login.isBlank()) {
				
				if(database.temAmizade(login, amigo) && !database.temAmizade(amigo, login)) {
					throw new CustomExceptions.PedidoRepetidoAmizadeException();
					
				} else if (!database.temAmizade(login, amigo)) {
					database.adicionaAmizade(login, amigo);
				}
				
				else if (database.temAmizade(login, amigo) && database.temAmizade(amigo, login)) {
					throw new CustomExceptions.JaAmigoException();	
				}
			}
		}
		else {
			throw new CustomExceptions.UsuarioNaoCadastradoException();
		}
	}
	
    /**
     * Obtém uma lista de amigos de um usuário.
     *
     * @param login O login do usuário.
     * @param database O banco de dados do sistema.
     * @return Uma lista de amigos.
     */
	
	public static String getAmigos(String login, DatabaseManager database){
		return database.listaAmigos(login);
	}

}
