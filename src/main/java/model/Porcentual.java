package model;

import java.util.List;

import exceptions.PromocionException;

public class Porcentual extends Promocion {

	public Porcentual(int id, Tipo tipoAtraccion, List<Atraccion> atracciones, double descuento) throws PromocionException {
		super(id, tipoAtraccion, atracciones);
		this.costo = setCosto(descuento);
	}

	private double setCosto(double descuento) {
		double costo = 0;
		for (Atraccion atraccion : atracciones) {
			costo+=atraccion.getCosto();
		}
		costo -= costo*descuento;
		return costo;
	}


}