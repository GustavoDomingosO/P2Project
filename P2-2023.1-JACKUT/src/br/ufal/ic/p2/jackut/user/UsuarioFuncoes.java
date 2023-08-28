package br.ufal.ic.p2.jackut.user;
import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.CustomExceptions;
import br.ufal.ic.p2.jackut.exceptions.CustomExceptions.*;

/**
 * Classe abstrata que gerencia as funcionalidades do usuário.
 */

public abstract class UsuarioFuncoes {

	/**
     * Construtor protegido para evitar a instanciação direta da classe.
     *
     * @throws NaoPodeInstanciar Esta exceção é lançada ao tentar instanciar a classe.
     */
	
	private UsuarioFuncoes() throws NaoPodeInstanciar {
		throw new CustomExceptions.NaoPodeInstanciar();
	}
	
    /**
     * Cria um novo usuário no sistema.
     *
     * @param login O login do novo usuário.
     * @param senha A senha do novo usuário.
     * @param nome O nome do novo usuário.
     * @param database O banco de dados do sistema.
     * @throws SenhaInvalidaException Quando a senha é inválida.
     * @throws ContaJaExisteException Quando a conta já existe.
     * @throws LoginInvalidoException Quando o login é inválido.
     */
	
	public static void criarUsuario(String login, String senha, String nome, DatabaseManager database)  throws CustomExceptions.SenhaInvalidaException, ContaJaExisteException, LoginInvalidoException{
		if(login == null) {
			throw new CustomExceptions.LoginInvalidoException(); 
		} 
		else if (senha == null) {
			throw new CustomExceptions.SenhaInvalidaException();
		}
		if(!database.checaLoginExiste(login)) {
			database.insereNoCadastro(login, senha, nome);			
		}
		else {
			throw new CustomExceptions.ContaJaExisteException();	
		}
	}
	
    /**
     * Edita um atributo específico do perfil de um usuário.
     *
     * @param id O ID do usuário.
     * @param atributo O atributo a ser editado.
     * @param valor O novo valor do atributo.
     * @param database O banco de dados do sistema.
     * @throws UsuarioNaoCadastradoException Quando o usuário não está cadastrado.
     */
	
	public static void editarPerfil(String id, String atributo, String valor, DatabaseManager database) throws UsuarioNaoCadastradoException{
		if (!id.equals("")) {
			String login = database.loginDoId(Integer.parseInt(id));
			if(!database.checaLoginExiste(login)) {
				throw new CustomExceptions.UsuarioNaoCadastradoException();
			} else {
				database.insereAtributo(login, atributo, valor);			
			}
		}
		else {
			throw new CustomExceptions.UsuarioNaoCadastradoException();
		}
	}
	
    /**
     * Obtém o valor de um atributo específico de um usuário.
     *
     * @param login O login do usuário.
     * @param atributo O atributo desejado.
     * @param database O banco de dados do sistema.
     * @return O valor do atributo do usuário.
     * @throws AtributoNaoPreenchidoException Quando o atributo não está preenchido.
     * @throws UsuarioNaoCadastradoException Quando o usuário não está cadastrado.
     */
	
	public static String getAtributoUsuario(String login, String atributo, DatabaseManager database) throws AtributoNaoPreenchidoException, UsuarioNaoCadastradoException {
		if(database.checaLoginExiste(login)) {
			String retorno = database.retornaValor(login, atributo);
			if(retorno == null) {
				throw new CustomExceptions.AtributoNaoPreenchidoException();
			}
			return database.retornaValor(login, atributo);
		}
		throw new CustomExceptions.UsuarioNaoCadastradoException();
	}	
	
    /**
     * Abre uma sessão para um usuário no sistema.
     *
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @param database O banco de dados do sistema.
     * @return O id do usuário .
     * @throws LoginSenhaInvalidosException Quando o login e/ou senha são inválidos.
     * @throws AtributoNaoPreenchidoException Quando o atributo não está preenchido.
     * @throws UsuarioNaoCadastradoException Quando o usuário não está cadastrado.
     */
	
	public static String abrirSessao(String login, String senha, DatabaseManager database) throws AtributoNaoPreenchidoException, UsuarioNaoCadastradoException, LoginSenhaInvalidosException {
		if(!database.checaLoginExiste(login)) {
			throw new CustomExceptions.LoginSenhaInvalidosException();
		}
		else {
			if(!getAtributoUsuario(login, "senha", database).equals(senha)) {
				throw new CustomExceptions.LoginSenhaInvalidosException();
			}
			else {
				return database.retornaValor(login, "id");
			}
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
	
	public static boolean ehAmigo(String login, String amigo, DatabaseManager database) {
		return Amizades.ehAmigo(login, amigo, database);
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
	
	public static void adicionarAmigo(String id, String amigo, DatabaseManager database) throws AutoAmizadeException, PedidoRepetidoAmizadeException, JaAmigoException, UsuarioNaoCadastradoException {
		Amizades.adicionarAmigo(id, amigo, database);
	}

    /**
     * Obtém uma lista de amigos de um usuário.
     *
     * @param login O login do usuário.
     * @param database O banco de dados do sistema.
     * @return Uma lista de amigos.
     */
	
	public static String getAmigos(String login, DatabaseManager database) {
		return Amizades.getAmigos(login, database);
	}

    /**
     * Envia um recado de um usuário para outro.
     *
     * @param id O ID do remetente.
     * @param destinatario O login do destinatário.
     * @param recado O conteúdo do recado.
     * @param database O banco de dados do sistema.
     * @throws UsuarioNaoCadastradoException Quando o usuário não está cadastrado.
     * @throws AutoRecadoException Quando se tenta enviar um recado para si mesmo.
     */
	
	public static void enviarRecado(String id, String destinatario, String recado, DatabaseManager database) throws UsuarioNaoCadastradoException, AutoRecadoException {
		Recados.enviarRecado(id, destinatario, recado, database);
	}

    /**
     * Lê o recado mais recente de um usuário.
     *
     * @param id O ID do usuário.
     * @param database O banco de dados do sistema.
     * @return O conteúdo do recado.
     * @throws SemRecadosException Quando não há recados disponíveis.
     */
	
	public static String lerRecado(String id, DatabaseManager database) throws SemRecadosException {
		return Recados.lerRecado(id, database);
	}
	
}
