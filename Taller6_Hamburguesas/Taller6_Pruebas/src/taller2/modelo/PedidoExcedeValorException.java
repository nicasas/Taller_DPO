package taller2.modelo;

public class PedidoExcedeValorException extends Exception {
    public PedidoExcedeValorException(String mensaje) {
        super("El producto excede el valor limite de 150000.");
    }
}
