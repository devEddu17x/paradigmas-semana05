package datos;

import java.util.HashMap;
import java.util.Map;
import entidades.cuenta.Cuenta;

public class Cuentas {
    private static Map<String, Cuenta> cuentas = new HashMap<String, Cuenta>();

    public static void agregarCuenta(Cuenta cuenta) {
        cuentas.put(cuenta.getNumeroCuenta(), cuenta);
    }

    public static Cuenta getCuenta(String numeroCuenta) {
        return cuentas.get(numeroCuenta);
    }
}
