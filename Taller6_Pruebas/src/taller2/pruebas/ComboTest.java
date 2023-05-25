package taller2.pruebas;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import taller2.modelo.Bebida;
import taller2.modelo.Combo;
import taller2.modelo.ProductoMenu;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComboTest {
    private Combo combo;
    private ProductoMenu producto1;
    private ProductoMenu producto2;
    private Bebida bebida1;
    private Bebida bebida2;

    @BeforeEach
    public void setUp() {
        combo = new Combo("Combo Test", 0.1);
        producto1 = new ProductoMenu("Hamburguesa", 10, 500);
        producto2 = new ProductoMenu("Papas fritas", 5, 300);
        bebida1 = new Bebida("Coca-Cola", 3, 150);
        bebida2 = new Bebida("Agua", 1, 0);
    }

    @Test
    public void testAgregarItemAlCombo() {
        combo.agregarItemAlCombo(producto1);
        combo.agregarItemAlCombo(bebida1);
        assertEquals(19.0, combo.getPrecio(), 0.01, "El precio del combo después del descuento debe ser 19");
        assertEquals(650, combo.getCalorias(), "El total de calorias debe ser 650");
    }

    @Test
    public void testGetCalorias() {
        combo.agregarItemAlCombo(producto1);
        combo.agregarItemAlCombo(bebida1);
        combo.agregarItemAlCombo(producto2);
        combo.agregarItemAlCombo(bebida2);
        assertEquals(950, combo.getCalorias(), "El total de calorias debe ser 950");
    }

    @Test
    public void testGetPrecio() {
        combo.agregarItemAlCombo(producto1);
        combo.agregarItemAlCombo(bebida1);
        combo.agregarItemAlCombo(producto2);
        combo.agregarItemAlCombo(bebida2);
        assertEquals(17.1, combo.getPrecio(), 0.01, "El precio del combo después del descuento debe ser 17.1");
    }

    @Test
    public void testToString() {
        combo.agregarItemAlCombo(producto1);
        combo.agregarItemAlCombo(bebida1);
        assertEquals("El combo Combo Test tiene un descuento del: 10% y un precio de: 19", combo.toString());
    }

    @Test
    public void testToFactura() {
        combo.agregarItemAlCombo(producto1);
        combo.agregarItemAlCombo(bebida1);
        String expectedFactura = "1 unidad del combo: Combo Test, el combo contiene:\n\t- Hamburguesa\n\t- Coca-Cola\nAl combo se le aplicó un descuento del: 10.000000%\nEl precio total del combo es de: $19.000000\n";
        assertEquals(expectedFactura, combo.toFactura());
    }
}