package entidades.transaccion;

import java.util.Date;

public class Transaccion {

    private String titular;
    private float monto;
    private String numeroCuenta;
    private Date fechaHora;

    public Transaccion(String titular, float monto, String numeroCuenta, Date fechaHora) {
        this.titular = titular;
        this.monto = monto;
        this.numeroCuenta = numeroCuenta;
        this.fechaHora = fechaHora;
    }

    public String getTitular() {
        return titular;
    }

    public float getMonto() {
        return monto;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

}
