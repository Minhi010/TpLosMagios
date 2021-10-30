package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import exceptions.AtraccionException;
import exceptions.MissingDataException;
import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Tipo;

public class AtraccionDAOImpl implements AtraccionDAO {
	public List<Atraccion> buscarTodos() {
		try {
			String sql = "SELECT * FROM Atraccion";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Atraccion> atracciones = new LinkedList<Atraccion>();
			while (rs.next()) {
				atracciones.add(toAtraccion(rs));
			}
			return atracciones;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Atraccion toAtraccion(ResultSet rs) throws AtraccionException, SQLException {
		int id = rs.getInt("Id_atraccion");
		String nombre = rs.getString("Nombre");
		int cupo = rs.getInt("Cupo");
		double costo = rs.getDouble("Costo");
		double tiempo = rs.getInt("Tiempo");
		Tipo tipo = Tipo.valueOf(rs.getString("Tipo_atraccion").toUpperCase());
		return new Atraccion(id, nombre, tipo, costo, tiempo, cupo);
	}

	public int actualizarAtraccion(Atraccion atraccion) {
		try {
			String sql = "UPDATE Atraccion SET cupo=? WHERE nombre=?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, atraccion.getCupo());
			st.setString(2, atraccion.getNombre());
			int rows = st.executeUpdate();
			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
