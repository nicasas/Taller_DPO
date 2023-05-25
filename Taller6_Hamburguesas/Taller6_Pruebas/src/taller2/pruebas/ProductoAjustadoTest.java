package taller2.pruebas;
import org.junit.Test;

import taller2.modelo.Ingrediente;
import taller2.modelo.ProductoAjustado;
import taller2.modelo.ProductoMenu;

import static org.junit.Assert.*;


public class ProductoAjustadoTest {
    @Test
    public void agregarIngrediente_DebeAumentarElPrecioSegunElCostoAdicional() {
        // Preparación
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa Clásica", 100, 200);
        ProductoAjustado productoAjustado = new ProductoAjustado(productoBase);
        Ingrediente ingrediente = new Ingrediente("Queso", 20, 50);

        // Ejecución
        productoAjustado.agregarIngrediente(ingrediente);

        // Verificación
        assertEquals(120, productoAjustado.getPrecio());
    }

    @Test
    public void quitarIngrediente_DebeDisminuirElPrecioSegunElCostoAdicional() {
        // Preparación
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa Clásica", 100, 200);
        ProductoAjustado productoAjustado = new ProductoAjustado(productoBase);
        Ingrediente ingrediente = new Ingrediente("Tomate", 10, 20);
        productoAjustado.agregarIngrediente(ingrediente);

        // Ejecución
        productoAjustado.quitarIngrediente(ingrediente);

        // Verificación
        assertEquals(100, productoAjustado.getPrecio());
    }

    @Test
    public void getCalorias_DebeRetornarLasCaloriasCorrectas() {
        // Preparación
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa Clásica", 100, 200);
        ProductoAjustado productoAjustado = new ProductoAjustado(productoBase);
        Ingrediente ingrediente1 = new Ingrediente("Queso", 20, 50);
        Ingrediente ingrediente2 = new Ingrediente("Tomate", 10, 20);
        productoAjustado.agregarIngrediente(ingrediente1);
        productoAjustado.agregarIngrediente(ingrediente2);

        // Ejecución
        int calorias = productoAjustado.getCalorias();

        // Verificación
        assertEquals(270, calorias);
    }

    @Test
    public void toFactura_DebeRetornarElTextoCorrectoDeLaFactura() {
        // Preparación
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa Clásica", 100, 200);
        ProductoAjustado productoAjustado = new ProductoAjustado(productoBase);
        Ingrediente ingrediente1 = new Ingrediente("Queso", 20, 50);
        Ingrediente ingrediente2 = new Ingrediente("Tomate", 10, 20);
        productoAjustado.agregarIngrediente(ingrediente1);
        productoAjustado.agregarIngrediente(ingrediente2);

        // Ejecución
        String textoFactura = productoAjustado.toFactura();

        // Verificación
        String expectedText = "1 unidad de Hamburguesa Clásica \n" +
                "\t- con adición de Queso, con un costo adicional de: 20\n" +
                "\t- con adición de Tomate, con un costo adicional de: 10\n" +
                "El precio total del producto fue: $130\n";
        assertEquals(expectedText, textoFactura);
    }
}