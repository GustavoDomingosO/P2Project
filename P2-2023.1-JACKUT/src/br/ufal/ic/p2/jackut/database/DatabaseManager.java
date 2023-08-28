package br.ufal.ic.p2.jackut.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe responsável pela gerência do banco de dados.
 */
public class DatabaseManager {
	
	private static final String DB_URL = "jdbc:sqlite:" + DatabaseManager.class.getResource("base.db");
	
	 /**
     * Construtor da classe DatabaseManager.
     * Cria as tabelas no banco de dados se elas não existirem.
     */
    public DatabaseManager() {
        criaTabelasSeNaoExistem();
    }
    
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
	
    /**
     * Cria as tabelas no banco de dados se elas não existirem.
     */
	public void criaTabelasSeNaoExistem() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS cadastro ("
                     + "login STRING, senha STRING, nome STRING, id INTEGER PRIMARY KEY AUTOINCREMENT,"
                     + "descricao STRING DEFAULT NULL, estadoCivil STRING DEFAULT NULL, aniversario STRING DEFAULT NULL, "
                     + "filhos STRING DEFAULT NULL, idiomas STRING DEFAULT NULL, cidadeNatal STRING DEFAULT NULL, "
                     + "estilo STRING DEFAULT NULL, fumo STRING DEFAULT NULL, bebo STRING DEFAULT NULL, moro STRING DEFAULT NULL)")) {
            statement.executeUpdate();
        } catch (SQLException e) {
        	System.err.println("Erro criando a Tabela cadastro" + e.getMessage());
        }
        
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS amizades (login STRING, amigo STRING)")) {
               statement.executeUpdate();
        } catch (SQLException e) {
       	System.err.println("Erro criando a Tabela amizades" + e.getMessage());
        }
        
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS recados (login STRING, destinatario STRING, recado STRING, id INTEGER PRIMARY KEY AUTOINCREMENT)")) {
               statement.executeUpdate();
        } catch (SQLException e) {
       	System.err.println("Erro criando a Tabela amizades" + e.getMessage());
        }
    }
	
    /**
     * Insere informações de um usuário no cadastro.
     *
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @param nome O nome do usuário.
     */
	
	public void insereNoCadastro(String login, String senha, String nome) {
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO cadastro (login, senha, nome) VALUES (?, ?, ?)")) {
            statement.setString(1, login);
            statement.setString(2, senha);
            statement.setString(3, nome);
            statement.executeUpdate();
           } catch (SQLException e) {
        	   System.err.println("Erro inserindo dados base na tabela cadastro" + e.getMessage());
           }
	}
    
	/**
     * Insere ou atualiza um atributo específico do usuário.
     *
     * @param login O login do usuário.
     * @param atributo O atributo a ser inserido ou atualizado.
     * @param valor O valor a ser inserido ou atualizado.
     */
	
	public void insereAtributo(String login, String atributo, String valor) {
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                		"UPDATE cadastro SET "+ atributo + " = ? WHERE login = ?")) {
            statement.setString(1, valor);
            statement.setString(2, login);
            statement.executeUpdate();
           } catch (SQLException e) {
        	   System.err.println("Erro inserindo um valor na tabela cadastro" + e.getMessage());
           }
	}
    
    /**
     * Obtém o login associado a um determinado ID de usuário.
     *
     * @param id O ID do usuário.
     * @return O login correspondente ao ID.
     */
	
	public String loginDoId(int id) {
		try (Connection connection = getConnection();
		        PreparedStatement statement = connection.prepareStatement(
		        		"SELECT login FROM cadastro WHERE id = ?")) {
            statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) { 
	            return resultSet.getString("login");
	        } else {
	            System.out.println("No results found for id: " + id);
	        }
			
		   } catch (SQLException e) {
			   System.err.println("Erro retornando o id: " + e.getMessage());
		   }
		return null;
	}
	
	/**
     * Verifica se um determinado login de usuário existe no banco de dados.
     *
     * @param login O login a ser verificado.
     * @return true se o login existe, false caso contrário.
     */
	
	public boolean checaLoginExiste(String login) {
	    boolean exists = false;
	    
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                		"SELECT COUNT(*) AS row_count FROM cadastro WHERE login = ?")) {
        	statement.setString(1, login);
        	ResultSet resultSet = statement.executeQuery();
	    	int count = resultSet.getInt("row_count");
	    	if (count > 0) {
	    	    exists = true;
	    	}
           } catch (SQLException e) {
        	   System.err.println("Erro checando o Login: " + e.getMessage());
           }
	    return exists;
	}
	
    /**
     * Retorna o valor de um atributo específico de um usuário.
     *
     * @param login O login do usuário.
     * @param atributo O atributo desejado.
     * @return O valor do atributo do usuário.
     */
	
	public String retornaValor(String login, String atributo) {
	    
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                		"SELECT " + atributo + " FROM cadastro WHERE login = ?")) {
        	statement.setString(1, login);        	
        	ResultSet resultSet = statement.executeQuery();
        	 if (resultSet.next()) {
                 if ("id".equals(atributo)) {
                     return String.valueOf((resultSet.getInt(atributo))); // Return id as a string
                 } else {
                     return resultSet.getString(atributo);
                 }
             }
           } catch (SQLException e) {
        	   System.err.println("Erro retornando o valor" + e.getMessage());
           }
    	return "";
	}
	
    /**
     * Zera todas as informações do sistema, limpando as tabelas do banco de dados.
     */
	
	public void zerarSistema() {
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                		"DELETE FROM cadastro")) {
               statement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro limpando tabela cadastro " + e.getMessage());
		}
        
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                		"DELETE FROM amizades")) {
               statement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro limpando tabela amizades " + e.getMessage());
		}
        
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                		"DELETE FROM recados")) {
               statement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Erro limpando tabela recados " + e.getMessage());
		}
	}
	
/*	
	public void deletaSistema() { Inutilizado
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                		"DROP TABLE IF EXISTS cadastro")) {
               statement.executeUpdate();
           } catch (SQLException e) {
        	   System.err.println("Erro encerrando o sistema" + e.getMessage());
           }
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                		"DROP TABLE IF EXISTS amizades")) {
               statement.executeUpdate();
           } catch (SQLException e) {
        	   System.err.println("Erro encerrando o sistema" + e.getMessage());
           }
        
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                		"DROP TABLE IF EXISTS recados")) {
               statement.executeUpdate();
           } catch (SQLException e) {
        	   System.err.println("Erro encerrando o sistema" + e.getMessage());
           }
	}*/
	
    /**
     * Verifica se existe uma amizade entre dois usuários.
     *
     * @param login O login do primeiro usuário.
     * @param amigo O login do segundo usuário.
     * @return true se há amizade, false caso contrário.
     */
	
	public boolean temAmizade(String login, String amigo) {
		boolean amizade1 = false;
	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(
	                 "SELECT COUNT(*) AS row_count FROM amizades WHERE login = ? AND amigo = ?")) {
	        statement.setString(1, login);
	        statement.setString(2, amigo);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            int count = resultSet.getInt("row_count");
	            if(count > 0) {
	            	amizade1 = true;
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro verificando amizade: " + e.getMessage());
	    }
	    
	    return amizade1; 
	}	
	
    /**
     * Obtém uma lista de amigos de um usuário.
     *
     * @param login O login do usuário.
     * @return Uma lista de amigos.
     */
	
	public String listaAmigos(String login) {
	    StringBuilder amigos = new StringBuilder("{"); // Use StringBuilder for efficient string concatenation
	    try (Connection connection = getConnection();
    		PreparedStatement statement = connection.prepareStatement(
                     "SELECT amigo FROM amizades WHERE login = ? AND amigo IN (SELECT login FROM amizades WHERE amigo = ?)")) {
	            statement.setString(1, login);
	            statement.setString(2, login);
	        ResultSet resultSet = statement.executeQuery();
	        boolean firstFriend = true; // To handle comma separation
	        
	        while (resultSet.next()) {
	            if (!firstFriend) {
	                amigos.append(","); // Add a comma separator after the first friend
	            }
	            amigos.append(resultSet.getString("amigo"));
	            firstFriend = false;
	        }
	        amigos.append("}");
	    } catch (SQLException e) {
	        System.err.println("Erro listando amigos: " + e.getMessage());
	    }
	    return amigos.toString();
	}

    /**
     * Adiciona uma amizade entre dois usuários.
     *
     * @param login O login do primeiro usuário.
     * @param amigo O login do segundo usuário.
     */
	
	public void adicionaAmizade(String login, String amigo) {
		if(!temAmizade(login, amigo)) {
			String comando = "INSERT INTO amizades (login, amigo) VALUES (?, ?)";
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(comando)) {
			            statement.setString(1, login);
			            statement.setString(2, amigo);
			            statement.executeUpdate();
			} catch (SQLException e) {
				System.err.println("Erro inserindo no comando amizade" + e.getMessage());
			}
		}
	}
	/*
	public int contaRegistroComLogin(String login) {
	    int count = 0;
	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(
	                 "SELECT COUNT(*) AS entry_count FROM cadastro WHERE login = ?")) {
	        statement.setString(1, login);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            count = resultSet.getInt("entry_count");
	        }
	    } catch (SQLException e) {
	        System.err.println("Error counting entries with login: " + e.getMessage());
	    }
	    return count;
	}*/
	
    /**
     * Armazena um recado enviado por um usuário para outro.
     *
     * @param login O login do remetente do recado.
     * @param destinatario O login do destinatário do recado.
     * @param recado O conteúdo do recado.
     */
	
	public void armazenaRecado(String login, String destinatario, String recado) {
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
     * Recolhe o recado mais recente de um usuário.
     *
     * @param login O login do usuário.
     * @return O conteúdo do recado.
     */
	
	public String recolheRecado(String login) {
		String messagem = "";
		int id = -1;
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT recado FROM recados WHERE destinatario = ? ORDER BY id ASC LIMIT 1")) {
		            statement.setString(1, login);
		            ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
        	messagem = resultSet.getString("recado");
        }
      
		} catch (SQLException e) {
			System.err.println("Erro lendo recado no banco de dados: " + e.getMessage());
		}
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT id FROM recados WHERE destinatario = ? ORDER BY id ASC LIMIT 1")) {
		            statement.setString(1, login);
		            ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
        	id = resultSet.getInt("id");
        }
      
		} catch (SQLException e) {
			System.err.println("Erro lendo recado no banco de dados: " + e.getMessage());
		}
		if(id != -1) {
	        try (Connection connection = getConnection();
	                PreparedStatement statement = connection.prepareStatement(
	                		"DELETE FROM recados WHERE id = ?")) {
	            statement.setInt(1, id);
	               statement.executeUpdate();
			} catch (SQLException e) {
				System.err.println("Erro deletando recado " + e.getMessage());
			}	
		}
		
		return messagem;
	}

}
