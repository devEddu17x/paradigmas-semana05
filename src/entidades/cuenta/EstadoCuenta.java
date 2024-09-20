package entidades.cuenta;

import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;

import entidades.transaccion.Transaccion;

public class EstadoCuenta {
    static final int MAX_TRANSACCIONES = 10;
    private List<Transaccion> transacciones;

    public EstadoCuenta() {
        transacciones = new LinkedList<>();
    }

    public void agregarTransaccion(Transaccion transaccion) {
        if (transacciones.size() >= 10) {
            transacciones.removeLast();
        }

        transacciones.addFirst(transaccion);
    }

    public void ListarTransaciones() {
        Iterator<Transaccion> it = transacciones.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println("Mostrando ultimas " + transacciones.size() + " transacciones");
    }
}