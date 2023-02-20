package taller2.modelo;
import java.util.ArrayList;


public class Combo implements Producto{
	private double descuento;
	private String nombreCombo;
	private ArrayList<Producto> itemsCombo;
	private ArrayList<Ingrediente> agregados;
	private ArrayList<Ingrediente> eliminados;
	
	public Combo(String nombreCombo, double descuento) {
		this.descuento = descuento;
		this.nombreCombo = nombreCombo;
		itemsCombo=new ArrayList<Producto>();
		agregados=new ArrayList<Ingrediente>();
		eliminados=new ArrayList<Ingrediente>();
		
	}
	
	
	public void agregarItemACombo(Producto itemCombo)
	{
		itemsCombo.add(itemCombo);
		
	}
	
	
	public void agregarIngredienteCombo(Ingrediente ingrediente)
	{
		agregados.add(ingrediente);
	}
	
	
	public void eliminarIngredienteCombo(Ingrediente ingrediente)
	{
		eliminados.add(ingrediente);
	}
	
	
	public String getNombre()
	{
		return nombreCombo;
	}
	
	
	public double getDescuento()
	{
		return descuento;
	}
	
	
	public int getPrecio()
	{
		int precio=0;
		for(int i=0;i<itemsCombo.size();i++)
		{	
			Producto producto=itemsCombo.get(i);
			precio+=producto.getPrecio();
		}
		precio=(int)(precio*(1-descuento));
		
		for(int i=0;i<agregados.size();i++)
		{
			Ingrediente ingrediente=agregados.get(i);
			precio+=ingrediente.getCostoAdicional();
		}
		
		return precio;
		
	}
	
	
	public int getPrecioIva()
	{
		return (int)(getPrecio()*0.19);
	}
	
	
	public int getPrecioTotal()
	{
		return getPrecio()+ getPrecioIva();
	}
	
	
	
	public ArrayList<Producto> getProductos()
	{
		return itemsCombo;
	}
	
	
	public String generarTextoFactura()
	{
		String cadenaImprimir= getNombre();
		for(int i=0;i<agregados.size();i++)
		{	Ingrediente ingrediente=agregados.get(i);
			cadenaImprimir+= " con " + ingrediente.getNombre();
		}
		for(int i=0;i<eliminados.size();i++)
		{
			Ingrediente ingrediente=eliminados.get(i);
			cadenaImprimir+=" sin " + ingrediente.getNombre();
			
		}
		
		return "Nombre: " + cadenaImprimir + " Precio neto : " + getPrecio() + " IVA : " + getPrecioIva() +  "Descuento : " + descuento + " Precio total : " + getPrecioTotal()+"\n";
	}

}
