package interfaces;

import entidades.cuenta.Cuenta;

public interface IDepositable {
    public void depositar(Cuenta cuenta, float monto);
}