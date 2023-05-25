package taller2.modelo;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import taller2.consola.Producto;

	
public class Pedido {
	private static int numeroPedido = 0;
	private static HashMap<Integer, Pedido> historialPedidos = new HashMap<>();

	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private List<Producto> itemsPedido;
	private static double iva = 0.19;

	public Pedido(String nombreClientePar, String direccionClientePar) {
		nombreCliente = nombreClientePar;
		direccionCliente = direccionClientePar;
		numeroPedido += 1;
		idPedido = numeroPedido;
		itemsPedido = new ArrayList<>();

	}

	public static int getNumeroPedido() {
		return numeroPedido;
	}

	public List<Producto> getItemsPedido() {
		return itemsPedido;
	}

	public void guardarPedidoEnHistorial(Pedido pedido) {
		historialPedidos.put(pedido.getIdPedido(), pedido);
	}

	public void agregarItemAlPedido(Producto nuevoItem) throws PedidoExcedeValorException {
        if (getPrecioTotalPedido() + nuevoItem.getPrecio() > 150000) {
            throw new PedidoExcedeValorException("El pedido supera el valor máximo permitido de 150.000 pesos.");
        }

        itemsPedido.add(nuevoItem);
    }

	public int getIdPedido() {
		return idPedido;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	private int getPrecioNetoPedido() {
		int precioTotal = 0;
		for (Producto producto : itemsPedido) {
			precioTotal += producto.getPrecio();
		}
		return precioTotal;
	}

	private float getPrecioIVAPedido() {
		return (float) (getPrecioNetoPedido() * iva);
	}

	private float getPrecioTotalPedido() {
		return (float) (getPrecioNetoPedido() + getPrecioIVAPedido());
	}

	private int getCaloriasTotales() {
		int caloriasTotales = 0;
		for (Producto producto : itemsPedido) {
			caloriasTotales += producto.getCalorias();
		}
		return caloriasTotales;
	}

	public String facturaBase() {
		String text = "";
		text += String.format("--- factura de la compra con ID: %d ---\n", idPedido);
		text += String.format("Factura emitida a nombre de: %s\n", nombreCliente);
		text += String.format("Envio hecho hacia la dirección: %s\n", direccionCliente);
		return text;
	}

	public String facturaFinal() {
		String text = "";
		text += String.format("\nEl pedido contiene un total de %d calorias\n", getCaloriasTotales());
		text += String.format("\nEl precio neto de la compra es: $%d\n", getPrecioNetoPedido());
		text += String.format("El precio del IVA de la compra es: $%f\n", getPrecioIVAPedido());
		text += String.format("El precio final de la compra es: $%f\n", getPrecioTotalPedido());

		return text;
	}

	public String textoFacturaTodo() {
		String text = "";
		text += facturaBase();
		for (Producto producto : itemsPedido) {
			text += producto.getTextoFactura();
			text += "\n";
		}
		text += facturaFinal();
		return text;
	}

	public void guardarFactura() throws IOException, IngredienteRepetidoException, ProductoRepetidoException {
	    if (hayPedidosIguales(this)) {
	        throw new IngredienteRepetidoException("Ya existe un pedido igual en el historial.");
	    }
	    
	    String text = textoFacturaTodo();
	    String idPedidoString = Integer.toString(idPedido);
	    String archivo = System.getProperty("user.dir") + "/data/" + "facturaID" + idPedidoString + ".txt";

	    FileWriter writer = new FileWriter(archivo);
	    writer.write(text);
	    writer.close();
	}


	public ArrayList<Integer> buscarPedidoIgual(Pedido pedidoAComparar) {
		ArrayList<Integer> listaPedidosIguales = new ArrayList<>();
		Integer caloriasAComparar = pedidoAComparar.getCaloriasTotales();
		Float precioAComparar = pedidoAComparar.getPrecioTotalPedido();
		List<Producto> listaAComparar = pedidoAComparar.getItemsPedido();
		Integer idPedido = pedidoAComparar.getIdPedido();
		for (HashMap.Entry<Integer, Pedido> entry : historialPedidos.entrySet()) {
			Boolean sonIguales = true;
			Integer idActual = entry.getKey();
			Pedido pedidoActual = entry.getValue();
			Integer caloriasActual = pedidoActual.getCaloriasTotales();
			Float precioActual = pedidoActual.getPrecioTotalPedido();
			List<Producto> listaActual = pedidoActual.getItemsPedido();
			if ((caloriasAComparar.equals(caloriasActual)) && (precioAComparar.equals(precioActual)) && (!(idPedido
					.equals(idActual)))) {
				for (Producto producto : listaAComparar) {
					Boolean estoyDobleJeje = false;
					for (Producto producto2 : listaActual) {
						if (producto.getNombre().equals(producto2.getNombre())) {
							estoyDobleJeje = true;
						}
					}
					if (estoyDobleJeje.equals(false)) {
						sonIguales = false;
					}
				}
			} else {
				sonIguales = false;
			}
			if (sonIguales) {
				listaPedidosIguales.add(idActual);
			}
		}
		return listaPedidosIguales;
	}

	public Boolean hayPedidosIguales(Pedido pedido) {
		ArrayList<Integer> listaPedidos = buscarPedidoIgual(pedido);
		if (listaPedidos.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}