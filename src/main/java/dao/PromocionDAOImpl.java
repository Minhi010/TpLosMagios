package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import exceptions.AtraccionException;
import exceptions.MissingDataException;
import exceptions.PromocionException;
import jdbc.ConnectionProvider;
import model.Absoluta;
import model.Atraccion;
import model.AxB;
import model.Porcentual;
import model.Promocion;
import model.Tipo;
import model.Usuario;

public class PromocionDAOImpl implements PromocionDAO {

	public List<Promocion> buscarTodos(List<Atraccion>atracciones) {
		Map<String, Atraccion> mapaAtracciones = crearMapaAtraccion(atracciones); 
		try {
			String sql = "SELECT Promocion.Id_promocion, Promocion.Nombre, Promocion.Tipo_atraccion, Promocion.Valor, group_concat(Atraccion) AS 'Atracciones' FROM Promocion\n"
					+ "JOIN Atracciones_promos on Atracciones_promos.Id_promocion = Promocion.Id_promocion\n"
					+ "GROUP BY Promocion.Id_promocion";
			List<Promocion> promo = new ArrayList<Promocion>();
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			
			while (rs.next()) {
				promo.add(toPromo(rs, mapaAtracciones));
			}
			return promo;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private Map<String, Atraccion> crearMapaAtraccion(List<Atraccion>atracciones){
		Map<String, Atraccion> mapA= new HashMap<String, Atraccion>();
		
		for (Atraccion atraccion : atracciones) {
			mapA.put(atraccion.getNombre(), atraccion);
		}
		return mapA;
		
	}
	
	
	private Promocion toPromo(ResultSet rs, Map<String, Atraccion> mapaAtraccion) throws SQLException, PromocionException {
		List <Atraccion> atracciones = new LinkedList<Atraccion>();
		String [] nombreAtracciones = rs.getString("Atracciones").split(",");//Moria,Mordor 
		
		for (String string : nombreAtracciones) {
			atracciones.add(mapaAtraccion.get(string));
		}
		Promocion p;
		if(rs.getString("Nombre").equals("PromoAbsoluta")) {
			p = new Absoluta(rs.getInt("Id_promocion"), Tipo.valueOf(rs.getString("Tipo_atraccion").toUpperCase()), atracciones, rs.getDouble("Valor"));
		}else if(rs.getString("Nombre").equals("PromoPorcentual")) {
			p = new Porcentual(rs.getInt("Id_promocion"), Tipo.valueOf(rs.getString("Tipo_atraccion").toUpperCase()), atracciones, rs.getDouble("Valor"));
		}else{
			p = new AxB(rs.getInt("Id_promocion"), Tipo.valueOf(rs.getString("Tipo_atraccion").toUpperCase()), atracciones);
		}
		return p;
	}

	

}
