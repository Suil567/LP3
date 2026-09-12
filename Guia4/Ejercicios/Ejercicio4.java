import java.util.NoSuchElementException;

class RegistroEstudiantes {
    private String[] estudiantes;
    private int cantidad;

    public RegistroEstudiantes(int capacidad) {
        estudiantes = new String[capacidad];
        cantidad = 0;
    }

    public void agregarEstudiante(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacio");
        }
        estudiantes[cantidad] = nombre;
        cantidad++;
    }

    public String buscarEstudiante(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (estudiantes[i].equals(nombre)) {
                return estudiantes[i];
            }
        }
        throw new NoSuchElementException("Estudiante no encontrado");
    }
}

public class Ejercicio4 {
    public static void main(String[] args) {
        RegistroEstudiantes registro = new RegistroEstudiantes(5);
        try {
            registro.agregarEstudiante("Carlos");
            registro.agregarEstudiante("");
            registro.buscarEstudiante("Maria");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}