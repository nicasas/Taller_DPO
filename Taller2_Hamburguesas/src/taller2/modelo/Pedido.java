package taller2.modelo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido 
{
	private static int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;
	
	
	public Pedido(String nombreCliente, String direccionCliente) 
	{
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		itemsPedido=new ArrayList<Producto>();
		idPedido=getNumeroPedidos();
	}
	
	
	
	public void agregarProducto(Producto nuevoItem)
	{
		itemsPedido.add(nuevoItem);
	}
		
	
	public int getIdPedido()
	{
		return idPedido;
	}
	
	
	public static int getNumeroPedidos() {
		return numeroPedidos;
	}
	
	
	public static void setNumeroPedidos(int numeroPedidos) {
		Pedido.numeroPedidos = numeroPedidos;
	}

	
	private  int getPrecioNetoPedido()
	{
		int preNet=0;
		for(int i=0;i<itemsPedido.size();i++)
		{	
		Producto item=itemsPedido.get(i);
		preNet+=item.getPrecio();
		}
			
		return preNet;
	}
	
	
	private int getPrecioIVAPedido()
	{
		int preIva=0;
		for(int i=0;i<itemsPedido.size();i++)
		{	
			Producto item=itemsPedido.get(i);
			preIva+=item.getPrecioIva();
		}
		
		return preIva;
	}

	
	private int getPrecioTotalPedido()
	{
		int preTotal=0;
		for(int i=0;i<itemsPedido.size();i++)
		{	
			Producto item=itemsPedido.get(i);
			preTotal+=item.getPrecioTotal();
		}
		
		return preTotal;
	}

	
	
	public String generarTextoFactura()
	{
		String cadenaImprimir ="";
		
		cadenaImprimir+="Cliente: " + nombreCliente + "Dirección: " + direccionCliente + "ID: " + idPedido + "\n";
		
		for(int i=0;i<itemsPedido.size();i++)
		{	
			Producto item=itemsPedido.get(i);
			cadenaImprimir+=item.generarTextoFactura();
		}
		cadenaImprimir+= "\n" + "Precio neto: " + getPrecioNetoPedido();
		cadenaImprimir+= "\n"+ "Precio IVA: " + getPrecioIVAPedido();
		cadenaImprimir+= "\n"+"Precio total: "+ getPrecioTotalPedido();
		return cadenaImprimir;
	}

	
	public void guardarFactura(File archivo)
	{
	    try {
	       	FileWriter fw = new FileWriter(archivo);
	        BufferedWriter bw = new BufferedWriter(fw);
			bw.write(generarTextoFactura());
			bw.close();
			} catch (IOException e) {
			
			e.printStackTrace();
		}       
	}		
}
