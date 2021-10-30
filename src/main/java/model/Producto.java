package model;


public abstract class Producto {
	private int id;
	
	private Tipo tipoAtraccion;

	public Producto(int id, Tipo tipoAtraccion) {
		this.id = id;
		
		this.tipoAtraccion = tipoAtraccion;
	}

	public abstract String getNombre();

	public abstract double getTiempo();

	public abstract double getCosto();

	public Tipo getTipoAtraccion() {
		return tipoAtraccion;
	}

	public abstract boolean esPromo();

	public abstract void ocuparLugar();

	public abstract boolean tieneLugar();


	public abstract boolean tengoProducto(Producto productop);

	public int getId() {
		return id;
	}

	
}