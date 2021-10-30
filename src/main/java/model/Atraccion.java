package model;

import dao.AtraccionDAO;
import dao.FactoryDAO;
import exceptions.AtraccionException;

public class Atraccion extends Producto {
	private String nombre;
	protected int cupo;
	private double costo;
	private double tiempo;
	
	
	public Atraccion(int id, String nombre, Tipo tipoAtraccion, double tiempo, double costo, int cupo) throws AtraccionException {
		super(id, tipoAtraccion);
		this.nombre = nombre;
		this.cupo = cupo;
		this.costo = costo;
		this.tiempo = tiempo;
		if (costo < 0 || tiempo < 0 || cupo < 0) {
			throw new AtraccionException("Una atracción no fue agregada como producto porque tenía parámetros negativos");
		}
	}

	public void ocuparLugar() {
		this.cupo--;
		AtraccionDAO aDAO = FactoryDAO.getAtraccionDAO();
		aDAO.actualizarAtraccion(this);
	}

	public boolean tieneLugar() {
		return this.cupo > 0;
	}

	@Override
	public boolean esPromo() {
		return false;
	}

	public boolean tengoProducto(Producto productop) {
		if (productop.esPromo()) {
			return productop.tengoProducto(this);
		}else {
			return this.equals(productop);
		}
	}

	public int getCupo() {
		return cupo;
	}

	@Override
	public double getTiempo() {
		return tiempo;
	}

	@Override
	public double getCosto() {
		return costo;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "Atraccion: nombre=" + nombre + ", cupo=" + cupo + ", costo=" + costo + ", tiempo=" + tiempo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
}