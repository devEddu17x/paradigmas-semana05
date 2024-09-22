package entidades.transaccion;

import java.time.LocalDate;

public class Transaccion {

    private String titular;
    private float monto;
    private String numeroCuenta;
    private LocalDate fechaHora;

    public Transaccion(String titular, float monto, String numeroCuenta, LocalDate fechaHora) {
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

    public LocalDate getFechaHora() {
        return fechaHora;
    }

}
