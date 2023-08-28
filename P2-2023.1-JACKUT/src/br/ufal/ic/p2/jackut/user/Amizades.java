package br.ufal.ic.p2.jackut.user;

import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.CustomExceptions;
import br.ufal.ic.p2.jackut.exceptions.CustomExceptions.*;


/**
 * Classe abstrata que gerencia a funcionalidade de fazer amizades.
 */

public abstract class Amizades {

	/**
     * Construtor protegido para evitar a instancia��o direta da classe.
     *
     * @throws NaoPodeInstanciar Esta exce��o � lan�ada ao tentar instanciar a classe.
     */
	
	private Amizades() throws NaoPodeInstanciar {
		throw new CustomExceptions.NaoPodeInstanciar();
	}
	
    /**
     * Verifica se dois usu�rios s�o amigos.
     *
     * @param login O login do primeiro usu�rio.
     * @param amigo O login do segundo usu�rio.
     * @param database O banco de dados do sistema.
     * @return true se s�o amigos, false caso contr�rio.
     */
	
	public static boolean ehAmigo(String login, String amigo, DatabaseManager database){
		return database.temAmizade(login, amigo) && database.temAmizade(amigo, login);
	}
	
    /**
     * Adiciona um novo amigo para um usu�rio.
     *
     * @param id O ID do usu�rio.
     * @param amigo O login do amigo a ser adicionado.
     * @param database O banco de dados do sistema.
     * @throws AutoAmizadeException Quando se tenta adicionar a si mesmo como amigo.
     * @throws PedidoRepetidoAmizadeException Quando j� existe um pedido de amizade pendente.
     * @throws JaAmigoException Quando o usu�rio j� � amigo do outro usu�rio.
     * @throws UsuarioNaoCadastradoException Quando o usu�rio n�o est� cadastrado.
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
     * Obt�m uma lista de amigos de um usu�rio.
     *
     * @param login O login do usu�rio.
     * @param database O banco de dados do sistema.
     * @return Uma lista de amigos.
     */
	
	public static String getAmigos(String login, DatabaseManager database){
		return database.listaAmigos(login);
	}

}
