package entidades.transaccion;

import enums.Moneda;
import interfaces.ITransferible;

public class Transferencia extends Transaccion implements ITransferible {
    private Moneda moneda;
    private String beneficiario;
    private String numeroCuentaDestino;
    private String numeroOperacion;

}
