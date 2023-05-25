package taller2.modelo;

public class ProductoRepetidoException extends HamburguesaException {
    public ProductoRepetidoException(String producto) {
        super("El producto '" + producto + "' está repetido.");
    }
}
