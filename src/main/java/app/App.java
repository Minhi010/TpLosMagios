package app;

import java.util.List;

import dao.AtraccionDAO;
import dao.FactoryDAO;
import dao.PromocionDAO;
import dao.UsuarioDAO;
import model.Atraccion;
import model.Promocion;
import model.TierraMedia;
import model.Usuario;

public class App {

	public static void main(String[] args) {
		UsuarioDAO uDAO = FactoryDAO.getUsuarioDAO();
		List<Usuario> usuarios = uDAO.buscarTodos();
		AtraccionDAO aDAO = FactoryDAO.getAtraccionDAO();
		List<Atraccion> atracciones = aDAO.buscarTodos();
		PromocionDAO pDAO = FactoryDAO.getPromocionDAO();
		List<Promocion> promo = pDAO.buscarTodos(atracciones);
		TierraMedia tm = new TierraMedia(usuarios, atracciones, promo);
		tm.ofrecerATodos();
	}

}
