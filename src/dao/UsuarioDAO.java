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
		String sql ="INSERT INTO usuario(login, senha, nome) VALUES (?,?,?)";
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
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
	
	public void delete(String id) {
		
		String sql = "DELETE FROM usuario WHERE id = '"+id+"'";
		try {
			PreparedStatement pmt = connection.prepareStatement(sql);
			pmt.execute();
			
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public List<BeanCursoJsp> listar() throws SQLException  {
		List<BeanCursoJsp> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM usuario";
		
			PreparedStatement list = connection.prepareStatement(sql);
			ResultSet rs = list.executeQuery();
			
			while(rs.next()) {
				
				BeanCursoJsp obj = new BeanCursoJsp();
//				Traz o Id também
				obj.setId(rs.getLong("id"));
				obj.setLogin(rs.getString("login"));
				obj.setSenha(rs.getString("senha"));
				obj.setNome(rs.getString("nome"));
				
				lista.add(obj);
			}
			
			return lista;
	}

	public BeanCursoJsp consultar(String id) {
		
		String sql = "SELECT * FROM usuario WHERE id = '"+id+"'";
		try {
		PreparedStatement pmt = connection.prepareStatement(sql);
		ResultSet rs = pmt.executeQuery();
		
			if(rs.next()) {
				BeanCursoJsp obj = new BeanCursoJsp();
//				Traz o Id também
				obj.setId(rs.getLong("id"));
				obj.setLogin(rs.getString("login"));
				obj.setSenha(rs.getString("senha"));
				obj.setNome(rs.getString("nome"));
				
				return obj;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void atualizar(BeanCursoJsp usuario) {
//		Vai execultar a Atualização
		String sql = "UPDATE usuario SET login = ?, senha = ?, nome = ? WHERE id =" +usuario.getId(); 
		try {
		PreparedStatement pmt = connection.prepareStatement(sql);
		pmt.setString(1, usuario.getLogin());
		pmt.setString(2, usuario.getSenha());
		pmt.setString(3, usuario.getNome());
		connection.commit();
		
		pmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
public boolean validarLogin(String login) {
		
		String sql = "SELECT COUNT(1) qtd FROM usuario WHERE login = '" +login+ "'";
		try {
		PreparedStatement pmt = connection.prepareStatement(sql);
		ResultSet rs = pmt.executeQuery();
		
			if(rs.next()) {
				
				return rs.getInt("qtd") <= 0;
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	

}
