package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.user.*;
import br.ufal.ic.p2.jackut.comunidade.Comunidade;
import br.ufal.ic.p2.jackut.mensagem.*;
import br.ufal.ic.p2.jackut.paquera.Paquera;
import br.ufal.ic.p2.jackut.recados.Recados;
import br.ufal.ic.p2.jackut.fa.*;
import br.ufal.ic.p2.jackut.inimigo.Inimigo;
/**
 * Classe que fornece uma interface simplificada para interagir com as funcionalidades do sistema.
 */
public class Facade {
    
    private final DatabaseManager databaseManager = new DatabaseManager();
    
    private Usuario usuario = new Usuario(databaseManager);
    
    private Mensagem mensagem = new Mensagem();
    
    private Recados recado = new Recados();
    
    /**
     * Zera todas as inform��es do sistema, redefinindo-o para um estado inicial.
     */
    public void zerarSistema() {
    //databaseManager.deletaSistema();
       databaseManager.zerarSistema();
    }
    
    /**
     * Obt�m o valor de um atributo espec�fico de um usu�rio.
     *
     * @param login O login do usu�rio.
     * @param atributo O atributo desejado.
     * @return O valor do atributo do usu�rio.
     * @throws AtributoNaoPreenchidoException Quando o atributo n�o est� preenchido.
     * @throws UsuarioNaoCadastradoException Quando o usu�rio n�o est� cadastrado.
     */
    
    public String getAtributoUsuario(String login, String atributo)
            throws AtributoNaoPreenchidoException, UsuarioNaoCadastradoException {
        return usuario .getAtributoUsuario(login, atributo);
    }
    
    /**
     * Cria um novo usu�rio no sistema.
     *
     * @param login O login do novo usu�rio.
     * @param senha A senha do novo usu�rio.
     * @param nome O nome do novo usu�rio.
     * @throws SenhaInvalidaException Quando a senha � inv�lida.
     * @throws ContaJaExisteException Quando a conta j� existe.
     * @throws LoginInvalidoException Quando o login � inv�lido.
     */
    public void criarUsuario(String login, String senha, String nome)
            throws SenhaInvalidaException, ContaJaExisteException, LoginInvalidoException {
    	usuario .criarUsuario(login, senha, nome);
    }
    
    /**
     * Encerra o sistema, realizando quaisquer opera��es necess�rias de finaliza��o.
     */
    public void encerrarSistema() {
        // Atualmente n�o faz nada j� que toda apera��o j� � armazenada no db.
    }
    
    /**
     * Abre uma sess�o para um usu�rio no sistema.
     *
     * @param login O login do usu�rio.
     * @param senha A senha do usu�rio.
     * @return O id do usu�rio .
     * @throws LoginSenhaInvalidosException Quando o login e/ou senha s�o inv�lidos.
     * @throws AtributoNaoPreenchidoException Quando o atributo n�o est� preenchido.
     * @throws UsuarioNaoCadastradoException Quando o usu�rio n�o est� cadastrado.
     */
    
    public String abrirSessao(String login, String senha)
            throws LoginSenhaInvalidosException, AtributoNaoPreenchidoException, UsuarioNaoCadastradoException {
        return usuario.abrirSessao(login, senha);
    }
    
    /**
     * Edita um atributo espec�fico do perfil de um usu�rio.
     *
     * @param id O ID do usu�rio.
     * @param atributo O atributo a ser editado.
     * @param valor O novo valor do atributo.
     * @throws UsuarioNaoCadastradoException Quando o usu�rio n�o est� cadastrado.
     */
    public void editarPerfil(String id, String atributo, String valor) throws UsuarioNaoCadastradoException {
    	usuario.editarPerfil(id, atributo, valor);
    }
    
    /**
     * Verifica se dois usu�rios s�o amigos.
     *
     * @param login O login do primeiro usu�rio.
     * @param amigo O login do segundo usu�rio.
     * @return true se s�o amigos, false caso contr�rio.
     */
    
    public boolean ehAmigo(String login, String amigo) {
        return usuario.ehAmigo(login, amigo);
    }
    
    /**
     * Adiciona um novo amigo para um usu�rio.
     *
     * @param id O ID do usu�rio.
     * @param amigo O login do amigo a ser adicionado.
     * @throws AutoAmizadeException Quando se tenta adicionar a si mesmo como amigo.
     * @throws PedidoRepetidoAmizadeException Quando j� existe um pedido de amizade pendente.
     * @throws JaAmigoException Quando o usu�rio j� � amigo do outro usu�rio.
     * @throws UsuarioNaoCadastradoException Quando o usu�rio n�o est� cadastrado.
     * @throws NaoPodeRealizarAcaoComInimigo 
     */
    
    public void adicionarAmigo(String id, String amigo)
            throws AutoAmizadeException, PedidoRepetidoAmizadeException, JaAmigoException, UsuarioNaoCadastradoException, NaoPodeRealizarAcaoComInimigo {
        usuario.adicionarAmigo(id, amigo);
    }
    
    /**
     * Obt�m uma lista de amigos de um usu�rio.
     *
     * @param login O login do usu�rio.
     * @return Uma lista de amigos.
     */
    
    public String getAmigos(String login) {
        return usuario.getAmigos(login);
    }
    
    /**
     * Envia um recado de um usu�rio para outro.
     *
     * @param id O ID do remetente.
     * @param destinatario O login do destinat�rio.
     * @param recado O conte�do do recado.
     * @throws UsuarioNaoCadastradoException Quando o usu�rio n�o est� cadastrado.
     * @throws AutoRecadoException Quando se tenta enviar um recado para si mesmo.
     * @throws NaoPodeRealizarAcaoComInimigo 
     */
    
    public void enviarRecado(String id, String destinatario, String recado) throws UsuarioNaoCadastradoException, AutoRecadoException, NaoPodeRealizarAcaoComInimigo {
    	this.recado.enviarRecado(id, destinatario, recado, databaseManager);
    }
    
    /**
     * L� o recado mais recente de um usu�rio.
     *
     * @param id O ID do usu�rio.
     * @return O conte�do do recado.
     * @throws SemRecadosException Quando n�o h� recados dispon�veis.
     */
    
    public String lerRecado(String id) throws SemRecadosException {
        return this.recado.lerRecado(id, databaseManager);
    }
    
    public void criarComunidade(String sessao, String nome, String descri��o) throws ComunidadeJaExiste{
    	Comunidade.criarComunidade(sessao, nome, descri��o, databaseManager);
	}
    
    public String getDescricaoComunidade(String nome) throws ComunidadeNaoExiste {
    	return Comunidade.getDescricaoComunidade(nome, databaseManager);
    }
    
    public String getDonoComunidade(String nome) throws ComunidadeNaoExiste {
    	return Comunidade.getDonoComunidade(nome, databaseManager);
    }
    
    public String getMembrosComunidade(String nome) throws ComunidadeNaoExiste {
    	return Comunidade.getMembrosComunidade(nome, databaseManager);
    }

    public String getComunidades(String nome) throws UsuarioNaoCadastradoException{
    	return Comunidade.getComunidades(nome, databaseManager);
    }
    
	public void adicionarComunidade(String sessao, String nomeComunidade) throws JaFazParteComunidade, ComunidadeNaoExiste, UsuarioNaoCadastradoException {
		Comunidade.adicionarComunidade(sessao, nomeComunidade, databaseManager);
	}
	
	public String lerMensagem(String id) throws SemMensagens, UsuarioNaoCadastradoException{
		//databaseManager.zerarSistema();
		return this.mensagem.lerMensagem(id, databaseManager);
	}
	
	public void enviarMensagem(String id, String comunidade, String mensagem) throws UsuarioNaoCadastradoException, ComunidadeNaoExiste{
		this.mensagem.enviarMensagem(id, comunidade, mensagem, databaseManager);
	}
	
	public boolean ehFa(String login, String idolo) {
		return this.usuario.ehFa(login, idolo);
	}
	
	public void adicionarIdolo(String id, String idolo) throws JaEhIdolo, UsuarioNaoCadastradoException, NaoPodeSerFaDeSiMesmo, NaoPodeRealizarAcaoComInimigo {
		this.usuario.adicionarIdolo(id, idolo);
	}
	
	public String getFas(String login) {
		return this.usuario.getFas(login);
	}
	
	public boolean ehPaquera(int id, String paquera) {
		return this.usuario.ehPaquera(id, paquera);
	}
	
	public void adicionarPaquera(int id, String paquera) throws JaEhIdolo, UsuarioNaoCadastradoException, NaoPodeSerFaDeSiMesmo, JaEhPaquera, NaoPodeSerPaqueraDeSiMesmo, NaoPodeRealizarAcaoComInimigo {
		this.usuario.adicionarPaquera(id, paquera);
	}
	
	public String getPaqueras(int id) {
		return this.usuario.getPaqueras(id);
	}
	
	public void adicionarInimigo(String id, String inimigo) throws JaEhInimigo, UsuarioNaoCadastradoException, NaoPodeSerInimigoDeSiMesmo {
		this.usuario.adicionarInimigo(id, inimigo);
	}
	
	public void removerUsuario(String id) throws UsuarioNaoCadastradoException {
		this.usuario.removerUsuario(id);
	}
    
}
