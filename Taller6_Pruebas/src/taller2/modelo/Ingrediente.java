package taller2.modelo;

public class Ingrediente {
	private String nombre;
	private int costoAdicional;
	private int calorias;

	public Ingrediente(String nombrePar, int costoAdicionalPar, int caloriasPar) {
		nombre = nombrePar;
		costoAdicional = costoAdicionalPar;
		calorias = caloriasPar;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCostoAdicional() {
		return costoAdicional;
	}

	public int getCalorias() {
		return calorias;
	}

	public String toString() {
		return String.format("El ingrediente %s tiene el precio: $%d", nombre, costoAdicional);
	}
}