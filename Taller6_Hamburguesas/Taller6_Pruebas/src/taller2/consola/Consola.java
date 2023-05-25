package taller2.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import taller2.modelo.Bebida;
import taller2.modelo.Combo;
import taller2.modelo.Ingrediente;
import taller2.modelo.IngredienteRepetidoException;
import taller2.modelo.Pedido;
import taller2.modelo.PedidoExcedeValorException;
import taller2.modelo.ProductoAjustado;
import taller2.modelo.ProductoMenu;
import taller2.modelo.ProductoRepetidoException;
import taller2.modelo.Restaurante;

public class Consola {

	private static Restaurante menu;

	public static void main(String[] args) throws IOException, ProductoRepetidoException, IngredienteRepetidoException, PedidoExcedeValorException {
		System.out.println("Estadísticas sobre los Juegos Olímpicos\n");
		try {
			menu = new Restaurante();
			// menu.cargarMenu();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		boolean continuar = true;
		while (continuar) {
			try {
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1) {
					cargarDatos();
				} else if (opcion_seleccionada == 2) {
					mostrarData();

				} else if (opcion_seleccionada == 3) {
					crearPedido();

				} else if (opcion_seleccionada == 4) {
					cerrarPedido();
				} else if (opcion_seleccionada == 5) {
					compararFactura();

				} else if (opcion_seleccionada == 6) {
					System.out.println("Chaolin pin pín	");
					continuar = false;
				} else {
					System.out.println("Por favor seleccione una opción válida.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}

	private static void compararFactura() {
		imprimirTitulo("¡Vamos a ver si existe una factura igual!");
		if (menu.hayPedidoIgual()) {
			for (Integer id : menu.listaDePedidosIguales()) {
				System.out.printf("\nEl pedido de ID: %d es igual al que acabas de terminar, ¡que linda coincidencia!",
						id);
			}
		} else {
			System.out.println("Ohno, no hay un pedido igual, lo siento mucho :C");
		}
	}

	private static void cerrarPedido() throws IOException, IngredienteRepetidoException, ProductoRepetidoException {
		menu.cerrarPedido();
		System.out.println("¡Se ha emitido la factura, puede revisarla en la carpeta Data!");
	}

	private static void crearPedido() throws FileNotFoundException, PedidoExcedeValorException {
		imprimirTitulo("Vamos a crear un pedido");

		String nombreCliente = (input("¿Cúal es su nombre?"));
		String direccionCliente = (input("¿A qué dirección debemos mandar el pedido?"));

		Pedido pedido = new Pedido(nombreCliente, direccionCliente);
		Boolean seguirArmando = true;
		while (seguirArmando) {
			armarProducto(pedido);
			System.out.print("\n\n");
			imprimirTitulo("¿Quieres añadir más productos a tu pedido?");
			System.out.println("1. Sí");
			System.out.println("2. No");
			int seguir = Integer.parseInt(Consola.input("Por favor seleccione una opción"));
			if (seguir == 2) {
				seguirArmando = false;
			}
			System.out.print("\n");
		}
		menu.actualizarPedido(pedido);
	}

	private static void cargarDatos() throws ProductoRepetidoException, IngredienteRepetidoException {
		try {
			menu.cargarRestaurante();
			System.out.println("¡Se han cargado los datos de forma exitosa!");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void mostrarData() {

		imprimirTitulo("Primero van los productos del Menú");
		List<ProductoMenu> bobolon = menu.getMenuBase();
		for (ProductoMenu producto : bobolon) {
			System.out.println(producto);
		}

		imprimirTitulo("Ahora van los ingredientes");

		List<Ingrediente> ingredientes = menu.getIngredientes();
		for (Ingrediente ingrediente : ingredientes) {
			System.out.println(ingrediente);
		}

		imprimirTitulo("Ahora van los combos");
		List<Combo> combos = menu.getCombos();
		for (Combo combo : combos) {
			System.out.println(combo);
		}
		imprimirTitulo("Ahora van las bebidas");
		List<Bebida> bebidas = menu.getBebidas();
		for (Bebida bebida : bebidas) {
			System.out.println(bebida);
		}
	}

	public static void mostrarMenu() {
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Cargar Datos");
		System.out.println("2. Mostrar Datos");
		System.out.println("3. Hacer un pedido");
		System.out.println("4. Terminar el pedido e imprimir factura");
		System.out.println("5. Comparar factura\n");
		System.out.println("6. Salir de la aplicación\n");
	}

	public static void armarProducto(Pedido pedido) throws FileNotFoundException, PedidoExcedeValorException {
		System.out.println("--- ¿Qué deseas que tenga tu pedido? ---");
		System.out.println("1. Un producto del menú (personalizable)");
		System.out.println("2. Un combo");
		System.out.println("3. Una bebida");
		int opcion_seleccionada = Integer.parseInt(Consola.input("\nPor favor seleccione una opción"));

		if (opcion_seleccionada == 1) {
			imprimirTitulo("¿Qué producto quieres?");
			Producto.mostrarProductosMenu(menu);
			int productoMenuIndex = Integer.parseInt(Consola.input("\nPor favor seleccione una opción"));
			ProductoMenu productoMenu = menu.buscarProductoMenuPorIndice(productoMenuIndex);

			System.out.println("¿Quieres personalizar tu producto del menú?");
			System.out.println("1. Sí");
			System.out.println("2. No");
			int siONo = Integer.parseInt(Consola.input("\nPor favor seleccione una opción"));

			if (siONo == 1) {
				Boolean seguir = true;
				ProductoAjustado productoAjustado = new ProductoAjustado(productoMenu);
				while (seguir) {
					System.out.println("\n¿Quieres agregar o quitar ingredientes a este producto?");
					System.out.println("1. Agregar");
					System.out.println("2. Quitar");
					System.out.println("3. No quiero personalizar más mi producto");
					int ponerQuitar = Integer.parseInt(Consola.input("\nPor favor seleccione una opción"));
					if (ponerQuitar == 1) {
						Producto.mostrarIngredientes(menu);
						int ingredienteIndex = Integer.parseInt(Consola.input("\nPor favor seleccione una opción"));
						Ingrediente ingrediente = menu.buscarIngredientePorIndice(ingredienteIndex);
						productoAjustado.agregarIngrediente(ingrediente);
					} else if (ponerQuitar == 2) {
						Producto.mostrarIngredientes(menu);
						int ingredienteIndex = Integer.parseInt(Consola.input("\nPor favor seleccione una opción"));
						Ingrediente ingrediente = menu.buscarIngredientePorIndice(ingredienteIndex);
						productoAjustado.quitarIngrediente(ingrediente);
					} else if (ponerQuitar == 3) {
						seguir = false;
					} else {
						System.out.println("\nPor favor seleccione una opción válida.");
					}
				}
				Producto producto = new Producto(productoAjustado);
				pedido.agregarItemAlPedido(producto);

			} else if (siONo == 2) {
				Producto producto = new Producto(productoMenu);
				pedido.agregarItemAlPedido(producto);
			} else {
				System.out.println("\nPor favor seleccione una opción válida.");
			}

		} else if (opcion_seleccionada == 2) {
			imprimirTitulo("¿Qué combo quieres?");
			Producto.mostrarProductosCombos(menu);
			int ComboIndex = Integer.parseInt(Consola.input("\nPor favor seleccione una opción"));
			Combo combo = menu.buscarComboPorIndice(ComboIndex);

			Producto producto = new Producto(combo);
			pedido.agregarItemAlPedido(producto);

		} else if (opcion_seleccionada == 3) {
			imprimirTitulo("¿Qué bebida quieres?");
			Producto.mostrarBebidas(menu);
			int ComboIndex = Integer.parseInt(Consola.input("\nPor favor seleccione una opción"));
			Bebida bebida = menu.buscarBebidaPorIndice(ComboIndex);
			Producto producto = new Producto(bebida);
			pedido.agregarItemAlPedido(producto);
		} else {
			System.out.println("Por favor seleccione una opción válida.");
		}

	}

	public static String input(String mensaje) {
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	public static void imprimirTitulo(String text) {
		System.out.print("\n");
		System.out.println("---  " + text + "  ---");
	}

}