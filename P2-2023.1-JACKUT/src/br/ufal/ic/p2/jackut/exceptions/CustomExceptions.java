package br.ufal.ic.p2.jackut.exceptions;

/**
 * Classe que define exce��es personalizadas relacionadas ao sistema.
 */

public class CustomExceptions {
	
    /**
     * Exce��o lan�ada quando um usu�rio n�o est� cadastrado.
     */
	
	public static class UsuarioNaoCadastradoException extends Exception {
        public UsuarioNaoCadastradoException() {
            super("Usu�rio n�o cadastrado.");
        }
    }
	
    /**
     * Exce��o lan�ada quando um atributo n�o est� preenchido.
     */

    public static class AtributoNaoPreenchidoException extends Exception {
        public AtributoNaoPreenchidoException() {
            super("Atributo n�o preenchido.");
        }
    }
    
    /**
     * Exce��o lan�ada quando uma conta com o mesmo nome j� existe.
     */

    public static class ContaJaExisteException extends Exception {
        public ContaJaExisteException() {
            super("Conta com esse nome j� existe.");
        }
    }
    
    /**
     * Exce��o lan�ada quando um login ou senha s�o inv�lidos.
     */

    public static class LoginSenhaInvalidosException extends Exception {
        public LoginSenhaInvalidosException() {
            super("Login ou senha inv�lidos.");
        }
    }
    
    /**
     * Exce��o lan�ada quando um login � inv�lido.
     */
    
    public static class LoginInvalidoException extends Exception {
        public LoginInvalidoException() {
            super("Login inv�lido.");
        }
    }
    
    /**
     * Exce��o lan�ada quando uma senha � inv�lida.
    */
    
    public static class SenhaInvalidaException extends Exception {
        public SenhaInvalidaException() {
            super("Senha inv�lida.");
        }
    }
    
    /**
     * Exce��o lan�ada quando um usu�rio tenta adicionar a si mesmo como amigo.
     */
    
    public static class AutoAmizadeException extends Exception {
        public AutoAmizadeException() {
            super("Usu�rio n�o pode adicionar a si mesmo como amigo.");
        }
    }
    
    /**
     * Exce��o lan�ada quando um pedido de amizade j� foi enviado.
     */
    
    public static class PedidoRepetidoAmizadeException extends Exception {
        public PedidoRepetidoAmizadeException() {
            super("Usu�rio j� est� adicionado como amigo, esperando aceita��o do convite.");
        }
    }
    
    /**
     * Exce��o lan�ada quando um usu�rio j� � amigo de outro.
     */
    
    public static class JaAmigoException extends Exception {
        public JaAmigoException() {
            super("Usu�rio j� est� adicionado como amigo.");
        }
    }
    
    /**
     * Exce��o lan�ada quando um usu�rio tenta enviar um recado para si mesmo.
     */
    
    public static class AutoRecadoException extends Exception {
        public AutoRecadoException() {
            super("Usu�rio n�o pode enviar recado para si mesmo.");
        }
    }
    
    /**
     * Exce��o lan�ada quando n�o h� recados dispon�veis.
     */
    
    public static class SemRecadosException extends Exception {
        public SemRecadosException() {
            super("N�o h� recados.");
        }
    }
    
    /**
     * Exce��o lan�ada quando tenta-se instanciar uma classe que n�o deve ser instanciada.
     */
    
    public static class NaoPodeInstanciar extends Exception {
        public NaoPodeInstanciar() {
            super("Esta classe n�o deve ser instanciada.");
        }
    }
}
