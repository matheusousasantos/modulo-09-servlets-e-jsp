package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class DAOLogin {
	
	private Connection connection;
	
	public DAOLogin() {
		
		connection = SingleConnection.getConnection();// Pegar a conex�o.
		
	}
	
	public boolean validarLogin(String login, String senha) throws Exception { // Validar acesso atrav�s do login e senha.
		
		/*Vai retornar o registro de acordo com o login e senha passado por par�metro*/
		String sql = "SELECT * FROM usuario WHERE login = '"+login+"'AND senha='"+senha+"'";//N�o pode ser espa��es entres as aspas.
		PreparedStatement smt = connection.prepareStatement(sql);
		System.out.println(sql);
		ResultSet rs = smt.executeQuery();
		
		if(rs.next()) { //Se ele encontrar alguma resultado.
			return true;// Validou usuario
		}else {
			return false; // N�o validou usuario
		}
	}

}
