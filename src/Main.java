import datos.Cuentas;
import entidades.cuenta.Cuenta;
import entidades.persona.*;
import enums.Moneda;

public class Main {
    public static void main(String[] args) {

        Persona p1 = new Natural("Av España", "juan@gmail.com", "+51933460321", "Juan", "Perez", "12345678");
        Cuenta c1 = new Cuenta("134123412", Moneda.SOLES, 0, 200, p1, 0);

        Persona p2 = new Natural("Av Venecia", "pepito@gmail.com", "+51933460322", "Pepito", "Piedra", "12345679");
        Cuenta c2 = new Cuenta("134123413", Moneda.SOLES, 0, 200, p2, 0);
        c2.añadirCliente(p1);
        System.out.println(c2);
        c2.quitarCliente(p1);
        System.out.println(c2);
        Cuentas.agregarCuenta(c2);
        /**
         * 
         * p2.setCuenta(c2);
         * p1.setCuenta(c1);
         * p1.depositar(1000);
         * p1.transferir(125, "13412343", Moneda.SOLES);
         * p1.verEstado();
         * p2.verEstado();
         */
    }
}