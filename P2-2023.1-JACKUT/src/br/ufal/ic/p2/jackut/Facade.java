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
     * Zera todas as informções do sistema, redefinindo-o para um estado inicial.
     */
    public void zerarSistema() {
    //databaseManager.deletaSistema();
       databaseManager.zerarSistema();
    }
    
    /**
     * Obtêm o valor de um atributo específico de um usuário.
     *
     * @param login O login do usuário.
     * @param atributo O atributo desejado.
     * @return O valor do atributo do usuário.
     * @throws AtributoNaoPreenchidoException Quando o atributo não está preenchido.
     * @throws UsuarioNaoCadastradoException Quando o usuário não está cadastrado.
     */
    
    public String getAtributoUsuario(String login, String atributo)
            throws AtributoNaoPreenchidoException, UsuarioNaoCadastradoException {
        return usuario .getAtributoUsuario(login, atributo);
    }
    
    /**
     * Cria um novo usuário no sistema.
     *
     * @param login O login do novo usuário.
     * @param senha A senha do novo usuário.
     * @param nome O nome do novo usuário.
     * @throws SenhaInvalidaException Quando a senha é inválida.
     * @throws ContaJaExisteException Quando a conta já existe.
     * @throws LoginInvalidoException Quando o login é inválido.
     */
    public void criarUsuario(String login, String senha, String nome)
            throws SenhaInvalidaException, ContaJaExisteException, LoginInvalidoException {
    	usuario .criarUsuario(login, senha, nome);
    }
    
    /**
     * Encerra o sistema, realizando quaisquer operações necessárias de finalização.
     */
    public void encerrarSistema() {
        // Atualmente não faz nada já que toda aperação já é armazenada no db.
    }
    
    /**
     * Abre uma sessão para um usuário no sistema.
     *
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @return O id do usuário .
     * @throws LoginSenhaInvalidosException Quando o login e/ou senha são inválidos.
     * @throws AtributoNaoPreenchidoException Quando o atributo não está preenchido.
     * @throws UsuarioNaoCadastradoException Quando o usuário não está cadastrado.
     */
    
    public String abrirSessao(String login, String senha)
            throws LoginSenhaInvalidosException, AtributoNaoPreenchidoException, UsuarioNaoCadastradoException {
        return usuario.abrirSessao(login, senha);
    }
    
    /**
     * Edita um atributo específico do perfil de um usuário.
     *
     * @param id O ID do usuário.
     * @param atributo O atributo a ser editado.
     * @param valor O novo valor do atributo.
     * @throws UsuarioNaoCadastradoException Quando o usuário não está cadastrado.
     */
    public void editarPerfil(String id, String atributo, String valor) throws UsuarioNaoCadastradoException {
    	usuario.editarPerfil(id, atributo, valor);
    }
    
    /**
     * Verifica se dois usuários são amigos.
     *
     * @param login O login do primeiro usuário.
     * @param amigo O login do segundo usuário.
     * @return true se são amigos, false caso contrário.
     */
    
    public boolean ehAmigo(String login, String amigo) {
        return usuario.ehAmigo(login, amigo);
    }
    
    /**
     * Adiciona um novo amigo para um usuário.
     *
     * @param id O ID do usuário.
     * @param amigo O login do amigo a ser adicionado.
     * @throws AutoAmizadeException Quando se tenta adicionar a si mesmo como amigo.
     * @throws PedidoRepetidoAmizadeException Quando já existe um pedido de amizade pendente.
     * @throws JaAmigoException Quando o usuário já é amigo do outro usuário.
     * @throws UsuarioNaoCadastradoException Quando o usuário não está cadastrado.
     * @throws NaoPodeRealizarAcaoComInimigo 
     */
    
    public void adicionarAmigo(String id, String amigo)
            throws AutoAmizadeException, PedidoRepetidoAmizadeException, JaAmigoException, UsuarioNaoCadastradoException, NaoPodeRealizarAcaoComInimigo {
        usuario.adicionarAmigo(id, amigo);
    }
    
    /**
     * Obtêm uma lista de amigos de um usuário.
     *
     * @param login O login do usuário.
     * @return Uma lista de amigos.
     */
    
    public String getAmigos(String login) {
        return usuario.getAmigos(login);
    }
    
    /**
     * Envia um recado de um usuário para outro.
     *
     * @param id O ID do remetente.
     * @param destinatario O login do destinatário.
     * @param recado O conteúdo do recado.
     * @throws UsuarioNaoCadastradoException Quando o usuário não está cadastrado.
     * @throws AutoRecadoException Quando se tenta enviar um recado para si mesmo.
     * @throws NaoPodeRealizarAcaoComInimigo 
     */
    
    public void enviarRecado(String id, String destinatario, String recado) throws UsuarioNaoCadastradoException, AutoRecadoException, NaoPodeRealizarAcaoComInimigo {
    	this.recado.enviarRecado(id, destinatario, recado, databaseManager);
    }
    
    /**
     * Lê o recado mais recente de um usuário.
     *
     * @param id O ID do usuário.
     * @return O conteúdo do recado.
     * @throws SemRecadosException Quando não há recados disponíveis.
     */
    
    public String lerRecado(String id) throws SemRecadosException {
        return this.recado.lerRecado(id, databaseManager);
    }
    
    public void criarComunidade(String sessao, String nome, String descrição) throws ComunidadeJaExiste{
    	Comunidade.criarComunidade(sessao, nome, descrição, databaseManager);
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
