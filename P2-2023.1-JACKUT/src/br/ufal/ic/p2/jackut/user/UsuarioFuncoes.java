package br.ufal.ic.p2.jackut.user;
import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.CustomExceptions;
import br.ufal.ic.p2.jackut.exceptions.CustomExceptions.*;

/**
 * Classe abstrata que gerencia as funcionalidades do usu�rio.
 */

public abstract class UsuarioFuncoes {

	/**
     * Construtor protegido para evitar a instancia��o direta da classe.
     *
     * @throws NaoPodeInstanciar Esta exce��o � lan�ada ao tentar instanciar a classe.
     */
	
	private UsuarioFuncoes() throws NaoPodeInstanciar {
		throw new CustomExceptions.NaoPodeInstanciar();
	}
	
    /**
     * Cria um novo usu�rio no sistema.
     *
     * @param login O login do novo usu�rio.
     * @param senha A senha do novo usu�rio.
     * @param nome O nome do novo usu�rio.
     * @param database O banco de dados do sistema.
     * @throws SenhaInvalidaException Quando a senha � inv�lida.
     * @throws ContaJaExisteException Quando a conta j� existe.
     * @throws LoginInvalidoException Quando o login � inv�lido.
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
     * Edita um atributo espec�fico do perfil de um usu�rio.
     *
     * @param id O ID do usu�rio.
     * @param atributo O atributo a ser editado.
     * @param valor O novo valor do atributo.
     * @param database O banco de dados do sistema.
     * @throws UsuarioNaoCadastradoException Quando o usu�rio n�o est� cadastrado.
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
     * Obt�m o valor de um atributo espec�fico de um usu�rio.
     *
     * @param login O login do usu�rio.
     * @param atributo O atributo desejado.
     * @param database O banco de dados do sistema.
     * @return O valor do atributo do usu�rio.
     * @throws AtributoNaoPreenchidoException Quando o atributo n�o est� preenchido.
     * @throws UsuarioNaoCadastradoException Quando o usu�rio n�o est� cadastrado.
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
     * Abre uma sess�o para um usu�rio no sistema.
     *
     * @param login O login do usu�rio.
     * @param senha A senha do usu�rio.
     * @param database O banco de dados do sistema.
     * @return O id do usu�rio .
     * @throws LoginSenhaInvalidosException Quando o login e/ou senha s�o inv�lidos.
     * @throws AtributoNaoPreenchidoException Quando o atributo n�o est� preenchido.
     * @throws UsuarioNaoCadastradoException Quando o usu�rio n�o est� cadastrado.
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
     * Verifica se dois usu�rios s�o amigos.
     *
     * @param login O login do primeiro usu�rio.
     * @param amigo O login do segundo usu�rio.
     * @param database O banco de dados do sistema.
     * @return true se s�o amigos, false caso contr�rio.
     */
	
	public static boolean ehAmigo(String login, String amigo, DatabaseManager database) {
		return Amizades.ehAmigo(login, amigo, database);
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
	
	public static void adicionarAmigo(String id, String amigo, DatabaseManager database) throws AutoAmizadeException, PedidoRepetidoAmizadeException, JaAmigoException, UsuarioNaoCadastradoException {
		Amizades.adicionarAmigo(id, amigo, database);
	}

    /**
     * Obt�m uma lista de amigos de um usu�rio.
     *
     * @param login O login do usu�rio.
     * @param database O banco de dados do sistema.
     * @return Uma lista de amigos.
     */
	
	public static String getAmigos(String login, DatabaseManager database) {
		return Amizades.getAmigos(login, database);
	}

    /**
     * Envia um recado de um usu�rio para outro.
     *
     * @param id O ID do remetente.
     * @param destinatario O login do destinat�rio.
     * @param recado O conte�do do recado.
     * @param database O banco de dados do sistema.
     * @throws UsuarioNaoCadastradoException Quando o usu�rio n�o est� cadastrado.
     * @throws AutoRecadoException Quando se tenta enviar um recado para si mesmo.
     */
	
	public static void enviarRecado(String id, String destinatario, String recado, DatabaseManager database) throws UsuarioNaoCadastradoException, AutoRecadoException {
		Recados.enviarRecado(id, destinatario, recado, database);
	}

    /**
     * L� o recado mais recente de um usu�rio.
     *
     * @param id O ID do usu�rio.
     * @param database O banco de dados do sistema.
     * @return O conte�do do recado.
     * @throws SemRecadosException Quando n�o h� recados dispon�veis.
     */
	
	public static String lerRecado(String id, DatabaseManager database) throws SemRecadosException {
		return Recados.lerRecado(id, database);
	}
	
}
