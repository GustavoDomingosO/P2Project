package br.ufal.ic.p2.jackut.exceptions;

/**
 * Classe que define exceções personalizadas relacionadas ao sistema.
 */

public class CustomExceptions {
	
    /**
     * Exceção lançada quando um usuário não está cadastrado.
     */
	
	public static class UsuarioNaoCadastradoException extends Exception {
        public UsuarioNaoCadastradoException() {
            super("Usuário não cadastrado.");
        }
    }
	
    /**
     * Exceção lançada quando um atributo não está preenchido.
     */

    public static class AtributoNaoPreenchidoException extends Exception {
        public AtributoNaoPreenchidoException() {
            super("Atributo não preenchido.");
        }
    }
    
    /**
     * Exceção lançada quando uma conta com o mesmo nome já existe.
     */

    public static class ContaJaExisteException extends Exception {
        public ContaJaExisteException() {
            super("Conta com esse nome já existe.");
        }
    }
    
    /**
     * Exceção lançada quando um login ou senha são inválidos.
     */

    public static class LoginSenhaInvalidosException extends Exception {
        public LoginSenhaInvalidosException() {
            super("Login ou senha inválidos.");
        }
    }
    
    /**
     * Exceção lançada quando um login é inválido.
     */
    
    public static class LoginInvalidoException extends Exception {
        public LoginInvalidoException() {
            super("Login inválido.");
        }
    }
    
    /**
     * Exceção lançada quando uma senha é inválida.
    */
    
    public static class SenhaInvalidaException extends Exception {
        public SenhaInvalidaException() {
            super("Senha inválida.");
        }
    }
    
    /**
     * Exceção lançada quando um usuário tenta adicionar a si mesmo como amigo.
     */
    
    public static class AutoAmizadeException extends Exception {
        public AutoAmizadeException() {
            super("Usuário não pode adicionar a si mesmo como amigo.");
        }
    }
    
    /**
     * Exceção lançada quando um pedido de amizade já foi enviado.
     */
    
    public static class PedidoRepetidoAmizadeException extends Exception {
        public PedidoRepetidoAmizadeException() {
            super("Usuário já está adicionado como amigo, esperando aceitação do convite.");
        }
    }
    
    /**
     * Exceção lançada quando um usuário já é amigo de outro.
     */
    
    public static class JaAmigoException extends Exception {
        public JaAmigoException() {
            super("Usuário já está adicionado como amigo.");
        }
    }
    
    /**
     * Exceção lançada quando um usuário tenta enviar um recado para si mesmo.
     */
    
    public static class AutoRecadoException extends Exception {
        public AutoRecadoException() {
            super("Usuário não pode enviar recado para si mesmo.");
        }
    }
    
    /**
     * Exceção lançada quando não há recados disponíveis.
     */
    
    public static class SemRecadosException extends Exception {
        public SemRecadosException() {
            super("Não há recados.");
        }
    }
    
    /**
     * Exceção lançada quando tenta-se instanciar uma classe que não deve ser instanciada.
     */
    
    public static class NaoPodeInstanciar extends Exception {
        public NaoPodeInstanciar() {
            super("Esta classe não deve ser instanciada.");
        }
    }
}
