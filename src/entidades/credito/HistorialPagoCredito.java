package entidades.credito;

import java.util.LinkedList;
import java.util.List;

public class HistorialPagoCredito {
    private int cuotaActual = 0;
    private List<PagoCuota> pagos;

    public HistorialPagoCredito() {
        this.cuotaActual = 0;
        pagos = new LinkedList<>();
    }

    public void agregarPago(PagoCuota pago) {
        cuotaActual++;
        pagos.add(pago);
        pago.setNumeroCuota(cuotaActual);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Listando pagos de cuotas: \n");
        for (PagoCuota pago : pagos) {
            sb.append(pago.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

}
