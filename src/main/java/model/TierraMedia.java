package model;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class TierraMedia {
	private List<Usuario> usuarios;
	private List<Producto> productos = new LinkedList<Producto>();

	public TierraMedia(List<Usuario> usuarios, List<Atraccion> atracciones, List<Promocion> promociones) {
		this.usuarios = usuarios;
		this.productos = setProductos(atracciones, promociones);
	}

	private List<Producto> setProductos(List<Atraccion> atracciones, List<Promocion> promociones) {
		List<Producto> productos = new LinkedList<Producto>();
		productos.addAll(atracciones);
		productos.addAll(promociones);
		return productos;
	}

	public void ofrecerProducto(Usuario usuario) {
		productos.sort(new ProductosPorPreferencia(usuario.getPreferencia()));
		Scanner eleccion = null;
		for (Producto producto : productos) {
			boolean loTengo = tengoProductoEnLista(producto, usuario.getItinerario().getPaseo());
			boolean tieneCupo = producto.tieneLugar();
			if (usuario.puedoComprar(producto) && !loTengo && tieneCupo) {
				while (true) {
					System.out.println("----------------------\n" + "Desea comprar: " + producto + "\ny/n");
					eleccion = new Scanner(System.in);
					String respuesta = eleccion.nextLine();
					if (respuesta.equals("y") || respuesta.equals("Y")) {
						usuario.comprarPaseo(producto);
						producto.ocuparLugar();
						break;
					} else if (respuesta.equals("n") || respuesta.equals("N"))
						break;
					else {
						System.out.println("Esa no es una respuesta válida! \nIntentá otra vez!\n");
					}
				}
			}
		}

			System.out.println(usuario.getItinerario());
			System.out.println("Gracias por visitar Tierra Media!\n");
	}

	private boolean tengoProductoEnLista(Producto productop, List<Producto> listaP) {
		ListIterator<Producto> itr = listaP.listIterator();
		boolean yaLoTengo = false;
		while (!yaLoTengo && itr.hasNext()) {
			yaLoTengo = productop.tengoProducto(itr.next());
		}
		return yaLoTengo;
	}

	public void ofrecerATodos() {
		for (Usuario usuarios : this.usuarios) {
			System.out.println("*****************************************************************************\n"
					+ "Bienvenidx: " + usuarios.getNombre() + "!");
			this.ofrecerProducto(usuarios);
		}
	}

	
}