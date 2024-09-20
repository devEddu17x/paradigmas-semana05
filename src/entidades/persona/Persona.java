package entidades.persona;

import java.text.MessageFormat;

import entidades.cuenta.Cuenta;
import enums.Moneda;

public class Persona {
    private String direccion;
    private String email;
    private String telefono;
    private String nombre;
    private String apellido;
    private Cuenta cuenta;

    public Persona(String direccion, String email, String telefono, String nombre, String apellido) {
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void depositar(float monto) {
        cuenta.depositar(this, monto);
    }

    public void retirar(float monto) {
        cuenta.retirar(this, monto);
    }

    public void transferir(float monto, String cuentaDestino, Moneda moneda) {
        cuenta.transferir(this, monto, cuentaDestino, moneda);
    }

    public void verEstado() {
        cuenta.getEstadoCuenta();
    }

    @Override
    public String toString() {
        return MessageFormat.format("""
                Nombre: {0}
                Apellido: {1}
                Direccion: {2}
                Email: {3}
                Telefono: {4}
                Cuenta: {5}
                """, nombre, apellido, direccion, email, telefono, cuenta);
    }

}
