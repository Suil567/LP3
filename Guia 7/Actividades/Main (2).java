import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// Clase Persona: es serializable
class Persona implements Serializable {
    private String nif;
    private String nombre;
    private int edad;

    public Persona() {
    }

    public Persona(String nif, String nombre, int edad) {
        this.nif = nif;
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNif() {
        return nif;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
}

// Clase Fecha: DEBE ser serializable porque Alumno la tiene como atributo
class Fecha implements Serializable {
    private int dia;
    private int mes;
    private int anio;

    public Fecha() {
    }

    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}

// Alumno hereda de Persona (por eso tambien es serializable) y tiene una Fecha
class Alumno extends Persona {
    private Fecha fechaMatricula;

    public Alumno() {
    }

    public Alumno(String nif, String nombre, int edad, Fecha fechaMatricula) {
        super(nif, nombre, edad);
        this.fechaMatricula = new Fecha();
        setFechaMatricula(fechaMatricula);
    }

    public Fecha getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Fecha fechaMatricula) {
        this.fechaMatricula.setDia(fechaMatricula.getDia());
        this.fechaMatricula.setMes(fechaMatricula.getMes());
        this.fechaMatricula.setAnio(fechaMatricula.getAnio());
    }
}

public class Main {

    public static void main(String[] args) {
        Alumno a;
        Fecha f;

        // 1) ESCRIBIR los 3 alumnos en el archivo alumnos.dat
        try (FileOutputStream fos = new FileOutputStream("alumnos.dat");
                ObjectOutputStream salida = new ObjectOutputStream(fos)) {

            f = new Fecha(5, 9, 2011);
            a = new Alumno("12345678A", "Lucas Gonzalez", 20, f);
            salida.writeObject(a);

            f = new Fecha(7, 9, 2011);
            a = new Alumno("98765432B", "Anacleto Jimenez", 19, f);
            salida.writeObject(a);

            f = new Fecha(8, 9, 2011);
            a = new Alumno("78234212Z", "Maria Zapata", 21, f);
            salida.writeObject(a);

            System.out.println("Alumnos guardados en alumnos.dat");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // 2) LEER los alumnos del archivo para comprobar que se guardaron bien
        try (FileInputStream fis = new FileInputStream("alumnos.dat");
                ObjectInputStream entrada = new ObjectInputStream(fis)) {

            for (int i = 0; i < 3; i++) {
                a = (Alumno) entrada.readObject(); // casting necesario
                f = a.getFechaMatricula();
                System.out.println(a.getNif() + " " + a.getNombre() + " " + a.getEdad()
                        + " - Matricula: " + f.getDia() + "/" + f.getMes() + "/" + f.getAnio());
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}