package entidades.persona;

public class Natural extends Persona {

    private String DNI;

    public Natural(String direccion, String email, String telefono, String nombre, String apellido, String DNI) {
        super(direccion, email, telefono, nombre, apellido);
        this.DNI = DNI;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dNI) {
        DNI = dNI;
    }

}
