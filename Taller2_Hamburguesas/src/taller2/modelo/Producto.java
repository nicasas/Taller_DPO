package taller2.modelo;

public interface Producto {
	public int getPrecio();
	public String getNombre();
	public String generarTextoFactura();
	public int getPrecioIva();
	public int getPrecioTotal();
	
}
