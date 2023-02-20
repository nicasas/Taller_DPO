package taller2.modelo;
import java.util.ArrayList;


public class ProductoAjustado implements Producto {

	private ArrayList<Ingrediente> eliminados=new ArrayList<Ingrediente>() ;
	private ArrayList<Ingrediente> agregados=new ArrayList<Ingrediente>(); 
	private ProductoMenu base;
	
	
	public ProductoAjustado(ProductoMenu base) {
		this.base = base;
	}
	
	public int getPrecio() {
		int basePrecio=base.getPrecio();
		for(int i=0;i<agregados.size();i++)
		{	
			Ingrediente ingrediente=agregados.get(i);
			basePrecio+= ingrediente.getCostoAdicional();
		}
	
		return basePrecio;
	}

	
	public int getPrecioIva() {
		return (int)(getPrecio()*0.19);
		
	}
	
	public String getNombre() {
		// TODO Auto-generated method stub
		return base.getNombre();
	}
	
	public int getPrecioTotal()
	{
		return getPrecioIva()+ getPrecio(); 	
	}
	
	
	public void agregarIngrediente(Ingrediente ingrediente)
	{
		agregados.add(ingrediente);
	}
	
	
	public void quitarIngrediente(Ingrediente ingrediente)
	{
		eliminados.add(ingrediente);
	}
	
	
	
	public String generarTextoFactura()
	{
		String cadenaImprimir=getNombre();
		for(int i=0;i<agregados.size();i++)
		{	
			Ingrediente ingrediente=agregados.get(i);
			cadenaImprimir+=" con " + ingrediente.getNombre();
		}
		for(int i=0;i<eliminados.size();i++)
		{
			Ingrediente ingrediente=eliminados.get(i);
			cadenaImprimir+=" sin " + ingrediente.getNombre();
		}
		
		return "Nombre: " + cadenaImprimir + ", Precio neto: " + getPrecio() + ", IVA: " + getPrecioIva() + ", Precio total: " +(getPrecioTotal())+"\n";
	}

	

}
