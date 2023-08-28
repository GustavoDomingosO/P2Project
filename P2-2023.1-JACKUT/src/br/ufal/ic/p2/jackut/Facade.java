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
     * Zera todas as informações do sistema, redefinindo-o para um estado inicial.
     */
    public void zerarSistema() {
        databaseManager.zerarSistema();
    }
    
    /**
     * Obtém o valor de um atributo específico de um usuário.
     *
     * @param login O login do usuário.
     * @param atributo O atributo desejado.
     * @return O valor do atributo do usuário.
     * @throws AtributoNaoPreenchidoException Quando o atributo não está preenchido.
     * @throws UsuarioNaoCadastradoException Quando o usuário não está cadastrado.
     */
    
    public String getAtributoUsuario(String login, String atributo)
            throws AtributoNaoPreenchidoException, UsuarioNaoCadastradoException {
        return UsuarioFuncoes.getAtributoUsuario(login, atributo, databaseManager);
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
        UsuarioFuncoes.criarUsuario(login, senha, nome, databaseManager);
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
        return UsuarioFuncoes.abrirSessao(login, senha, databaseManager);
    }
    
    /**
     * Edita um atributo específico do perfil de um usuário.
     *
     * @param id O ID do usuário.
     * @param atributo O atributo a ser editado.
     * @param valor O novo valor do atributo.
     * @throws UsuarioNaoCadastradoException Quando o usuário não está cadastrado.
     */
    public void editarPerfil(String id, String atributo, String valor)
            throws UsuarioNaoCadastradoException {
        UsuarioFuncoes.editarPerfil(id, atributo, valor, databaseManager);
    }
    
    /**
     * Verifica se dois usuários são amigos.
     *
     * @param login O login do primeiro usuário.
     * @param amigo O login do segundo usuário.
     * @return true se são amigos, false caso contrário.
     */
    
    public boolean ehAmigo(String login, String amigo) {
        return UsuarioFuncoes.ehAmigo(login, amigo, databaseManager);
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
     */
    
    public void adicionarAmigo(String id, String amigo)
            throws AutoAmizadeException, PedidoRepetidoAmizadeException, JaAmigoException, UsuarioNaoCadastradoException {
        UsuarioFuncoes.adicionarAmigo(id, amigo, databaseManager);
    }
    
    /**
     * Obtém uma lista de amigos de um usuário.
     *
     * @param login O login do usuário.
     * @return Uma lista de amigos.
     */
    
    public String getAmigos(String login) {
        return UsuarioFuncoes.getAmigos(login, databaseManager);
    }
    
    /**
     * Envia um recado de um usuário para outro.
     *
     * @param id O ID do remetente.
     * @param destinatario O login do destinatário.
     * @param recado O conteúdo do recado.
     * @throws UsuarioNaoCadastradoException Quando o usuário não está cadastrado.
     * @throws AutoRecadoException Quando se tenta enviar um recado para si mesmo.
     */
    
    public void enviarRecado(String id, String destinatario, String recado)
            throws UsuarioNaoCadastradoException, AutoRecadoException {
        UsuarioFuncoes.enviarRecado(id, destinatario, recado, databaseManager);
    }
    
    /**
     * Lê o recado mais recente de um usuário.
     *
     * @param id O ID do usuário.
     * @return O conteúdo do recado.
     * @throws SemRecadosException Quando não há recados disponíveis.
     */
    
    public String lerRecado(String id)
            throws SemRecadosException {
        return UsuarioFuncoes.lerRecado(id, databaseManager);
    }
}

