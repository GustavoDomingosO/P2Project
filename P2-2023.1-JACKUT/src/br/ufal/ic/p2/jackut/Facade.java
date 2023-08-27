package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.user.Usuario;

import java.util.ArrayList;

import java.lang.Integer;

import br.ufal.ic.p2.jackut.Database.DatabaseManager;
import easyaccept.EasyAccept;

public class Facade {
	
    private final DatabaseManager databaseManager = new DatabaseManager();
    
    private String loginAtual = "";
    
    public void setloginAtual(String value) {
    	this.loginAtual = value;
    }
    
    public String getloginAtual() {
    	return this.loginAtual;
    }   
    

	public void zerarSistema(){
		databaseManager.zerarSistema();
	}
	public String getAtributoUsuario(String login, String atributo) throws Exception {
		
		if(databaseManager.checaLoginExiste(login)) {
			String retorno = databaseManager.retornaValor(login, atributo);
			if(retorno == null) {
				throw new Exception("Atributo n�o preenchido.");
			}
			return databaseManager.retornaValor(login, atributo);
		}
		
		throw new Exception("Usu�rio n�o cadastrado.");
	}
	
	public void criarUsuario(String login, String senha, String nome) throws Exception{
		if(login == null) {
			throw new Exception("Login inv�lido.");
		} else if (senha == null) {
			throw new Exception("Senha inv�lida.");			
		}
		if(!databaseManager.checaLoginExiste(login)) {
			databaseManager.insereNoCadastro(login, senha, nome);			
		}
		else {
			throw new Exception("Conta com esse nome j� existe.");
		}

	}
	
	public void encerrarSistema() {
		/*databaseManager.zerarSistema();
		databaseManager.encerraSistema();
		databaseManager.createTableIfNotExists();*/
		
	}
	
	public String abrirSessao(String login, String senha) throws Exception{
		if(!databaseManager.checaLoginExiste(login)) {
			throw new Exception("Login ou senha inv�lidos.");
		}
		else {
			if(!getAtributoUsuario(login, "senha").equals(senha)) {
				throw new Exception("Login ou senha inv�lidos.");
			}
			else {
				setloginAtual(login);
				return databaseManager.retornaValor(login, "id");
			}
		}
	}
	
	public void editarPerfil(String id, String atributo, String valor) throws Exception{
		if (!id.equals("")) {
			String login = databaseManager.loginDoId(Integer.parseInt(id));
			if(!databaseManager.checaLoginExiste(login)) {
				throw new Exception("Usu�rio n�o cadastrado.");
			} else {
				databaseManager.insereAtributo(getloginAtual(), atributo, valor);			
			}
		}
		else {
			throw new Exception("Usu�rio n�o cadastrado.");
		}

	}
	
	public boolean ehAmigo(String login, String amigo) {

		return databaseManager.temAmizade(login, amigo) && databaseManager.temAmizade(amigo, login);
	}
	
	public void adicionarAmigo(String id, String amigo) throws Exception{
		if (!id.equals("") && databaseManager.checaLoginExiste(amigo)) {
			String login = databaseManager.loginDoId(Integer.parseInt(id));
			if(login.equals(amigo)) {
				throw new Exception("Usu�rio n�o pode adicionar a si mesmo como amigo.");
			}
			if(!login.isBlank()) {
				if(databaseManager.temAmizade(login, amigo) && !databaseManager.temAmizade(amigo, login)) {
					
					throw new Exception("Usu�rio j� est� adicionado como amigo, esperando aceita��o do convite.");
					
				} else if (!databaseManager.temAmizade(login, amigo)) {
					databaseManager.adicionaAmizade(login, amigo);
				} else if (databaseManager.temAmizade(login, amigo) && databaseManager.temAmizade(amigo, login)) {
					throw new Exception("Usu�rio j� est� adicionado como amigo.");
					
				}
			}
		}
		else {
			throw new Exception("Usu�rio n�o cadastrado.");
		}
		
	}
	
	
	public String getAmigos(String login) {
		return databaseManager.listaAmigos(login);
	}
	
	public void enviarRecado (String id, String destinatario, String recado) throws Exception {
		if (!id.equals("")){
			String login = databaseManager.loginDoId(Integer.parseInt(id));
				if(!databaseManager.checaLoginExiste(login) || !databaseManager.checaLoginExiste(destinatario)) {
					throw new Exception("Usu�rio n�o cadastrado.");
				} else if (login.equals(destinatario)) {
					throw new Exception("Usu�rio n�o pode enviar recado para si mesmo.");					
				} else {
					databaseManager.armazenaRecado(login, destinatario, recado);
				}
				

		} else {
			throw new Exception("Usu�rio n�o cadastrado.");
		}
	}
	
	public String lerRecado(String id) throws Exception{
		String login = databaseManager.loginDoId(Integer.parseInt(id));
		String recado = databaseManager.recolheRecado(login);
		if(recado.equals("")) {
			throw new Exception("N�o h� recados.");			
		}else {
			return recado;			
		}
	}
	
	
}
