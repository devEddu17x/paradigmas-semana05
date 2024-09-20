package interfaces;

import entidades.cuenta.Cuenta;

public interface IRetirable {
    public void retirar(Cuenta cuenta, float monto);
}