package interfaces;

import entidades.cuenta.Cuenta;;

public interface ITransferible {

    public void transferir(Cuenta cuenta, float monto, Cuenta cuentaDestino);
}
