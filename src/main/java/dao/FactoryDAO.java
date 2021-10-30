package dao;

public class FactoryDAO {
	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}
	public static ItinerarioDAO getItinerarioDAO() {
		return new ItinerarioDAOImpl();
	}
	public static PromocionDAO getPromocionDAO() {
		return new PromocionDAOImpl();
	}
	public static AtraccionDAO getAtraccionDAO() {
		return new AtraccionDAOImpl();
	}
}
