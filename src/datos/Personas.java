package datos;

import java.util.HashMap;
import java.util.Map;
import entidades.persona.Persona;

public class Personas {
    private static Map<String, Persona> personas = new HashMap<String, Persona>();
    private static int numeroPersonas = 0;

    public static void agregarPersona(Persona persona) {
        personas.put(persona.getEmail(), persona);
        numeroPersonas++;
    }

    public static int getNumeroPersonas() {
        return numeroPersonas;
    }

    public static Persona getCuenta(String email) {
        return personas.get(email);
    }

    public static void listarPersonas() {
        System.out.println("Listando personas: ");
        for (Map.Entry<String, Persona> entry : personas.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
    }
}
