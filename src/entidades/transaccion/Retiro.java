package entidades.transaccion;

import java.text.MessageFormat;
import java.util.Date;

import entidades.cuenta.Cuenta;
import interfaces.IRetirable;

public class Retiro extends Transaccion implements IRetirable {
    public Retiro(String titular, float monto, String numeroCuenta, Date fechaHora) {
        super(titular, monto, numeroCuenta, fechaHora);
    }

    @Override
    public void retirar(Cuenta cuenta, float monto) {
        if (monto > cuenta.getSaldo()) {
            throw new Error("Saldo insuficiente");
        } else if (monto <= 0) {
            throw new Error("Monto a retirar no puede ser menor o igual a 0");
        }
        cuenta.setSaldo(cuenta.getSaldo() - monto);

    }

    @Override
    public String toString() {
        return MessageFormat.format("""
                    Retiro
                    Titular: {0}
                    Monto: {1}
                    Numero de cuenta: {2}
                    Fecha y hora: {3}
                """, super.getTitular(), super.getMonto(), super.getNumeroCuenta(), super.getFechaHora());
    }
}
