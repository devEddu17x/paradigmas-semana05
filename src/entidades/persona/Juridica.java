package entidades.persona;

public class Juridica extends Persona {

    protected String ruc;
    protected String dniRepresentante;
    protected String poderRegistral;

    public Juridica(String direccion, String email, String telefono, String nombre, String apellido, String ruc,
            String dniRepresentante,
            String poderRegistral) {
        super(direccion, email, telefono, nombre, apellido);
        this.ruc = ruc;
        this.dniRepresentante = dniRepresentante;
        this.poderRegistral = poderRegistral;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDniRepresentante() {
        return dniRepresentante;
    }

    public void setDniRepresentante(String dniRepresentante) {
        this.dniRepresentante = dniRepresentante;
    }

    public String getPoderRegistral() {
        return poderRegistral;
    }

    public void setPoderRegistral(String poderRegistral) {
        this.poderRegistral = poderRegistral;
    }
}
