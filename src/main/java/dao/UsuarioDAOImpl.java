package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import exceptions.MissingDataException;
import exceptions.UsuarioException;
import jdbc.ConnectionProvider;
import model.Producto;
import model.Tipo;
import model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	public List<Usuario> buscarTodos() {
		try {
			String sql = "SELECT * FROM usuario";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Usuario> usuarios = new LinkedList<Usuario>();
			while (rs.next()) {
				usuarios.add(toUsuario(rs));
			}
			return usuarios;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario toUsuario(ResultSet rs) throws SQLException, UsuarioException {

		String nombre = rs.getString("Nombre");
		Double presupuesto = rs.getDouble("Presupuesto");
		Double tiempo = rs.getDouble("Tiempo");
		Tipo preferencia = Tipo.valueOf(rs.getString("Preferencia").toUpperCase());

		return new Usuario(nombre, presupuesto, tiempo, preferencia);

	}

	public int actualizarUsuario(Usuario usuario) {

		try {
			String sql = "UPDATE Usuario SET Presupuesto = ?, Tiempo = ? WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDouble(1, usuario.getPresupuesto());
			st.setDouble(2, usuario.getTiempoUsuario());
			st.setString(3, usuario.getNombre());
			int rs = st.executeUpdate();
			return rs;

		} catch (SQLException e) {
			throw new MissingDataException(e);
		}

	}

	public int aniadirPaseo(Producto producto, Usuario usuario) {
		try {
			String sql;
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement st;
			if (producto.esPromo()) {
				sql = "INSERT INTO itinerario (usuario, id_promocion, tipo_producto) VALUES (?,?,?)";
				st = conn.prepareStatement(sql);
				st.setString(1, usuario.getNombre());
				st.setInt(2, producto.getId());
				st.setString(3, "Promocion");
			} else {
				sql = "INSERT INTO itinerario (usuario, atraccion, tipo_producto) VALUES (?,?,?)";
				st = conn.prepareStatement(sql);
				st.setString(1, usuario.getNombre());
				st.setString(2, producto.getNombre());
				st.setString(3, "Atraccion");
			}
			int rs = st.executeUpdate();
			return rs;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}

	}

}
