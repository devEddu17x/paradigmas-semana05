import entidades.cuenta.Cuenta;
import entidades.persona.*;
import enums.Moneda;

public class Main {
    public static void main(String[] args) {

        Persona p1 = new Natural("Av España", "juan@gmail.com", "+51933460321", "Juan", "Perez", "12345678");
        Persona p2 = new Natural("Av Venecia", "pepito@gmail.com", "+51933460322", "Pepito", "Piedra", "12345679");

        // crear cuenta con p2 como cliente
        Cuenta c2 = new Cuenta("134123413", Moneda.SOLES, 0, 200, p2);
        // añadir p1 como cliente
        c2.añadirCliente(p1);

        // depositar en cuenta con p1 y p2
        c2.depositar(p1, 100);
        c2.depositar(p2, 100);

        // retirar de cuenta con p1 y p2
        c2.retirar(p1, 50);
        c2.retirar(p2, 23);

        // retirar cliente p1
        c2.quitarCliente(p1);
        c2.quitarCliente(p2);
        // ver estado de p1 y p2
        p1.verEstado();
        p2.verEstado();

    }
}