package br.ufal.ic.p2.jackut.user;
import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.CustomExceptions;
import br.ufal.ic.p2.jackut.exceptions.CustomExceptions.*;

/**
 * Classe abstrata que gerencia a funcionalidade de envio e recebimento de recados entre usu�rios.
 */

public abstract class Recados {
	
	/**
     * Construtor protegido para evitar a instancia��o direta da classe.
     *
     * @throws NaoPodeInstanciar Esta exce��o � lan�ada ao tentar instanciar a classe.
     */
	
	private Recados()throws NaoPodeInstanciar {
		throw new CustomExceptions.NaoPodeInstanciar();
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
     * L� o recado mais recente de um usu�rio.
     *
     * @param id O ID do usu�rio.
     * @param database O banco de dados do sistema.
     * @return O conte�do do recado.
     * @throws SemRecadosException Quando n�o h� recados dispon�veis.
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
