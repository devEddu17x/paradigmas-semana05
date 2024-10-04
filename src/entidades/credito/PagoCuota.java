package entidades.credito;

import java.text.MessageFormat;
import java.time.LocalDate;

public class PagoCuota {
    private LocalDate diaDePago;
    private float totalCuota;
    private int diasMora;
    private int numeroCuota;

    public PagoCuota(float totalCuota, int diasMora) {
        this.totalCuota = totalCuota;
        this.diaDePago = LocalDate.now();
        this.diasMora = diasMora;
    }

    public void setNumeroCuota(int numero) {
        this.numeroCuota = numero;
    }

    @Override
    public String toString() {
        return MessageFormat.format("""
                Numero de Cuota: \t{0}
                Dia de Pago: \t{1}
                Dias de Mora: \t{2}
                Total Cuota: \t{3}
                """, numeroCuota, diaDePago, diasMora, totalCuota);
    }
}
