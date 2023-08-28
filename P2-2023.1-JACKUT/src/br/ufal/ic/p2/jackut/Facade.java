package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.CustomExceptions.*;
import br.ufal.ic.p2.jackut.user.UsuarioFuncoes;

import java.lang.Integer;

import easyaccept.EasyAccept;

/**
 * Classe que fornece uma interface simplificada para interagir com as funcionalidades do sistema.
 */
public class Facade {
    
    private final DatabaseManager databaseManager = new DatabaseManager();
    
    /**
     * Zera todas as informa��es do sistema, redefinindo-o para um estado inicial.
     */
    public void zerarSistema() {
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
        return UsuarioFuncoes.getAtributoUsuario(login, atributo, databaseManager);
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
        UsuarioFuncoes.criarUsuario(login, senha, nome, databaseManager);
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
        return UsuarioFuncoes.abrirSessao(login, senha, databaseManager);
    }
    
    /**
     * Edita um atributo espec�fico do perfil de um usu�rio.
     *
     * @param id O ID do usu�rio.
     * @param atributo O atributo a ser editado.
     * @param valor O novo valor do atributo.
     * @throws UsuarioNaoCadastradoException Quando o usu�rio n�o est� cadastrado.
     */
    public void editarPerfil(String id, String atributo, String valor)
            throws UsuarioNaoCadastradoException {
        UsuarioFuncoes.editarPerfil(id, atributo, valor, databaseManager);
    }
    
    /**
     * Verifica se dois usu�rios s�o amigos.
     *
     * @param login O login do primeiro usu�rio.
     * @param amigo O login do segundo usu�rio.
     * @return true se s�o amigos, false caso contr�rio.
     */
    
    public boolean ehAmigo(String login, String amigo) {
        return UsuarioFuncoes.ehAmigo(login, amigo, databaseManager);
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
     */
    
    public void adicionarAmigo(String id, String amigo)
            throws AutoAmizadeException, PedidoRepetidoAmizadeException, JaAmigoException, UsuarioNaoCadastradoException {
        UsuarioFuncoes.adicionarAmigo(id, amigo, databaseManager);
    }
    
    /**
     * Obt�m uma lista de amigos de um usu�rio.
     *
     * @param login O login do usu�rio.
     * @return Uma lista de amigos.
     */
    
    public String getAmigos(String login) {
        return UsuarioFuncoes.getAmigos(login, databaseManager);
    }
    
    /**
     * Envia um recado de um usu�rio para outro.
     *
     * @param id O ID do remetente.
     * @param destinatario O login do destinat�rio.
     * @param recado O conte�do do recado.
     * @throws UsuarioNaoCadastradoException Quando o usu�rio n�o est� cadastrado.
     * @throws AutoRecadoException Quando se tenta enviar um recado para si mesmo.
     */
    
    public void enviarRecado(String id, String destinatario, String recado)
            throws UsuarioNaoCadastradoException, AutoRecadoException {
        UsuarioFuncoes.enviarRecado(id, destinatario, recado, databaseManager);
    }
    
    /**
     * L� o recado mais recente de um usu�rio.
     *
     * @param id O ID do usu�rio.
     * @return O conte�do do recado.
     * @throws SemRecadosException Quando n�o h� recados dispon�veis.
     */
    
    public String lerRecado(String id)
            throws SemRecadosException {
        return UsuarioFuncoes.lerRecado(id, databaseManager);
    }
}

