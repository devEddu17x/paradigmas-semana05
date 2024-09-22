package entidades.transaccion;

import java.text.MessageFormat;
import java.time.LocalDate;

import interfaces.IDepositable;
import entidades.cuenta.Cuenta;

public class Deposito extends Transaccion implements IDepositable {

    public Deposito(String titular, float monto, String numeroCuenta, LocalDate fechaHora) {
        super(titular, monto, numeroCuenta, fechaHora);
    }

    @Override
    public void depositar(Cuenta cuenta, float monto) {

        if (monto <= 0) {
            throw new Error("Monto a depositar no puede ser menor o igual a 0");
        }
        cuenta.setSaldo(monto + cuenta.getSaldo());
    }

    @Override
    public String toString() {
        return MessageFormat.format("""
                    Deposito
                    N° de cuenta: {0}
                    Titular: {1}
                    Monto: {2}
                    Numero de cuenta: {3}
                    Fecha y hora: {4}
                """, super.getNumeroCuenta(), super.getTitular(), super.getMonto(), super.getNumeroCuenta(),
                super.getFechaHora());
    }
}
