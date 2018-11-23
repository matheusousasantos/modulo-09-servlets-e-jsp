package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO() {

		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoJsp usuario) {
		String sql ="INSERT INTO usuario(login, senha) VALUES (?,?)";
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			
			insert.execute();
			connection.commit();
			
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public List<BeanCursoJsp> listarTodos() throws SQLException  {
		List<BeanCursoJsp> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM usuario";
		
			PreparedStatement list = connection.prepareStatement(sql);
			ResultSet rs = list.executeQuery();
			
			while(rs.next()) {
				
				BeanCursoJsp obj = new BeanCursoJsp();
				obj.setLogin(rs.getString("login"));
				obj.setSenha(rs.getString("senha"));
				
				lista.add(obj);
			}
			
			return lista;
	}
	
	

}
