package entidades.cuenta;

import enums.Moneda;

import java.text.MessageFormat;
import java.util.Date;

import datos.Cuentas;
import entidades.persona.Persona;
import entidades.transaccion.Deposito;
import entidades.transaccion.Retiro;
import entidades.transaccion.Transaccion;
import entidades.transaccion.Transferencia;

public class Cuenta {
    private String numeroCuenta;
    private Moneda moneda;
    private float saldo;
    private float montoDiarioRetiro;
    private boolean mancomunada;
    private Persona clientes[] = new Persona[2];
    private boolean bloqueada;
    private float montoMantenimiento;
    private EstadoCuenta estadoCuenta;

    public Cuenta(String numeroCuenta, Moneda moneda, float saldo, float montoDiarioRetiro,
            Persona cliente,
            float montoMantenimiento) {
        this.numeroCuenta = numeroCuenta;
        this.moneda = moneda;
        this.saldo = saldo;
        this.montoDiarioRetiro = montoDiarioRetiro;
        this.mancomunada = false;
        this.clientes[0] = cliente;
        this.montoMantenimiento = montoMantenimiento;
        this.estadoCuenta = new EstadoCuenta();
        this.bloqueada = false;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public float getMontoDiarioRetiro() {
        return montoDiarioRetiro;
    }

    public void setMontoDiarioRetiro(float montoDiarioRetiro) {
        this.montoDiarioRetiro = montoDiarioRetiro;
    }

    public boolean isMancomunada() {
        return mancomunada;
    }

    public void añadirCliente(Persona cliente) {
        if (clientes[0] != null && clientes[1] != null) {
            System.out.println("La cuenta ya tiene dos titulares");
            return;
        } else if (clientes[0] != null) {
            clientes[1] = cliente;
            mancomunada = true;
        } else if (clientes[1] != null) {
            clientes[0] = cliente;
            mancomunada = true;
        }
    }

    public void quitarCliente(Persona cliente) {
        if (clientes[0].getEmail() != cliente.getEmail() && clientes[1].getEmail() != cliente.getEmail()) {
            System.out.println("La persona no es titular");
            return;
        }
        if (clientes[0].getEmail() == cliente.getEmail()) {
            clientes[0] = clientes[1];
            clientes[1] = null;
            mancomunada = false;
        } else {
            clientes[1] = null;
            mancomunada = false;
        }
    }

    public Persona[] getClientes() {
        return clientes;
    }

    public void setClientes(Persona[] clientes) {
        this.clientes = clientes;
    }

    public float getMontoMantenimiento() {
        return montoMantenimiento;
    }

    public void setMontoMantenimiento(float montoMantenimiento) {
        this.montoMantenimiento = montoMantenimiento;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void getEstadoCuenta() {
        System.out.println("Saldo: " + saldo);
        estadoCuenta.ListarTransaciones();
    }

    public void depositar(Persona persona, float monto) {
        if (persona != clientes[0] && persona != clientes[1]) {
            System.out.println("La persona no es titular");
            return;
        }

        Deposito deposito = new Deposito(
                persona.getNombre() + " " + persona.getApellido(), monto, this.numeroCuenta, new Date());
        try {
            deposito.depositar(this, monto);
            estadoCuenta.agregarTransaccion(deposito);
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
    }

    public void retirar(Persona persona, float monto) {
        if (persona != clientes[0] && persona != clientes[1]) {
            System.out.println("La persona no es titular");
            return;
        }

        Retiro retiro = new Retiro(persona.getNombre() + " " + persona.getApellido(), monto, this.numeroCuenta,
                new Date());
        try {

            retiro.retirar(this, monto);
            estadoCuenta.agregarTransaccion(retiro);
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
    }

    public void guardarTransaccion(Transaccion transaccion) {
        estadoCuenta.agregarTransaccion(transaccion);
    }

    public void transferir(Persona persona, float monto, String cuentaDestino, Moneda moneda) {
        if (persona != clientes[0] && persona != clientes[1]) {
            System.out.println("La persona no es titular");
            return;
        }
        try {
            Cuenta cuentaBeneficiaria = Cuentas.getCuenta(cuentaDestino);
            if (cuentaBeneficiaria == null) {
                throw new Error("Cuenta destino no existe");
            }
            Transferencia transferencia = new Transferencia(persona.getNombre() + " " + persona.getApellido(), monto,
                    this.numeroCuenta, new Date(), moneda);
            transferencia.transferir(this, monto, cuentaBeneficiaria);
            guardarTransaccion(transferencia);
            cuentaBeneficiaria.guardarTransaccion(transferencia);
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return MessageFormat.format("""
                CUENTA
                Numero de cuenta: {0}
                Moneda: {1}
                Saldo: {2}
                Monto diario de retiro: {3}
                Mancomunada: {4}
                Clientes: {5}
                Bloqueada: {6}
                Monto de mantenimiento: {7}
                """, numeroCuenta, moneda, saldo, montoDiarioRetiro, mancomunada ? "SI" : "NO",
                clientes[0].getNombre() + " " + clientes[0].getApellido()
                        + (clientes[1] != null ? " y " + clientes[1].getNombre() + " " + clientes[1].getApellido()
                                : ""),
                bloqueada ? "SI" : "NO", montoMantenimiento);
    }

}
