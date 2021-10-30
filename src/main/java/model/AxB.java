package model;

import java.util.List;

import exceptions.PromocionException;

public class AxB extends Promocion {

	
	public AxB(int id, Tipo tipoAtraccion, List<Atraccion> atracciones) throws PromocionException {
		super(id, tipoAtraccion, atracciones);
		this.costo = setCosto();
	}

	private double setCosto() {
		double costo = 0;
		for (Atraccion atraccion : atracciones) {
			costo+=atraccion.getCosto();
		}
		costo-=atracciones.get(atracciones.size()-1).getCosto();
		return costo;
	}

}