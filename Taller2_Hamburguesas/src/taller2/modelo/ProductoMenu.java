package taller2.modelo;

public class ProductoMenu implements Producto{
	private String nombre;
	private int precioBase  ;
	public ProductoMenu(String nombre, int precioBase) {
		this.nombre = nombre;
		this.precioBase = precioBase;
	}
	
	
	public int getPrecio() {
		return precioBase;
	}
	public void setPrecioBase(int precioBase) {
		this.precioBase = precioBase;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}
	@Override
	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		return "Nombre:" + nombre + ", Precio neto: " + getPrecio() + ", IVA: " + getPrecioIva() + ", Precio total: " + getPrecioTotal() +"\n";
	}


	@Override
	public int getPrecioIva() {
		// TODO Auto-generated method stub
		return (int)(getPrecio()*0.19);
	}


	@Override
	public int getPrecioTotal() {
		// TODO Auto-generated method stub
		return (int)(getPrecio()+getPrecioIva());
	}
	
	
	
}
