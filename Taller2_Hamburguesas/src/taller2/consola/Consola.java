package taller2.consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import taller2.modelo.Combo;
import taller2.modelo.Ingrediente;
import taller2.modelo.Pedido;
import taller2.modelo.Producto;
import taller2.modelo.ProductoMenu;
import taller2.modelo.ProductoAjustado;
import taller2.modelo.Restaurante;

public class Consola {
	Restaurante restaurante;

	public static void main(String[] args)  {
		Consola consola=new Consola();	    
		System.out.println("Bienvenido...");
		consola.cargarInformacion();
		consola.mostrarMenu();

	}
	
	
	public void mostrarMenu()  
	{	
		System.out.println("1. Iniciar nuevo pedido ");
		System.out.println("2. Agregar elemento a pedido ");
		System.out.println("3. Cerrar pedido y guardar factura ");
		System.out.println("4. Consultar informacion segun id ");
		System.out.println("5. Salir ");
		int numero=inputNumero(" Por favor, Seleccione una opcion "+" \n");
		
		
		
		if(numero<=5)
		{
			ejecutarOpcion(numero);
		}
		else
		{
			System.out.println("Digite un número entre 1 y 5.");
			mostrarMenu();
		}
		
	}
	public void ejecutarOpcion(int opcionDeseada)  
	{
		if(opcionDeseada==1)
		{	
			String nombreCliente=input("Ingrese su nombre "+" \n");
			String direccionCliente=input("Ingrese su direccion de domicilio "+" \n");
			
			restaurante.iniciarPedido(nombreCliente, direccionCliente);
			
		}
		else if(opcionDeseada==2)
		{
			System.out.println("Puede escoger...."+" \n") ;
			System.out.println("1. Combo ");
			System.out.println("2. Producto Menu ");
			
			
			int numero=inputNumero("Ingrese la opcion que desee"+" \n");
			
			
			if(numero<=2)
			{	
				agregarItem(numero);
			}
			else
			{
				System.out.println("Escoja una opción 1 o 2."+" \n");
				ejecutarOpcion(2);
			}
			
			
		}
		else if(opcionDeseada==3)
		{
			restaurante.cerraryGuardarPedido();
		}
		else if(opcionDeseada==4)
		{
			int id=inputNumero("Digite un identificador de su factura:"+" \n");
			
			String cadena=restaurante.imprimirFactura(id);
			if (cadena!=null)
			{
				System.out.println(cadena);
			}
			else
			{
				System.out.println("Su identificador no existe, Gracias."+" \n");
			}
		}
		else if(opcionDeseada==5)
		{
			System.exit(0);
		}
		else
		{
			System.out.println("Digite una opcion valida."+" \n");
		}
		mostrarMenu();
	}
	
public void agregarItem(int numero)
	{
		if(numero==1)
		{
			ejecutarCombos();
		}
		else if(numero==2)
		{
			ejecutarProductos();
		}
	
	}
public void ejecutarCombos()
{	ArrayList<Integer> eliminados=new ArrayList<Integer>();
	ArrayList<Integer> agregados=new ArrayList<Integer>();
	ArrayList<Combo> combos = restaurante.getCombos();
	for (int i=0;i<combos.size();i++)
	{	Combo combo=combos.get(i);
		System.out.println( (i+1) + " .) Nombre: "+ combo.getNombre()+ " Descuento : " + combo.getDescuento() );	
	}
	int numero=inputNumero("Escoja el comobo que desee"+" \n");
	
	if (numero-1<combos.size())
	{
		
		Combo combo=combos.get(numero-1);
		ArrayList<Producto> productos= combo.getProductos();
		
		for(int i=0;i<productos.size();i++)
		{	
			System.out.println("Este combo esta compuesto por: "+" \n");
			Producto producto=productos.get(i);
			System.out.println((i+1) + "  " + producto.getNombre() + producto.getPrecio());
				
		}
		
		System.out.println("Quisiera agregar algún ingrediente?: "+" \n");
		System.out.println("1.) Agregar o eliminar ingrediente");
		System.out.println("2.) No agregar.");
		int opcion=inputNumero("Seleccione una opcion: "+" \n");
		
		
		if (opcion==1) 
		{
			ejecutarControlIngredientesCombo(combo,agregados,eliminados);
		}			
		else if(opcion==2)
		{
			restaurante.agregarProductoCombo(combo,agregados,eliminados);
		}
	
	}
	else
	{
		System.out.println("No es valida esa opcion"+" \n");
		ejecutarCombos();
	}
	
	
}
public void ejecutarControlIngredientesCombo(Combo combo,ArrayList<Integer> agregados,ArrayList<Integer> eliminados)
{ 
	System.out.println("1. Agregar ingrediente");
	System.out.println("2. Eliminar ingrediente");
	System.out.println("3. No agregar.");
	
	int numero=inputNumero("Seleccione una opción valida:"+" \n");
	
	mostrarIngredientes();
	
	
	
	if(numero==1)
	{	numero=inputNumero("Seleccione el ingrediente"+" \n");
		agregados.add(numero-1);
		ejecutarControlIngredientesCombo(combo,agregados,eliminados);
	}
	else if(numero==2)
	{
		numero=inputNumero("Seleccione el ingrediente"+" \n");
		eliminados.add(numero-1);
		ejecutarControlIngredientesCombo(combo,agregados,eliminados);
	}
	else
	{
		restaurante.agregarProductoCombo(combo, agregados, eliminados);
	}
}
public void ejecutarProductos()
{	
	ArrayList<Integer> eliminados=new ArrayList<Integer>();
	ArrayList<Integer> agregados=new ArrayList<Integer>();
	ArrayList<Producto> menuBase = restaurante.getMenuBase();
	
	for (int i=0;i<menuBase.size();i++)
	{
		Producto producto=menuBase.get(i);
		System.out.println( (i+1) +" " + producto.generarTextoFactura());
	}
	int opcion=inputNumero("Ingrese el producto deseado");
	
	if (opcion-1<menuBase.size())
		{
			ProductoMenu producto= (ProductoMenu) menuBase.get(opcion-1);
			System.out.println("1.) Agregar o eliminar ingrediente");
			System.out.println("2.) No agregarle ingrediente");
			opcion=inputNumero("Seleccione una opcion valida"+" \n");
			
			
			if (opcion==1) 
			{
				ejecutarControlIngredientesMenu(producto,agregados,eliminados);
			}			
			else if(opcion==2)
			{
				restaurante.agregarProductoMenu(producto,agregados,eliminados);
			}
		}
	else
	{
	System.out.println("Esa no es una opcion valida."+" \n");
	}
}

public void ejecutarControlIngredientesMenu(ProductoMenu producto,ArrayList<Integer> agregados,ArrayList<Integer> eliminados)
{
	System.out.println("1.) Aregar ingrediente");
	System.out.println("2.) Eliminar ingrediente");
	System.out.println("3.) No agregar mas ingredientes");
	
	int numero=inputNumero("Seleccione una opción valida:"+" \n");
	
	mostrarIngredientes();
	
	
	
	if(numero==1)
	{	numero=inputNumero("Seleccione el ingrediente"+" \n");
		agregados.add(numero-1);
		ejecutarControlIngredientesMenu(producto,agregados,eliminados);
	}
	else if(numero==2)
	{
		numero=inputNumero("Seleccione el ingrediente"+" \n");
		eliminados.add(numero-1);
		ejecutarControlIngredientesMenu(producto,agregados,eliminados);
	}
	else
	{
		restaurante.agregarProductoMenu(producto, agregados, eliminados);
	}
}
public void mostrarIngredientes()
{	ArrayList<Ingrediente> ingredientes= new ArrayList<Ingrediente>();
	ingredientes=restaurante.getIngredientes();
	for (int i=0;i<ingredientes.size();i++)
	{	
		Ingrediente ingrediente=ingredientes.get(i);
		System.out.println((i+1) + " Nombre : "+ ingrediente.getNombre()+ " Precio :  " + ingrediente.getCostoAdicional()+" \n"); 
	}
}
public void cargarInformacion() 
{	
	System.out.println("Cargando datos");
	restaurante=new Restaurante();
	File archivoIngredientes= new File("./Data/ingredientes.txt");
	File archivoMenu=new File("./Data/menu.txt");
	File archivoCombos=new File("./Data/combos.txt");
	
	try {
		restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Se cargaron los datos");
}
	
	public int inputNumero(String mensaje) 
	{
		System.out.print(mensaje + ": ");
		Scanner reader = new Scanner(System.in);
		return reader.nextInt();
		
	}
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
			
		}
		
		catch (IOException e)
		{
			System.out.println("No se puede cargar la consola"+" \n");
			e.printStackTrace();
		
		}
		return null;
	
	}
	
}


