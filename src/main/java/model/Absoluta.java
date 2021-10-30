package model;

import java.util.List;

import exceptions.PromocionException;

public class Absoluta extends Promocion {

	public Absoluta(int id, Tipo tipoAtraccion, List<Atraccion> atracciones, double nuevoCosto) throws PromocionException {
		super(id, tipoAtraccion, atracciones);
		this.costo = nuevoCosto;
	}

}