package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Telefone;
import connection.SingleConnection;

public class TelefoneDAO {

	private Connection connection;

	public TelefoneDAO() {

		connection = SingleConnection.getConnection();
	}

	public void salvar(Telefone telefone) {
		String sql = "INSERT INTO Telefone(numero, tipo, usuario) VALUES (?,?,?)";
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());

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

		String sql = "DELETE FROM Telefone WHERE id = '" + id + "'";
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

	public List<Telefone> listar(Long usuario) throws SQLException {
		List<Telefone> lista = new ArrayList<>();

		String sql = "SELECT * FROM Telefone WHERE usuario =" + usuario;

		PreparedStatement list = connection.prepareStatement(sql);
		ResultSet rs = list.executeQuery();

		while (rs.next()) {

			Telefone obj = new Telefone();
			// Traz o Id também
			obj.setId(rs.getLong("id"));
			obj.setNumero(rs.getString("numero"));
			obj.setTipo(rs.getString("tipo"));
			obj.setUsuario(rs.getLong("usuario"));
			lista.add(obj);
		}

		return lista;
	}

}