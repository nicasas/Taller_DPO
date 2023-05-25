package taller2.pruebas;

import org.junit.Test;
import static org.junit.Assert.*;


import taller2.modelo.ProductoMenu;


public class ProductoMenuTest {
    @Test
    public void calcularCalorias_DebeRetornarLasCaloriasCorrectas() {
        // Preparación
        ProductoMenu producto = new ProductoMenu("Hamburguesa Clásica", 100, 200);

        // Ejecución
        int calorias = producto.getCalorias();

        // Verificación
        assertEquals(200, calorias);
    }

    @Test
    public void toString_DebeRetornarElTextoCorrecto() {
        // Preparación
        ProductoMenu producto = new ProductoMenu("Hamburguesa Clásica", 100, 200);

        // Ejecución
        String texto = producto.toString();

        // Verificación
        assertEquals("El producto Hamburguesa Clásica tiene el precio: $100", texto);
    }

    @Test
    public void toFactura_DebeRetornarElTextoCorrectoDeLaFactura() {
        // Preparación
        ProductoMenu producto = new ProductoMenu("Hamburguesa Clásica", 100, 200);

        // Ejecución
        String textoFactura = producto.toFactura();

        // Verificación
        assertEquals("1 unidad del producto: Hamburguesa Clásica\nTiene un precio de: 100\n", textoFactura);
    }
}
