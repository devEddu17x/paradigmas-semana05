package entidades.credito;

import java.text.MessageFormat;
import java.time.LocalDate;

public class Credito {
    private float monto;
    private float TEA = 5; // tasa de interes;
    private LocalDate fechaAprobacion;
    private int numeroCuotas;
    private int cuotasPagadas;
    private HistorialPagoCredito historialPagoCredito = new HistorialPagoCredito();
    private LocalDate diaDePago;
    private int DIA_DE_PAGO;
    private float pagoCuota;

    public void setMonto(float sueldo, float monto) {
        if (monto > sueldo * 10) {
            throw new Error("Monto no puede ser mayor a 10 veces el sueldo del cliente");
        } else {
            this.monto = monto;
        }
    }

    public void setDiaDePago() {
        LocalDate fechaActual = LocalDate.now();
        int year = fechaActual.getYear();
        int month = fechaActual.getMonthValue();

        diaDePago = LocalDate.of(year, month, DIA_DE_PAGO);

        if (fechaActual.isAfter(diaDePago)) {
            diaDePago = diaDePago.plusMonths(1);
        }
    }

    public void setNumeroCuotas(int numeroCuotas, float sueldo) {
        if (monto * (1 + TEA / 100) / numeroCuotas > sueldo * 0.1f) {
            throw new Error("Numero de cuotas no puede ser mayor a el 10% del sueldo del cliente");
        } else {
            this.numeroCuotas = numeroCuotas;
        }
    }

    public void pagarCuota() {
        if (cuotasPagadas == numeroCuotas) {
            throw new Error("Credito ya ha sido pagado");
        }
        int diferenciaDias = diaDePago.until(LocalDate.now()).getDays();
        float mora;
        if (diferenciaDias > 0) {
            mora = monto * 0.05f * diferenciaDias;
        } else {
            mora = 0.0f;
        }
        float total = mora + pagoCuota;
        PagoCuota pago = new PagoCuota(total, diferenciaDias);
        cuotasPagadas++;
        historialPagoCredito.agregarPago(pago);
    }

    @Override
    public String toString() {
        return MessageFormat.format("""
                CREDITO
                Monto: {0}
                TEA: {1}
                Fecha de Aprobacion: {2}
                Numero de Cuotas: {3}
                Dia de Pago: {4}
                Pago Cuota: {5}
                """, monto, TEA, fechaAprobacion, numeroCuotas, diaDePago, pagoCuota);
    }
}
