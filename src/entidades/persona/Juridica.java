package entidades.persona;

public class Juridica {
    protected String ruc;
    protected String dniRepresentante;
    protected String poderRegistral;

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
