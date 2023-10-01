package br.ufal.ic.p2.jackut.recados;
import br.ufal.ic.p2.jackut.caixa_de_entrada.CaixaDeEntrada;
import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.*;

/**
 * Classe abstrata que gerencia a funcionalidade de envio e recebimento de recados entre usu�rios.
 */

public class Recados extends CaixaDeEntrada{
	
	/**
     * Construtor protegido para evitar a instancia��o direta da classe.
     *
     * @throws NaoPodeInstanciar Esta exce��o � lan�ada ao tentar instanciar a classe.
     */
	
	public Recados() {

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
     * @throws NaoPodeRealizarAcaoComInimigo 
     */
	
	public void enviarRecado(String id, String destinatario, String recado, DatabaseManager database) throws UsuarioNaoCadastradoException, AutoRecadoException, NaoPodeRealizarAcaoComInimigo{
		if (!id.isEmpty()){
			String login = database.getLoginDoId(Integer.parseInt(id));
				if(!database.checaLoginExiste(login) || !database.checaLoginExiste(destinatario)) {
					
					throw new UsuarioNaoCadastradoException();
					
				} else if (login.equals(destinatario)) {
					
					throw new AutoRecadoException();			
					
				} else if (database.checaSeUsuarioEhInimigo(login, destinatario) || database.checaSeUsuarioEhInimigo(destinatario, login)){
					
					throw new NaoPodeRealizarAcaoComInimigo(database.getAtributo(destinatario, "nome"));
					
				} else {
					database.insereRecado(login, destinatario, recado);
				}
				
		} 
		else {
			throw new UsuarioNaoCadastradoException();
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
	
	public String lerRecado(String id, DatabaseManager database) throws SemRecadosException {

		super.lerConteudo(id, database, "recado");
		
		if(!super.conteudo.isEmpty()) {
			return super.conteudo;		
		}
		else {
			throw new SemRecadosException();
		}
	}
	
	// Se motivo == 1 ent�o � Aviso de paquera.
	public static void recadoDoSistema(int motivo, String destinatario, String recado, DatabaseManager database) {
		database.insereRecado("Sistema Jackut", destinatario, recado);
	}
}
