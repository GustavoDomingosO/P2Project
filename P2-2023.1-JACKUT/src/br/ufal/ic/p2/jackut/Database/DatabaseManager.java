package br.ufal.ic.p2.jackut.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe respons�vel pela ger�ncia do banco de dados.
 */
public class DatabaseManager implements CoordenadorDeListagem, CoordenadorDeTabela, CoordenadorDeCaixaDeEntrada, CoordenadorDeChecagem,
	CoordenadorDeGetters, CoordenadorDeInsercao, CoordenadorDeAuxiliares, CoordenadorDeLimpeza{
	
	private static final String DB_URL = "jdbc:sqlite:" + DatabaseManager.class.getResource("base.db");
	
	private String[] tabelas = {"cadastro", "amizades", "recados", "comunidade", "membrosComunidade",
			"mensagemUsuario", "usuarioIdolo", "usuarioPaquera", "usuarioInimigo"}; 
	
	 /**
     * Construtor da classe DatabaseManager.
     * Cria as tabelas no banco de dados se elas n�o existirem.
     */
    public DatabaseManager() {
        criaTabelasSeNaoExistem();
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
	
    /**
     * Cria as tabelas no banco de dados se elas n�o existirem.
     */
	@Override
    public void criaTabelasSeNaoExistem() {
	    aux_criarTabela("cadastro",
	            "login STRING, senha STRING, nome STRING, id INTEGER PRIMARY KEY AUTOINCREMENT,"
	                    + "descricao STRING DEFAULT NULL, estadoCivil STRING DEFAULT NULL, aniversario STRING DEFAULT NULL, "
	                    + "filhos STRING DEFAULT NULL, idiomas STRING DEFAULT NULL, cidadeNatal STRING DEFAULT NULL, "
	                    + "estilo STRING DEFAULT NULL, fumo STRING DEFAULT NULL, bebo STRING DEFAULT NULL, moro STRING DEFAULT NULL");

	    aux_criarTabela("amizades", "login STRING, amigo STRING");

	    aux_criarTabela("recados", "login STRING, destinatario STRING, recado STRING, id INTEGER PRIMARY KEY AUTOINCREMENT");

	    aux_criarTabela("comunidade", "nome STRING, descri��o STRING, login STRING");

	    aux_criarTabela("membrosComunidade", "nome STRING, login STRING");

	    aux_criarTabela("mensagemUsuario", "login STRING, mensagem STRING, id INTEGER PRIMARY KEY AUTOINCREMENT");

	    aux_criarTabela("usuarioIdolo", "login STRING, idolo STRING");

	    aux_criarTabela("usuarioPaquera", "login STRING, paquera STRING");

	    aux_criarTabela("usuarioInimigo", "login STRING, inimigo STRING");
    }
	
	@Override
	public void aux_criarTabela(String nomeTabela, String colunas) {
		try (Connection connection = getConnection();
		         PreparedStatement statement = connection.prepareStatement(
		                 "CREATE TABLE IF NOT EXISTS " + nomeTabela + " (" + colunas + ")")) {
		        statement.executeUpdate();
		    } catch (SQLException e) {
		        System.err.println("Erro criando a tabela " + nomeTabela + ": " + e.getMessage());
		    }
	}
	
    /**
     * Zera todas as informa��es do sistema, limpando as tabelas do banco de dados.
     */

	@Override
	public void zerarSistema() {
		for(String tabela : this.tabelas) {
			aux_zerarTabela(tabela);
		}
	}
	
	@Override
	public void aux_zerarTabela(String nomeTabela) {
	    try (Connection connection = getConnection();
	            PreparedStatement statement = connection.prepareStatement(
	            		"DELETE FROM " + nomeTabela)) {
	       statement.executeUpdate();
	    } catch (SQLException e) {
	        System.err.println("Erro limpando tabela " + nomeTabela + ": " + e.getMessage());
	    }
	}
	
	@Override
	public void aux_executarSQL(String query, String... valores) {
	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        for (int i = 0; i < valores.length; i++) {
	            statement.setString(i + 1, valores[i]);
	        }
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        System.err.println("Erro executando SQL: " + e.getMessage());
	    }
	}
	
	/**
     * Insere informa��es de um usu�rio no cadastro.
     *
     * @param login O login do usu�rio.
     * @param senha A senha do usu�rio.
     * @param nome O nome do usu�rio.
     */
	
	@Override
	public void insereNoCadastro(String login, String senha, String nome) {
	    String query = "INSERT INTO cadastro (login, senha, nome) VALUES (?, ?, ?)";
	    aux_executarSQL(query, login, senha, nome);
	}

	@Override	
	public void insereNovaComunidade(String nome, String descricao, String dono) {
		String query = "INSERT INTO comunidade (nome, descri��o, login) VALUES (?, ?, ?)";
		aux_executarSQL(query,nome,descricao,dono);
	}

	@Override
	public void insereNovoMembroNaComunidade(String nome, String membro) {
		String query = "INSERT INTO membrosComunidade (nome, login) VALUES (?, ?)";
		aux_executarSQL(query,nome, membro);
	}

	@Override
	public void insereMensagemUsuario(String usuario, String mensagem) {
		String query = "INSERT INTO mensagemUsuario (login, mensagem) VALUES (?, ?)";
		aux_executarSQL(query, usuario, mensagem);
	}
    
	/**
     * Insere ou atualiza um atributo espec�fico do usu�rio.
     *
     * @param login O login do usu�rio.
     * @param atributo O atributo a ser inserido ou atualizado.
     * @param valor O valor a ser inserido ou atualizado.
     */
	@Override
	public void insereAtributo(String login, String atributo, String valor) {
		String query = "UPDATE cadastro SET "+ atributo + " = ? WHERE login = ?";
		aux_executarSQL(query, valor, login);
	}

	@Override
	public void insereIdolo(String usuario, String idolo) {
		String query = "INSERT INTO usuarioIdolo (login, idolo) VALUES (?, ?)";
		aux_executarSQL(query, usuario, idolo);
	}
	
	@Override
	public void inserePaquera(String usuario, String paquera) {
		String query = "INSERT INTO usuarioPaquera (login, paquera) VALUES (?, ?)";
		aux_executarSQL(query, usuario, paquera);
	}
	
	@Override
	public void insereInimigo(String usuario, String inimigo) {
		String query = "INSERT INTO usuarioInimigo (login, inimigo) VALUES (?, ?)";
		aux_executarSQL(query, usuario, inimigo);
	}
	
    /**
     * Armazena um recado enviado por um usu�rio para outro.
     *
     * @param login O login do remetente do recado.
     * @param destinatario O login do destinat�rio do recado.
     * @param recado O conte�do do recado.
     */
	@Override
	public void insereRecado(String login, String destinatario, String recado) {
		String comando = "INSERT INTO recados (login, destinatario, recado) VALUES (?, ?, ?)";
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(comando)) {
		            statement.setString(1, login);
		            statement.setString(2, destinatario);
		            statement.setString(3, recado);
		            statement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro inserindo recado no banco de dados: " + e.getMessage());
		}
	}
	
    /**
     * Adiciona uma amizade entre dois usu�rios.
     *
     * @param login O login do primeiro usu�rio.
     * @param amigo O login do segundo usu�rio.
     */
	@Override
	public void insereAmizade(String login, String amigo) {
		String query = "INSERT INTO amizades (login, amigo) VALUES (?, ?)";
		if(!checaAmizade(login, amigo)) {
			aux_executarSQL(query,login, amigo);
		}
	}
	
	@Override	
	public boolean aux_checagemSQL(String query, String... valores) {
	    boolean exists = false;
		
	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        for (int i = 0; i < valores.length; i++) {
	            statement.setString(i + 1, valores[i]);
	        }
	        ResultSet resultSet = statement.executeQuery();
	        exists = aux_getRowCount(resultSet) > 0;
	    } catch (SQLException e) {
	        System.err.println("Erro checando a exist�ncia do registro: " + e.getMessage());
	    }

	    return exists;
	}
	
	@Override
	public int aux_getRowCount(ResultSet resultSet) throws SQLException {
	    return resultSet.next() ? resultSet.getInt("row_count") : 0;
	}
	
	/**
     * Verifica se um determinado login de usu�rio existe no banco de dados.
     *
     * @param login O login a ser verificado.
     * @return true se o login existe, false caso contr�rio.
     */
	
	@Override
	public boolean checaLoginExiste(String login) {
		String query = "SELECT COUNT(*) AS row_count FROM cadastro WHERE login = ?";
		return aux_checagemSQL(query, login);
	}
	
	@Override
	public boolean checaSeIdDeUsuarioExiste(int id) {
		String query = "SELECT COUNT(*) AS row_count FROM cadastro WHERE id = ?";
		return aux_checagemSQL(query,  Integer.toString(id));
	}
	
	@Override
	public boolean checaComunidadeExiste(String nome) {
		String query ="SELECT COUNT(*) AS row_count FROM comunidade WHERE nome = ?";
		return aux_checagemSQL(query, nome);
	}
	
    /**
     * Verifica se existe uma amizade entre dois usu�rios.
     *
     * @param login O login do primeiro usu�rio.
     * @param amigo O login do segundo usu�rio.
     * @return true se h� amizade, false caso contr�rio.
     */
	
	@Override
	public boolean checaAmizade(String login, String amigo) {
		String query = "SELECT COUNT(*) AS row_count FROM amizades WHERE login = ? AND amigo = ?";
		return aux_checagemSQL(query,login,amigo);
	}
	
	@Override
	public boolean checaSeUsuarioEhFa(String usuario, String idolo) {
		String query = "SELECT COUNT(*) AS row_count FROM usuarioIdolo WHERE login = ? AND idolo = ?";
		return aux_checagemSQL(query,usuario,idolo);
	}
	
	@Override
	public boolean checaSeUsuarioEhPaquera(String usuario,String paquera) {
		String query = "SELECT COUNT(*) AS row_count FROM usuarioPaquera WHERE login = ? AND paquera = ?";
		return aux_checagemSQL(query,usuario,paquera);
	}
	
	@Override
	public boolean checaSeUsuarioEhInimigo(String usuario,String inimigo) {
		String query = "SELECT COUNT(*) AS row_count FROM usuarioInimigo WHERE login = ? AND inimigo = ?";
		return aux_checagemSQL(query,usuario,inimigo);
	}
	
	@Override
	public String aux_consultaSQL(String query, String... valores) {
		try (Connection connection = getConnection();
		         PreparedStatement statement = connection.prepareStatement(query)) {
		        for (int i = 0; i < valores.length; i++) {
		            statement.setString(i + 1, valores[i]);
		        }

		        ResultSet resultSet = statement.executeQuery();
		        if (resultSet.next()) {
		            return resultSet.getString(1);
		        }
		    } catch (SQLException e) {
		        System.err.println("Erro na consulta ao banco: " + e.getMessage());
		    }
		    return "";
	}
	
    /**
     * Obt�m o login associado a um determinado ID de usu�rio.
     *
     * @param id O ID do usu�rio.
     * @return O login correspondente ao ID ou null caso n�o exista.
     */	
	@Override
	public String getLoginDoId(int id) {
	   String query = "SELECT login FROM cadastro WHERE id = ?";
	   String resultado = aux_consultaSQL(query,Integer.toString(id));
	   if(resultado.equals("")) {
		   return null;
	   } else {
		   return resultado;
	   }
	}

    /**
     * Retorna o valor de um atributo espec�fico de um usu�rio.
     *
     * @param login O login do usu�rio.
     * @param atributo O atributo desejado.
     * @return O valor do atributo do usu�rio.
     */
	
	@Override
	public String getAtributo(String login, String atributo) {
		String query = "SELECT " + atributo + " FROM cadastro WHERE login = ?";
		return aux_consultaSQL(query, login);
	}
	
	@Override
	public String getDescricao(String nome) {
	    String query = "SELECT descri��o FROM comunidade WHERE nome = ?";
	    return aux_consultaSQL(query, nome);
	}
	
	@Override
	public String getComunidadePeloDono(String dono) {
	    String query = "SELECT nome FROM comunidade WHERE login = ?";
	    return aux_consultaSQL(query, dono);
	}
	
	@Override
	public String getNomeDaComunidade(String nome) {
	    String query = "SELECT login FROM comunidade WHERE nome = ?";
	    return aux_consultaSQL(query, nome);
	}
	
	@Override
	public void aux_deletaTabela(String nomeTabela) {
		try (Connection connection = getConnection();
	            PreparedStatement statement = connection.prepareStatement(
	            		"DROP TABLE IF EXISTS " + nomeTabela)) {
	       statement.executeUpdate();
	    } catch (SQLException e) {
	        System.err.println("Erro deletando tabela " + nomeTabela + ": " + e.getMessage());
	    }
	}
	
	@Override
	public void deletaSistema() { //Inutilizado
		for(String tabela : this.tabelas) {
			aux_deletaTabela(tabela);
		}
	}
	
	@Override
	public void removeUsuario(String login) {
		
		removeTodosOsMembrosDaComunidade(getComunidadePeloDono(login));
  		for (String tabela : this.tabelas) {
  			try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(
            		"DELETE FROM " +  tabela + " WHERE login = ?")) {
            statement.setString(1, login);
            statement.executeUpdate();
			} catch (SQLException e) {
				System.err.println("Erro deletando informa��es em " + tabela + " " + e.getMessage());
			}	
		}
	}

	@Override
	public void removeTodosOsMembrosDaComunidade(String nome) {
		String membrosComunidade = listaMembrosDaComunidade(nome);
		
		membrosComunidade = membrosComunidade.substring(1, membrosComunidade.length() - 1);
		String[] array = membrosComunidade.split("\\s*,\\s*");
		
        for (String membros : array) {
	        try (Connection connection = getConnection();
	                PreparedStatement statement = connection.prepareStatement(
	                		"DELETE FROM membrosComunidade WHERE login = ?")) {
	            statement.setString(1, membros);
	               statement.executeUpdate();
			} catch (SQLException e) {
				System.err.println("Erro deletando membros da comunidade " + e.getMessage());
			}	
        }
	}
	
	@Override
	public String aux_executeListagem(String query, String parameter, String columnName, String errorMessage) {
	    StringBuilder result = new StringBuilder("{");
	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, parameter);
	        if(columnName.equals("amigo")) statement.setString(2, parameter);
	        ResultSet resultSet = statement.executeQuery();
	        boolean firstItem = true;
	        
	        while (resultSet.next()) {
	            if (!firstItem) {
	                result.append(",");
	            }
	            result.append(resultSet.getString(columnName));
	            firstItem = false;
	        }
	        result.append("}");
	    } catch (SQLException e) {
	        System.err.println("Erro " + errorMessage + ": " + e.getMessage());
	    }
	    return result.toString();
	}
	
    /**
     * Obt�m uma lista de amigos de um usu�rio.
     *
     * @param login O login do usu�rio.
     * @return Uma lista de amigos.
     */
	
	@Override
	public String listaAmigos(String login) {
	    String query = "SELECT amigo FROM amizades WHERE login = ? AND amigo IN (SELECT login FROM amizades WHERE amigo = ?)";
	    return aux_executeListagem(query, login, "amigo", "listando amigos");
	}
	
	@Override
	public String listaFas(String idolo) {
		String query = "SELECT login FROM usuarioIdolo WHERE idolo = ?";
	    return aux_executeListagem(query, idolo, "login", "listando f�s de " + idolo);
	}
	
	@Override
	public String listaPaqueras(String usuario) {
		String query = "SELECT paquera FROM usuarioPaquera WHERE login = ?";
	    return aux_executeListagem(query, usuario, "paquera", "listando paqueras de " + usuario);
	}
	
	@Override
	public String listaMembrosDaComunidade(String nome) {
		String query =  "SELECT login FROM membrosComunidade WHERE nome = ?";
	    return aux_executeListagem(query, nome, "login", "listando membros de " + nome);
	}
	
	@Override
	public String listaComunidadeDoMembro(String membro) {
		String query = "SELECT nome FROM membrosComunidade WHERE login = ?";
	    return aux_executeListagem(query, membro, "nome", "listando comunidades de " + membro);
	}
	
	@Override
	public String aux_lerConteudo(String usuario, String query, String parameter) {
	    String conteudo = "";
	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, usuario);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	        	conteudo = resultSet.getString(parameter);
	        }

	    } catch (SQLException e) {
	        System.err.println("Erro lendo conteudo: " + e.getMessage());
	    }
	    return conteudo;
	}
	
	@Override
	public int aux_buscarID(String query, String... valores) {
		int id = -1;
		
	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        for (int i = 0; i < valores.length; i++) {
	            statement.setString(i + 1, valores[i]);
	        }
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	        	id = resultSet.getInt("id");
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro buscando ID: " + e.getMessage());
	    }

	    return id;
	}
	
	@Override
	public void aux_deletarMensagemUsuario(int id, String query) {
	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, id);
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        System.err.println("Erro deletando conteudo: " + e.getMessage());
	    }
	}
	
    /**
     * Recolhe o recado mais recente de um usu�rio.
     *
     * @param login O login do usu�rio.
     * @return O conte�do do recado.
     */
	
	@Override
	public String recolheRecado(String login) {
		String query = "SELECT recado FROM recados WHERE destinatario = ? ORDER BY id ASC LIMIT 1";
		int id = -1;
		
	    String recado = aux_lerConteudo(login,query, "recado");
	    
	    query = "SELECT id FROM recados WHERE destinatario = ? ORDER BY id ASC LIMIT 1";
	    
	    id = aux_buscarID(query, login);
	    
	    if (id != -1) {
	        aux_deletarMensagemUsuario(id, "DELETE FROM recados WHERE id = ?");
	    }
	    return recado;
	}

	@Override
	public String recolheMessagem(String usuario) {
		String query = "SELECT mensagem FROM mensagemUsuario WHERE login = ? ORDER BY id ASC LIMIT 1";
		int id = -1;
		
	    String mensagem = aux_lerConteudo(usuario,query, "mensagem");
	    
	    query = "SELECT id FROM mensagemUsuario WHERE login = ? AND mensagem = ? ORDER BY id ASC LIMIT 1";
	    
	    id = aux_buscarID(query, usuario, mensagem);
	    
	    if (id != -1) {
	        aux_deletarMensagemUsuario(id, "DELETE FROM mensagemUsuario WHERE id = ?");
	    }
	    return mensagem;
	}


}
