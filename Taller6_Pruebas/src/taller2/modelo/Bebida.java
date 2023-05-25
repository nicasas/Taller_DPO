package taller2.modelo;

public class Bebida {
	private String nombre;
	private int precio;
	private int calorias;
	
	public Bebida(String nombrePar, int precioPar,int caloriasPar) {
		nombre = nombrePar;
		precio = precioPar;
		calorias = caloriasPar;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPrecio() {
		return precio;
	}
	public int getCalorias() {
		return calorias;
	}


	public String toString() {
		return String.format("El producto %s (%d) tiene el precio: $%d", this.nombre,calorias,this.precio);
	}
	public String toFactura() {
		String text ="";
		text+= String.format("1 unidad de la bebida: %s\n", nombre);
		text+= String.format("Tiene un precio de: %d\n", precio);
		return text;
	}
	
}