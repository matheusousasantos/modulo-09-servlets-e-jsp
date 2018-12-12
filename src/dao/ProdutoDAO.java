package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Produto;
import connection.SingleConnection;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO() {

		connection = SingleConnection.getConnection();
	}

	public void salvar(Produto usuario) {
		String sql ="INSERT INTO produto(nome, quantidade, valor) VALUES (?,?,?)";
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getNome());
			insert.setDouble(2, usuario.getQuantidade());
			insert.setDouble(3, usuario.getValor());
			
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
		
		String sql = "DELETE FROM produto WHERE id = '"+id+"'";
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
	
	public List<Produto> listar() throws SQLException  {
		List<Produto> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM produto";
		
			PreparedStatement list = connection.prepareStatement(sql);
			ResultSet rs = list.executeQuery();
			
			while(rs.next()) {
				
				Produto obj = new Produto();
//				Traz o Id também
				obj.setId(rs.getLong("id"));
				obj.setNome(rs.getString("nome"));
				obj.setQuantidade(rs.getDouble("quantidade"));
				obj.setValor(rs.getDouble("valor"));
				lista.add(obj);
			}
			
			return lista;
	}

	public Produto consultar(String id) {
		
		String sql = "SELECT * FROM produto WHERE id = '"+id+"'";
		try {
		PreparedStatement pmt = connection.prepareStatement(sql);
		ResultSet rs = pmt.executeQuery();
		
			if(rs.next()) {
				Produto obj = new Produto();
				
				obj.setId(rs.getLong("id"));
				obj.setNome(rs.getString("nome"));
				obj.setQuantidade(rs.getDouble("quantidade"));
				obj.setValor(rs.getDouble("valor"));
				return obj;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void atualizar(Produto produto) {
//		Vai execultar a Atualização
		String sql = "UPDATE produto SET nome = ?, quantidade = ?, valor = ? WHERE id = " +produto.getId(); 
		try {
		PreparedStatement pmt = connection.prepareStatement(sql);
		
		pmt.setString(1, produto.getNome());
		pmt.setDouble(2, produto.getQuantidade());
		pmt.setDouble(3, produto.getValor());
	
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
	
// Verifica se existe um determinado produto.
	
public boolean validarNome(String nome) {
		
		String sql = "SELECT COUNT(1) qtd FROM usuario WHERE nome = '" + nome + "'";
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