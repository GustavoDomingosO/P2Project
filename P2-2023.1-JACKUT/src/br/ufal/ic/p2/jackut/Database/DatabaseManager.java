package br.ufal.ic.p2.jackut.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseManager {
	
	private static final String DB_URL = "jdbc:sqlite:" + DatabaseManager.class.getResource("base.db");
	
    public DatabaseManager() {
        createTableIfNotExists();
    }

	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
	
	public void createTableIfNotExists() {
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
	
	public boolean checaLoginExiste(String login) {
	    boolean exists = false;
	    
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                		"SELECT COUNT(*) AS row_count FROM cadastro WHERE login = '" + login + "'")) {
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
	
	public String retornaValor(String login, String atributo) {
	    
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                		"SELECT " + atributo + " FROM cadastro WHERE login = '" + login + "'")) {
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
	
	public void encerraSistema() {
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
	}
	
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
	
	public int countEntriesWithLogin(String login) {
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
	}
	
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
