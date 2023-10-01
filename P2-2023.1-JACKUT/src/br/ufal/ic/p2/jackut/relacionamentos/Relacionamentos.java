package br.ufal.ic.p2.jackut.relacionamentos;

import br.ufal.ic.p2.jackut.database.DatabaseManager;
import br.ufal.ic.p2.jackut.exceptions.AutoAmizadeException;
import br.ufal.ic.p2.jackut.exceptions.JaAmigoException;
import br.ufal.ic.p2.jackut.exceptions.JaEhIdolo;
import br.ufal.ic.p2.jackut.exceptions.JaEhInimigo;
import br.ufal.ic.p2.jackut.exceptions.JaEhPaquera;
import br.ufal.ic.p2.jackut.exceptions.NaoPodeRealizarAcaoComInimigo;
import br.ufal.ic.p2.jackut.exceptions.NaoPodeSerFaDeSiMesmo;
import br.ufal.ic.p2.jackut.exceptions.NaoPodeSerInimigoDeSiMesmo;
import br.ufal.ic.p2.jackut.exceptions.NaoPodeSerPaqueraDeSiMesmo;
import br.ufal.ic.p2.jackut.exceptions.PedidoRepetidoAmizadeException;
import br.ufal.ic.p2.jackut.exceptions.UsuarioNaoCadastradoException;

public interface Relacionamentos {
	public void adicionarRelacionamento(String id, String relacionado, DatabaseManager database) throws NaoPodeRealizarAcaoComInimigo, UsuarioNaoCadastradoException, NaoPodeSerFaDeSiMesmo, JaEhIdolo, AutoAmizadeException, PedidoRepetidoAmizadeException, JaAmigoException, JaEhInimigo, NaoPodeSerInimigoDeSiMesmo, NaoPodeSerPaqueraDeSiMesmo, JaEhPaquera;
	public boolean EhRelacionamento(String login, String relacionado, DatabaseManager database);
	public String getRelacionamentos(String login, DatabaseManager database);
}
