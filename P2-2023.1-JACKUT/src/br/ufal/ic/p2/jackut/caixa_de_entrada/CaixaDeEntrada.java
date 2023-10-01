package br.ufal.ic.p2.jackut.caixa_de_entrada;

import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.AutoRecadoException;
import br.ufal.ic.p2.jackut.exceptions.ComunidadeNaoExiste;
import br.ufal.ic.p2.jackut.exceptions.NaoPodeRealizarAcaoComInimigo;
import br.ufal.ic.p2.jackut.exceptions.SemMensagens;
import br.ufal.ic.p2.jackut.exceptions.SemRecadosException;
import br.ufal.ic.p2.jackut.exceptions.UsuarioNaoCadastradoException;

public class CaixaDeEntrada {
	
	protected String login;
	protected String conteudo = "";	

	public void lerConteudo(String id, DatabaseManager database, String tipo){
		
		this.login = database.getLoginDoId(Integer.parseInt(id));
		
		if(tipo.equals("recado")){
			this.conteudo = database.recolheRecado(login);
		} else {
			this.conteudo = database.recolheMessagem(login);
		}
	}

}
