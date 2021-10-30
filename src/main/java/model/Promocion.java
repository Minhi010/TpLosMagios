package model;

import java.util.List;
import java.util.ListIterator;

import exceptions.PromocionException;

public class Promocion extends Producto {
	private double tiempo;
	protected double costo;
	protected List<Atraccion> atracciones;

	public Promocion(int id, Tipo tipoAtraccion, List<Atraccion> atracciones) throws PromocionException {
		super(id, tipoAtraccion);
		this.atracciones = validarAtraccion(atracciones, tipoAtraccion);
		this.tiempo = setTiempo(atracciones);
	}

	
	private List<Atraccion> validarAtraccion(List<Atraccion> atracciones, Tipo tipoAtraccion)
			throws PromocionException {
		for (Atraccion atraccion : atracciones) {
			if (atraccion.getTipoAtraccion() != tipoAtraccion) {
				throw new PromocionException("Una atraccion no coincide con el tipo de la promoción");
			}
		}
		return atracciones;
	}
	
	
	private double setTiempo(List<Atraccion> lista) {
	double tiempo = 0;
		for (Atraccion atraccion : lista) {
			tiempo+=atraccion.getTiempo();
		}
		return tiempo;
	}

	@Override
	public boolean esPromo() {
		return true;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public void setAtracciones(List<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}

	@Override
	public boolean tieneLugar() {
		for (Atraccion atraccion : atracciones) {
			if (!atraccion.tieneLugar())
				return false;
		}
		return true;
	}

	@Override
	public void ocuparLugar() {
		for (Atraccion atraccion : atracciones) {
			atraccion.ocuparLugar();
		}
	}

	@Override
	public boolean tengoProducto(Producto productop) {
		boolean loEncontre = false;
		ListIterator<Atraccion> itr = atracciones.listIterator();
		while (!loEncontre && itr.hasNext()) {
			loEncontre = productop.tengoProducto(itr.next());
		}
		return loEncontre;
	}

	@Override
	public String getNombre() {
		return "atracciones: " + atracciones;
	}

	@Override
	public double getTiempo() {
		return tiempo;
	}

	@Override
	public double getCosto() {
		return costo;
	}


	@Override
	public String toString() {
		return "Promocion: tiempo=" + tiempo + ", costo=" + costo + ", atracciones=" + atracciones;
	}
	
}