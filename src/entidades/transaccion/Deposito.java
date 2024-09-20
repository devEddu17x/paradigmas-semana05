package entidades.transaccion;

import java.text.MessageFormat;
import java.util.Date;

import interfaces.IDepositable;
import entidades.cuenta.Cuenta;

public class Deposito extends Transaccion implements IDepositable {

    public Deposito(String titular, float monto, String numeroCuenta, Date fechaHora) {
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
                    Titular: {0}
                    Monto: {1}
                    Numero de cuenta: {2}
                    Fecha y hora: {3}
                """, super.getTitular(), super.getMonto(), super.getNumeroCuenta(), super.getFechaHora());
    }
}
