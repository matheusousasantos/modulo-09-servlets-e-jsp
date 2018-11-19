package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class DAOLogin {
	
	private Connection connection;
	
	public DAOLogin() {
		
		connection = SingleConnection.getConnection();// Pegar a conexão.
		
	}
	
	public boolean validarLogin(String login, String senha) throws Exception { // Validar acesso através do login e senha.
		
		/*Vai retornar o registro de acordo com o login e senha passado por parâmetro*/
		String sql = "SELECT * FROM usuario WHERE login = '"+login+"'AND senha='"+senha+"'";//Não pode ser espações entres as aspas.
		PreparedStatement smt = connection.prepareStatement(sql);
		System.out.println(sql);
		ResultSet rs = smt.executeQuery();
		
		if(rs.next()) { //Se ele encontrar alguma resultado.
			return true;// Validou usuario
		}else {
			return false; // Não validou usuario
		}
	}

}
