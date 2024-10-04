package entidades.cuenta;

import enums.Moneda;

import java.text.MessageFormat;
import java.time.LocalDate;

import datos.Cuentas;
import entidades.credito.Credito;
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
    private LocalDate fechaCreacion;
    private LocalDate uttimoPagoMantenimiento;
    private Credito credito;

    public Cuenta(String numeroCuenta, Moneda moneda, float saldo, float montoDiarioRetiro,
            Persona cliente) {
        this.numeroCuenta = numeroCuenta;
        this.moneda = moneda;
        this.saldo = saldo;
        this.montoDiarioRetiro = montoDiarioRetiro;
        this.mancomunada = false;
        this.clientes[0] = cliente;
        cliente.setCuenta(this);
        this.montoMantenimiento = moneda == Moneda.SOLES ? 10 : 3;
        this.estadoCuenta = new EstadoCuenta();
        this.bloqueada = false;
        fechaCreacion = LocalDate.now();
        this.uttimoPagoMantenimiento = fechaCreacion;
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
        } else if (cliente.tieneCuente()) {
            System.out.println("La persona ya tiene una cuenta");
            return;

        } else if (clientes[1] == null) {
            clientes[1] = cliente;
            mancomunada = true;
            cliente.setCuenta(this);
        }
    }

    public void quitarCliente(Persona cliente) {
        boolean soloUno = (clientes[0] != null && clientes[1] == null)
                || (clientes[0] == null && clientes[1] != null);
        if (soloUno) {
            System.out.println("La cuenta debe tener al menos un cliente");
            return;
        }

        boolean esPrimeraPersona = clientes[0] != null && clientes[0].getEmail().equals(cliente.getEmail());
        boolean esSegundaPersona = clientes[1] != null && clientes[1].getEmail().equals(cliente.getEmail());

        if (esPrimeraPersona && !soloUno) {
            clientes[0] = clientes[1];
            clientes[1] = null;
            mancomunada = false;
            cliente.setCuenta(null);
            System.out.println("Cliente eliminado");
        } else if (esSegundaPersona && !soloUno) {
            clientes[1] = null;
            mancomunada = false;
            cliente.setCuenta(null);
            System.out.println("Cliente eliminado");
        } else {
            System.out.println("La persona no es titular de la cuenta");
        }
    }

    public Persona[] getClientes() {
        return clientes;
    }

    public float getMontoMantenimiento() {
        return montoMantenimiento;
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

    public void pagarMantenimiento() {
        if (saldo < montoMantenimiento) {
            bloqueada = true;
            throw new Error(
                    "Cuenta bloqueada por no pagar mantenimiento. Deposite un monto mayor al monto de mantenimiento para desbloquear.");
        }
        saldo -= montoMantenimiento;
        uttimoPagoMantenimiento = LocalDate.now();
    }

    public void depositar(Persona persona, float montoDeposito) {

        LocalDate fechaActual = LocalDate.now();
        try {
            if (bloqueada && montoDeposito > montoMantenimiento) {
                montoDeposito -= montoMantenimiento;
                uttimoPagoMantenimiento = fechaActual;
                bloqueada = false;
            } else {
                if (fechaActual.getMonthValue() > uttimoPagoMantenimiento.getMonthValue() ||
                        fechaActual.getYear() > uttimoPagoMantenimiento.getYear()) {
                    montoDeposito -= montoMantenimiento;
                    uttimoPagoMantenimiento = fechaActual;
                    bloqueada = false;
                }
            }

        } catch (Error e) {
            System.err.println(e.getMessage());
            return;
        }

        if (persona != clientes[0] && persona != clientes[1]) {
            System.out.println("ERROR DE DEPOSITO: La persona no es titular");
            return;
        }

        Deposito deposito = new Deposito(
                persona.getNombre() + " " + persona.getApellido(), montoDeposito, this.numeroCuenta, LocalDate.now());
        try {
            deposito.depositar(this, montoDeposito);
            estadoCuenta.agregarTransaccion(deposito);
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
    }

    public void retirar(Persona persona, float monto) {
        if (bloqueada) {
            System.out.println("Cuenta bloqueada");
            return;
        }
        if (persona != clientes[0] && persona != clientes[1]) {
            System.out.println("ERROR DE RETIRO: La persona no es titular");
            return;
        }

        Retiro retiro = new Retiro(persona.getNombre() + " " + persona.getApellido(), monto, this.numeroCuenta,
                LocalDate.now());
        try {

            retiro.retirar(this, monto);
            estadoCuenta.agregarTransaccion(retiro);
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
    }

    public void transferir(Persona persona, float monto, String cuentaDestino, Moneda moneda) {
        if (bloqueada) {
            System.out.println("Cuenta bloqueada");
            return;
        }
        if (persona != clientes[0] && persona != clientes[1]) {
            System.out.println("ERROR DE TRANSFERENCIA: La persona no es titular");
            return;
        }
        try {
            Cuenta cuentaBeneficiaria = Cuentas.getCuenta(cuentaDestino);
            if (cuentaBeneficiaria == null) {
                throw new Error("Cuenta destino no existe");
            }
            Transferencia transferencia = new Transferencia(persona.getNombre() + " " + persona.getApellido(), monto,
                    this.numeroCuenta, LocalDate.now(), moneda);
            transferencia.transferir(this, monto, cuentaBeneficiaria);
            guardarTransaccion(transferencia);
            cuentaBeneficiaria.guardarTransaccion(transferencia);
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
    }

    public void guardarTransaccion(Transaccion transaccion) {
        estadoCuenta.agregarTransaccion(transaccion);
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
