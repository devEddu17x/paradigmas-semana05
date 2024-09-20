package entidades.cuenta;

import enums.Moneda;

import java.util.Date;

import entidades.persona.Persona;
import entidades.transaccion.Deposito;
import entidades.transaccion.Retiro;
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

    public Cuenta(String numeroCuenta, Moneda moneda, float saldo, float montoDiarioRetiro, boolean mancomunada,
            Persona cliente,
            float montoMantenimiento) {
        this.numeroCuenta = numeroCuenta;
        this.moneda = moneda;
        this.saldo = saldo;
        this.montoDiarioRetiro = montoDiarioRetiro;
        this.mancomunada = mancomunada;
        this.clientes[0] = cliente;
        this.montoMantenimiento = montoMantenimiento;
        this.estadoCuenta = new EstadoCuenta();
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

    public void setMancomunada(boolean mancomunada) {
        this.mancomunada = mancomunada;
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

    public void transferir(Persona persona, float monto, String cuentaDestino, Moneda moneda) {
        if (persona != clientes[0] && persona != clientes[1]) {
            System.out.println("La persona no es titular");
            return;
        }

        Transferencia transferencia = new Transferencia(persona.getNombre() + " " + persona.getApellido(), monto,
                this.numeroCuenta, new Date(), moneda);
        try {
            transferencia.transferir(this, monto, cuentaDestino);
            estadoCuenta.agregarTransaccion(transferencia);
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
    }

}
