package taller2.consola;

import java.io.FileNotFoundException;
import java.util.List;

import taller2.modelo.Bebida;
import taller2.modelo.Combo;
import taller2.modelo.Ingrediente;
import taller2.modelo.ProductoAjustado;
import taller2.modelo.ProductoMenu;
import taller2.modelo.Restaurante;

public class Producto {
	private String nombre;
	private int precioTotal;
	private int caloriasTotal;
	private String textoFactura;

	public Producto(ProductoMenu producto) {
		nombre = producto.getNombre();
		precioTotal = producto.getPrecioBase();
		textoFactura = producto.toFactura();
		caloriasTotal = producto.getCalorias();
	}

	public Producto(ProductoAjustado producto) {
		nombre = producto.getNombre();
		precioTotal = producto.getPrecio();
		textoFactura = producto.toFactura();
		caloriasTotal = producto.getCalorias();
	}

	public Producto(Bebida producto) {
		nombre = producto.getNombre();
		precioTotal = producto.getPrecio();
		textoFactura = producto.toFactura();
		caloriasTotal = producto.getCalorias();
	}

	public Producto(Combo combo) {
		nombre = combo.getNombreCombo();
		precioTotal = (int) combo.getPrecio();
		textoFactura = combo.toFactura();
		caloriasTotal = combo.getCalorias();
	}

	public String getNombre() {
		return nombre;
	}

	public int getPrecio() {
		return precioTotal;
	}

	public String getTextoFactura() {
		return textoFactura;
	}

	public int getCalorias() {
		return caloriasTotal;
	}

	public static void mostrarProductosMenu(Restaurante restaurante) throws FileNotFoundException {
		List<ProductoMenu> listaMenu = restaurante.getMenuBase();
		for (int index = 0; index < listaMenu.size(); index++) {
			System.out.printf("\n%d. %s", (index + 1), listaMenu.get(index));
		}
	}

	public static void mostrarIngredientes(Restaurante restaurante) throws FileNotFoundException {
		List<Ingrediente> listaIngredientes = restaurante.getIngredientes();
		for (int index = 0; index < listaIngredientes.size(); index++) {
			System.out.printf("\n%d. %s", (index + 1), listaIngredientes.get(index));
		}
	}

	public static void mostrarProductosCombos(Restaurante restaurante) throws FileNotFoundException {
		List<Combo> listaCombos = restaurante.getCombos();
		for (int index = 0; index < listaCombos.size(); index++) {
			System.out.printf("\n%d. %s", (index + 1), listaCombos.get(index));
		}
	}

	public static void mostrarBebidas(Restaurante restaurante) throws FileNotFoundException {
		List<Bebida> listaBebidas = restaurante.getBebidas();
		for (int index = 0; index < listaBebidas.size(); index++) {
			System.out.printf("\n%d. %s", (index + 1), listaBebidas.get(index));
		}
	}

}