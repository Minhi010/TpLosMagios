package dao;

import java.util.List;

import model.Atraccion;

public interface AtraccionDAO extends GenericDAO<Atraccion> {
	public List<Atraccion> buscarTodos();
	
	public int actualizarAtraccion(Atraccion atraccion);
}
