package model;

import java.util.List;

public class Itinerario {
	private List<Producto> paseo;
	
	public Itinerario(List<Producto> paseo) {
		this.paseo = paseo;
	}

	public List<Producto> getPaseo() {
		return paseo;
	}

	
	@Override
	public String toString() {
		if(paseo.isEmpty()) {
			return "El usuario no ha comprado nada";
		}
		return "Itinerario: " + this.paseo + "\nTiene un costo final de = " + getCostoFinal() + "\nUna duración de = " + getTiempoFinal() + "\n";
	}
	private double getCostoFinal() {
		double total = 0;
		for (Producto producto : paseo) 
			total += producto.getCosto();
		return total;
	}
	private double getTiempoFinal() {
		double total = 0;
		for (Producto producto : paseo)
			total += producto.getTiempo();
		return total;
	}
	
	public void aniadirPaseo(Producto producto) {
		paseo.add(producto);
	}
}