package br.ufal.ic.p2.jackut.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CoordenadorDeAuxiliares {
	void aux_zerarTabela(String nomeTabela);
	void aux_criarTabela(String nomeTabela, String colunas);
	void aux_executarSQL(String query, String... valores);
	boolean aux_checagemSQL(String query, String... valores);
	int aux_getRowCount(ResultSet resultSet) throws SQLException;
	String aux_consultaSQL(String query, String... valores);
	void aux_deletaTabela(String nomeTabela);
	String aux_executeListagem(String query, String parameter, String columnName, String errorMessage);
	String aux_lerConteudo(String usuario, String query, String parameter);
	int aux_buscarID(String query, String... valores);
	void aux_deletarMensagemUsuario(int id, String query);
}
