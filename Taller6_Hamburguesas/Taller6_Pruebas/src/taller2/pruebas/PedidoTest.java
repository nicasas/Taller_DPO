package taller2.pruebas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import taller2.consola.Producto;
import taller2.modelo.Pedido;
import taller2.modelo.PedidoExcedeValorException;
import taller2.modelo.ProductoMenu;

public class PedidoTest {
    private Pedido pedido;
    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    public void setUp() {
        pedido = new Pedido("Cliente Prueba", "Dirección Prueba");
        producto1 = new Producto(new ProductoMenu("Producto 1", 50000, 250));
        producto2 = new Producto(new ProductoMenu("Producto 2", 80000, 350));
    }

    @Test
    public void testAgregarItemAlPedido() {
        assertDoesNotThrow(() -> pedido.agregarItemAlPedido(producto1));
        assertDoesNotThrow(() -> pedido.agregarItemAlPedido(producto2));
        List<Producto> items = pedido.getItemsPedido();
        assertTrue(items.contains(producto1));
        assertTrue(items.contains(producto2));
    }

    @Test
    public void testAgregarItemAlPedidoExcedeValor() {
        Producto producto3 = new Producto(new ProductoMenu("Producto 3", 120000, 500));
        assertDoesNotThrow(() -> pedido.agregarItemAlPedido(producto3));
        assertThrows(PedidoExcedeValorException.class, () -> pedido.agregarItemAlPedido(producto1));
    }

    @Test
    public void testTextoFacturaTodo() {
        assertDoesNotThrow(() -> pedido.agregarItemAlPedido(producto1));
        assertDoesNotThrow(() -> pedido.agregarItemAlPedido(producto2));
        String factura = pedido.textoFacturaTodo();
        assertTrue(factura.contains("Producto 1"));
        assertTrue(factura.contains("Producto 2"));
    }

    @Test
    public void testGuardarFactura() {
        assertDoesNotThrow(() -> pedido.agregarItemAlPedido(producto1));
        assertDoesNotThrow(() -> pedido.agregarItemAlPedido(producto2));
        assertDoesNotThrow(() -> pedido.guardarFactura());
        File archivo = Paths.get(System.getProperty("user.dir"), "/data/", "facturaID" + pedido.getIdPedido() + ".txt").toFile();
        assertTrue(archivo.exists());
    }
}
