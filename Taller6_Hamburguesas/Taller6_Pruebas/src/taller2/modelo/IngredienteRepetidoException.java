package taller2.modelo;

public class IngredienteRepetidoException extends HamburguesaException {
    public IngredienteRepetidoException(String ingrediente) {
        super("El ingrediente '" + ingrediente + "' está repetido.");
    }
}