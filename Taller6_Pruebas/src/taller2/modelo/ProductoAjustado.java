package taller2.modelo;
import java.util.ArrayList;
import java.util.List;

public class ProductoAjustado {
	private String nombre;
	private int precio;
	private int calorias;
	private List<Ingrediente> agregados;
	private List<Ingrediente> quitados;

	public ProductoAjustado(ProductoMenu productoBase) {
		nombre = productoBase.getNombre();
		precio = productoBase.getPrecioBase();
		calorias = productoBase.getCalorias();
		agregados = new ArrayList<>();
		quitados = new ArrayList<>();
	}

	public void agregarIngrediente(Ingrediente ingrediente) {
		agregados.add(ingrediente);
		precio += ingrediente.getCostoAdicional();
	}

	public void quitarIngrediente(Ingrediente ingrediente) {
		quitados.add(ingrediente);
	}

	public String getNombre() {
		return nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public List<Ingrediente> getAgregados() {
		return agregados;
	}

	public List<Ingrediente> getQuitados() {
		return quitados;
	}

	public int getCalorias() {
		int caloriasTotal = calorias;
		for (Ingrediente ingrediente : agregados) {
			caloriasTotal += ingrediente.getCalorias();
		}
		for (Ingrediente ingrediente : quitados) {
			caloriasTotal -= ingrediente.getCalorias();
		}
		return caloriasTotal;
	}

	public String toFactura() {
		String text = "";
		text += String.format("1 unidad de %s \n", nombre);
		for (Ingrediente ingrediente : agregados) {
			text += String.format("\t- con adición de %s, con un costo adicional de: %d\n", ingrediente.getNombre(),
					ingrediente.getCostoAdicional());
		}
		for (Ingrediente ingrediente : quitados) {
			text += String.format("\t- se quitó el ingrediente %s\n", ingrediente.getNombre());
		}
		text += String.format("El precio total del producto fue: $%d\n", precio);
		return text;
	}
}