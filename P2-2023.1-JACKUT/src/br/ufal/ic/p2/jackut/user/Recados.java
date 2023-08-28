package br.ufal.ic.p2.jackut.user;
import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.CustomExceptions;
import br.ufal.ic.p2.jackut.exceptions.CustomExceptions.*;

/**
 * Classe abstrata que gerencia a funcionalidade de envio e recebimento de recados entre usuários.
 */

public abstract class Recados {
	
	/**
     * Construtor protegido para evitar a instanciação direta da classe.
     *
     * @throws NaoPodeInstanciar Esta exceção é lançada ao tentar instanciar a classe.
     */
	
	private Recados()throws NaoPodeInstanciar {
		throw new CustomExceptions.NaoPodeInstanciar();
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
	
	public static void enviarRecado(String id, String destinatario, String recado, DatabaseManager database) throws UsuarioNaoCadastradoException, AutoRecadoException{
		if (!id.equals("")){
			String login = database.loginDoId(Integer.parseInt(id));
				if(!database.checaLoginExiste(login) || !database.checaLoginExiste(destinatario)) {
					throw new CustomExceptions.UsuarioNaoCadastradoException();
				} 
				else if (login.equals(destinatario)) {
					throw new CustomExceptions.AutoRecadoException();			
				} 
				else {
					database.armazenaRecado(login, destinatario, recado);
				}
				
		} 
		else {
			throw new CustomExceptions.UsuarioNaoCadastradoException();
		}
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
		String login = database.loginDoId(Integer.parseInt(id));
		String recado = database.recolheRecado(login);
		if(recado.equals("")) {
			throw new CustomExceptions.SemRecadosException();
		}
		else {
			return recado;			
		}
	}
}
