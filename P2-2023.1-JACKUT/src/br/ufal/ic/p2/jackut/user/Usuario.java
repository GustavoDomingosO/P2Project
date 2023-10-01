package br.ufal.ic.p2.jackut.user;
import br.ufal.ic.p2.jackut.amizade.*;
import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.fa.Fa;
import br.ufal.ic.p2.jackut.fa.FaConcreto;
import br.ufal.ic.p2.jackut.inimigo.Inimigo;
import br.ufal.ic.p2.jackut.inimigo.InimigoConcreto;
import br.ufal.ic.p2.jackut.paquera.Paquera;
import br.ufal.ic.p2.jackut.paquera.PaqueraConcreta;

/**
 * Classe abstrata que gerencia as funcionalidades do usu�rio.
 */

public class Usuario {
	
	private DatabaseManager database = new DatabaseManager();
	
	private PaqueraConcreta paquera = new PaqueraConcreta();
	
	private AmizadesConcreta amizades = new AmizadesConcreta();
	
	private FaConcreto fa = new FaConcreto();
	
	private InimigoConcreto inimigo = new InimigoConcreto();

	/**
     * Construtor protegido para evitar a instancia��o direta da classe.
     *
     * @throws NaoPodeInstanciar Esta exce��o � lan�ada ao tentar instanciar a classe.
     */
	
	public Usuario(DatabaseManager databaseManager){
		this.database = databaseManager;
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
	
	public void criarUsuario(String login, String senha, String nome)  throws SenhaInvalidaException, ContaJaExisteException, LoginInvalidoException{
		if(login == null) {
			throw new LoginInvalidoException(); 
		} 
		else if (senha == null) {
			throw new SenhaInvalidaException();
		}
		if(!this.database.checaLoginExiste(login)) {
			this.database.insereNoCadastro(login, senha, nome);			
		}
		else {
			throw new ContaJaExisteException();	
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
	
	public void editarPerfil(String id, String atributo, String valor) throws UsuarioNaoCadastradoException{
		if (!id.isEmpty()) {
			String login = this.database.getLoginDoId(Integer.parseInt(id));
			if(!this.database.checaLoginExiste(login)) {
				throw new UsuarioNaoCadastradoException();
			} else {
				this.database.insereAtributo(login, atributo, valor);			
			}
		}
		else {
			throw new UsuarioNaoCadastradoException();
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
	
	public String getAtributoUsuario(String login, String atributo) throws AtributoNaoPreenchidoException, UsuarioNaoCadastradoException {
		if(this.database.checaLoginExiste(login)) {
			String retorno = this.database.getAtributo(login, atributo);
			if(retorno == null) {
				throw new AtributoNaoPreenchidoException();
			}
			return this.database.getAtributo(login, atributo);
		}
		throw new UsuarioNaoCadastradoException();
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
	
	public String abrirSessao(String login, String senha) throws AtributoNaoPreenchidoException, UsuarioNaoCadastradoException, LoginSenhaInvalidosException {
		if(!this.database.checaLoginExiste(login)) {
			throw new LoginSenhaInvalidosException();
		}
		else {
			if(!getAtributoUsuario(login, "senha").equals(senha)) {
				throw new LoginSenhaInvalidosException();
			}
			else {
				return this.database.getAtributo(login, "id");
			}
		}
	}

	public void removerUsuario(String id) throws UsuarioNaoCadastradoException {
		int idValue = Integer.parseInt(id); 
		if (this.database.checaSeIdDeUsuarioExiste(idValue)) {
			String login = this.database.getLoginDoId(idValue);
			if(this.database.checaLoginExiste(login)) {
				this.database.removeUsuario(login);
			} else {
				throw new UsuarioNaoCadastradoException();
			}
		}  else {
			throw new UsuarioNaoCadastradoException();
		}

	}
	
	public boolean ehPaquera(int id, String paquera) {
		String usuario = database.getLoginDoId(id);
		
		return this.paquera.EhRelacionamento(usuario, paquera,this.database);
	}
	
	public void adicionarPaquera(int id, String paquera) throws NaoPodeRealizarAcaoComInimigo, UsuarioNaoCadastradoException, NaoPodeSerPaqueraDeSiMesmo, JaEhPaquera {
		String idString = Integer.toString(id);
		this.paquera.adicionarRelacionamento(idString, paquera, this.database);
	}
	
	public String getPaqueras(int id) {
		String usuario = database.getLoginDoId(id);
		return this.paquera.getRelacionamentos(usuario, this.database);
	}
	
	public boolean ehAmigo(String login, String amigo) {
		return this.amizades.EhRelacionamento(login, amigo, this.database);
	}
	
	public void adicionarAmigo(String id, String amigo) throws AutoAmizadeException, PedidoRepetidoAmizadeException, JaAmigoException, UsuarioNaoCadastradoException, NaoPodeRealizarAcaoComInimigo {
		this.amizades.adicionarRelacionamento(id, amigo, this.database);
	}
	
	public String getAmigos(String login) {
		return this.amizades.getRelacionamentos(login, this.database);
	}
	
	public boolean ehFa(String login, String idolo) {
		return this.fa.EhRelacionamento(login, idolo, this.database);
	}
	
	public void adicionarIdolo(String id, String idolo) throws JaEhIdolo, UsuarioNaoCadastradoException, NaoPodeSerFaDeSiMesmo, NaoPodeRealizarAcaoComInimigo {
		this.fa.adicionarRelacionamento(id, idolo, this.database);
	}
	
	public String getFas(String login) {
		return this.fa.getRelacionamentos(login, this.database);
	}
	
	public void adicionarInimigo(String id, String inimigo) throws JaEhInimigo, UsuarioNaoCadastradoException, NaoPodeSerInimigoDeSiMesmo {
		this.inimigo.adicionarRelacionamento(id, inimigo, this.database);
	}
	
}
