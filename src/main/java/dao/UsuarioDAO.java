package dao;


import java.util.List;

import model.Producto;
import model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario>{
	public List<Usuario> buscarTodos();
	
	public int actualizarUsuario(Usuario usuario);

	public int aniadirPaseo(Producto producto, Usuario usuario);
}
