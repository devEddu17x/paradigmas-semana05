import java.util.Scanner;

import datos.Personas;
import entidades.cuenta.Cuenta;
import entidades.persona.*;
import enums.Moneda;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        menu();

    }

    public static void menu() {

        int opcion;
        String menu = """
                \nMENU DE OPCIONES | BANCO

                        1. Crear persona
                        2. Listar personas
                        3. Cuentas
                        4. Creditos
                        5. Servicios
                        6. Salir
                        """;
        do {
            System.out.println(menu);
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    crearPersona();
                    break;
                case 2:
                    Personas.listarPersonas();
                    break;
                case 3:
                    cuentas();
                    break;
                case 4:
                    // servicios();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 6);
    }

    public static void crearPersona() {

        try {

            String nombre, apellido, direccion, email, telefono;
            Persona p;
            System.out.println("Ingrese nombre: ");
            nombre = sc.next();
            System.out.println("Ingrese apellido: ");
            apellido = sc.next();
            System.out.println("Ingrese direccion: ");
            direccion = sc.next();
            System.out.println("Ingrese email: ");
            email = sc.next();
            System.out.println("Ingrese telefono: ");
            telefono = sc.next();

            while (true) {
                System.out.println("¿Persona Natural o Juridica?");
                System.out.println("1. Natural");
                System.out.println("2. Juridica");
                int opcion = sc.nextInt();
                if (opcion == 1) {
                    System.out.println("Ingrese DNI: ");
                    String dni = sc.next();
                    System.out.println("dni: " + dni);
                    p = new Natural(direccion, email, telefono, nombre, apellido, dni);
                    break;
                } else if (opcion == 2) {
                    System.out.println("Ingrese RUC: ");
                    String ruc = sc.next();
                    System.out.println("Ingrese DNI Representante: ");
                    String dniRepresentante = sc.next();
                    System.out.println("Ingrese Poder Registral: ");
                    String poderRegistral = sc.next();
                    p = new Juridica(direccion, email, telefono, nombre, apellido, ruc,
                            dniRepresentante, poderRegistral);
                    break;
                } else {
                    System.out.println("Opcion no valida");
                }
            }

            Personas.agregarPersona(p);
        } catch (Exception e) {
            System.out.println("Error al crear persona");
        }
    }

    public static void cuentas() {
        if (Personas.getNumeroPersonas() == 0) {
            System.out.println("Primero se debe añadir al menos una persona.");
        }
        System.out.println("Antes de acceder a este menu se debe ingresar como una persona");
        System.out.println("Ingrese su email: ");
        String email = sc.next();
        Persona persona = Personas.getCuenta(email);
        if (persona == null) {
            System.out.println("Persona no encontrada.");
            return;
        }
        int opcion;
        String menu = """
                \nMENU DE OPCIONES | CUENTAS

                        1. Crear cuenta
                        2. Depositar
                        3. Retirar
                        4. Transferir
                        5. Salir
                        6. Ver estado cuenta
                        """;
        do {
            System.out.println(menu);
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    crearPersona();
                    break;
                case 2:
                    Personas.listarPersonas();
                    break;
                case 3:
                    cuentas();
                    break;
                case 4:
                    // servicios();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 6);
    }
}

/*
 * 
 * (String direccion, String email, String telefono, String nombre, String
 * apellido, String ruc,
 * String dniRepresentante,
 * String poderRegistral)
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * this.direccion = direccion;
 * this.email = email;
 * this.telefono = telefono;
 * this.nombre = nombre;
 * this.apellido = apellido;
 */