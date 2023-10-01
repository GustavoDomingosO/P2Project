package br.ufal.ic.p2.jackut.mensagem;
import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.caixa_de_entrada.CaixaDeEntrada;
import br.ufal.ic.p2.jackut.database.DatabaseManager;

public class Mensagem extends CaixaDeEntrada{
	
	public String lerMensagem (String id, DatabaseManager database) throws SemMensagens, UsuarioNaoCadastradoException{

		super.lerConteudo(id, database, "mensagem");
		
		if (!super.conteudo.isEmpty()) {
			return super.conteudo;
			
		} else {
			
			throw new SemMensagens();
		}
		
		
	}
	
	public void enviarMensagem(String id, String comunidade, String mensagem, DatabaseManager database) throws UsuarioNaoCadastradoException, ComunidadeNaoExiste {

		if (id.isEmpty()) {
			throw new UsuarioNaoCadastradoException();
		}
		if (database.checaComunidadeExiste(comunidade)) {
			String membrosComunidade = database.listaMembrosDaComunidade(comunidade);
			
			membrosComunidade = membrosComunidade.substring(1, membrosComunidade.length() - 1);
			String[] array = membrosComunidade.split("\\s*,\\s*");
			
	        for (String membros : array) {
	        	database.insereMensagemUsuario(membros, mensagem);
	        }
		} else {
			throw new ComunidadeNaoExiste();
		}
	}
	
}
