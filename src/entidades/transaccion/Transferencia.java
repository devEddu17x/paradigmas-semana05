package entidades.transaccion;

import java.text.MessageFormat;
import java.time.LocalDate;

import enums.Moneda;
import interfaces.ITransferible;
import entidades.cuenta.Cuenta;
import entidades.persona.Persona;

public class Transferencia extends Transaccion implements ITransferible {

    private Moneda moneda;
    private String beneficiario;
    private String numeroCuentaDestino;
    private String numeroOperacion;

    public Transferencia(String titular, float monto, String numeroCuenta, LocalDate fechaHora, Moneda moneda) {
        super(titular, monto, numeroCuenta, fechaHora);
        this.moneda = moneda;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    @Override
    public void transferir(Cuenta cuenta, float monto, Cuenta cuentaBeneficiaria) {
        if (monto <= 0) {
            throw new Error("El monto a transferir debe ser mayor a 0");
        } else if (monto > cuenta.getSaldo()) {
            throw new Error("Saldo insuficiente");
        }

        if (cuentaBeneficiaria.getNumeroCuenta().equals(cuenta.getNumeroCuenta())) {
            throw new Error("No se puede transferir a la misma cuenta");
        } else if (cuenta.getMoneda() != cuentaBeneficiaria.getMoneda()) {
            throw new Error("Las cuentas deben ser de la misma moneda");
        }
        Persona clientes[] = cuentaBeneficiaria.getClientes();
        beneficiario = clientes[0].getNombre() + " " + clientes[0].getApellido()
                + (clientes[1] != null ? " y " + clientes[1].getNombre() + " "
                        + clientes[1].getApellido() : "");
        numeroCuentaDestino = cuentaBeneficiaria.getNumeroCuenta();
        numeroOperacion = "N° " + (int) (Math.random() * 1000000);

        cuenta.setSaldo(cuenta.getSaldo() - monto);
        cuentaBeneficiaria.setSaldo(monto + cuentaBeneficiaria.getSaldo());
    }

    @Override
    public String toString() {
        return MessageFormat.format("""
                    Transferencia
                    Beneficiarios: {0}
                    Monto: {1}
                    Numero de cuenta origen: {2}
                    Numero de cuenta destino: {3}
                    Moneda: {4}
                    Fecha y hora: {5}
                    Numero de operacion: {6}
                """, beneficiario, super.getMonto(), super.getNumeroCuenta(), numeroCuentaDestino, moneda,
                super.getFechaHora(), numeroOperacion);
    }

}
