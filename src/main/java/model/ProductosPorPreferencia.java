package model;

import java.util.Comparator;

public class ProductosPorPreferencia implements Comparator<Producto>{
	private Tipo tipoPreferido;
	
	public ProductosPorPreferencia(Tipo tipoPreferido) {
		this.tipoPreferido = tipoPreferido;
	}
	
	public int compare(Producto o1, Producto o2) {
		int resultado;
		if(o1.getTipoAtraccion()==tipoPreferido && o2.getTipoAtraccion()!=tipoPreferido) {
			resultado = -1;
		}
		else if(o1.getTipoAtraccion()!=tipoPreferido && o2.getTipoAtraccion()==tipoPreferido) {
			resultado = 1;
		}
		else {
			resultado = -Boolean.compare(o1.esPromo(), o2.esPromo());
			if(resultado == 0) {
				resultado = -Double.compare(o1.getCosto(), o2.getCosto());
				if(resultado == 0) {
					resultado = -Double.compare(o1.getTiempo(), o2.getTiempo());
				}
			}
		}
		return resultado;
	}
}