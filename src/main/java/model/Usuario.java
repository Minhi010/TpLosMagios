package model;

import java.util.LinkedList;

import dao.FactoryDAO;
import dao.UsuarioDAO;
import exceptions.UsuarioException;

public class Usuario {
	private int id;
	private String nombre;
	private double presupuesto;
	private double tiempoUsuario;
	private Tipo preferencia;
	private Itinerario itinerario = new Itinerario(new LinkedList<Producto>()) ;

	public Usuario(String nombre, double presupuesto, double tiempoUsuario, Tipo preferencia) throws UsuarioException {
		if (presupuesto < 0 || tiempoUsuario < 0) {
			throw new UsuarioException("Un usuario no fue contemplado porque tenía parámetros negativos");
		}
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoUsuario = tiempoUsuario;
		this.preferencia = preferencia;
	}
	
	@Override
	public String toString() {
		return "Usuario: " + nombre + ", presupuesto de=" + presupuesto
				+ ", un tiempo de=" + tiempoUsuario +", su preferencia es=" + preferencia;
	}

	public Tipo getPreferencia() {
		return preferencia;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public double getTiempoUsuario() {
		return tiempoUsuario;
	}

	public void agregarAItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	public Itinerario getItinerario() {
		return itinerario;
	}
	
	public boolean puedoComprar(Producto producto) {
		return producto.getCosto()<=presupuesto && producto.getTiempo()<=tiempoUsuario;
	}
	
	public void comprarPaseo(Producto producto) {
		presupuesto -= producto.getCosto();
		tiempoUsuario -= producto.getTiempo();
		itinerario.aniadirPaseo(producto);
		UsuarioDAO uDAO = FactoryDAO.getUsuarioDAO();
		uDAO.actualizarUsuario(this);
		uDAO.aniadirPaseo(producto, this);
	}
	
}