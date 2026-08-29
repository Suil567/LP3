import java.util.ArrayList;
interface Inscribible {
    void inscribirEstudiante(Estudiante estudiante);
    void retirarEstudiante(Estudiante estudiante);
}


abstract class Persona {
    protected String nombre;
    protected String dni;
    protected String correo;


    private static int cantidadPersonas = 0;


    public Persona(String nombre, String dni, String correo) {
        this.nombre = nombre;
        this.dni = dni;
        this.correo = correo;
        cantidadPersonas++;
    }


    public String getNombre() {
        return nombre;
    }


    public String getDni() {
        return dni;
    }


    public String getCorreo() {
        return correo;
    }


    public static int getCantidadPersonas() {
        return cantidadPersonas;
    }


    public abstract void mostrarInformacion();
}


class Estudiante extends Persona {
    private String codigoEstudiante;
    private int ciclo;


    public Estudiante(String nombre, String dni, String correo,
                      String codigoEstudiante, int ciclo) {
        super(nombre, dni, correo);
        this.codigoEstudiante = codigoEstudiante;
        this.ciclo = ciclo;
    }


    @Override
    public void mostrarInformacion() {
        System.out.println("Información del estudiante");
        System.out.println("Nombre: " + nombre);
        System.out.println("DNI: " + dni);
        System.out.println("Correo: " + correo);
        System.out.println("Código: " + codigoEstudiante);
        System.out.println("Ciclo: " + ciclo);
    }
}


class Profesor extends Persona {
    private String codigoProfesor;
    private String especialidad;


    public Profesor(String nombre, String dni, String correo,
                    String codigoProfesor, String especialidad) {
        super(nombre, dni, correo);
        this.codigoProfesor = codigoProfesor;
        this.especialidad = especialidad;
    }


    @Override
    public void mostrarInformacion() {
        System.out.println("Información del profesor");
        System.out.println("Nombre: " + nombre);
        System.out.println("DNI: " + dni);
        System.out.println("Correo: " + correo);
        System.out.println("Código: " + codigoProfesor);
        System.out.println("Especialidad: " + especialidad);
    }
}


class Categoria {
    private String nombre;
    private String descripcion;


    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


    public String getNombre() {
        return nombre;
    }


    public String getDescripcion() {
        return descripcion;
    }
}


class Curso implements Inscribible {


    public static final int CAPACIDAD_MAXIMA = 30;
    public static final double COSTO_CREDITO = 50.0;


    private static int cantidadCursos = 0;


    private String codigo;
    private String nombre;
    private int creditos;


    private Profesor profesor;
    private Categoria categoria;


    private ArrayList<Estudiante> estudiantes;


    public Curso(String codigo, String nombre, int creditos,
                 Profesor profesor, Categoria categoria) {


        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.profesor = profesor;
        this.categoria = categoria;
        this.estudiantes = new ArrayList<>();


        cantidadCursos++;
    }


    @Override
    public void inscribirEstudiante(Estudiante estudiante) {


        if (estudiantes.size() < CAPACIDAD_MAXIMA) {
            estudiantes.add(estudiante);


            System.out.println(estudiante.getNombre()
                    + " se matriculó en " + nombre);
        } else {
            System.out.println("El curso está lleno.");
        }
    }


    @Override
    public void retirarEstudiante(Estudiante estudiante) {


        if (estudiantes.remove(estudiante)) {
            System.out.println(estudiante.getNombre()
                    + " fue retirado de " + nombre);
        } else {
            System.out.println("El estudiante no está matriculado.");
        }
    }


    public int cantidadEstudiantes() {
        return estudiantes.size();
    }


    public boolean estaDisponible() {
        return estudiantes.size() < CAPACIDAD_MAXIMA;
    }


    public String getNombre() {
        return nombre;
    }


    public String getCodigo() {
        return codigo;
    }


    public static int getCantidadCursos() {
        return cantidadCursos;
    }


    @Override
    public String toString() {
        return "Curso: " + nombre
                + "\nCódigo: " + codigo
                + "\nCréditos: " + creditos
                + "\nCategoría: " + categoria.getNombre()
                + "\nProfesor: " + profesor.getNombre()
                + "\nEstudiantes matriculados: "
                + estudiantes.size()
                + "/" + CAPACIDAD_MAXIMA;
    }
}


class SistemaGestion {


    private ArrayList<Curso> cursos;
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Profesor> profesores;


    public SistemaGestion() {
        cursos = new ArrayList<>();
        estudiantes = new ArrayList<>();
        profesores = new ArrayList<>();
    }


    public void agregarCurso(Curso curso) {
        cursos.add(curso);
    }


    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }


    public void agregarProfesor(Profesor profesor) {
        profesores.add(profesor);
    }


    public void mostrarCursos() {


        for (Curso curso : cursos) {
            System.out.println(curso);
  
        }
    }


    public void mostrarCursosDisponibles() {


        for (Curso curso : cursos) {


            if (curso.estaDisponible()) {
                System.out.println(curso.getCodigo()
                        + " - " + curso.getNombre()
                        + " - Disponible");
            }
        }
    }
}


public class Main {


    public static void main(String[] args) {


        SistemaGestion sistema = new SistemaGestion();


        Profesor profesor1 = new Profesor(
                "Juan Pérez",
                "70123456",
                "juan@gmail.com",
                "P001",
                "Programación"
        );


        Profesor profesor2 = new Profesor(
                "Ana Torres",
                "70234567",
                "ana@gmail.com",
                "P002",
                "Matemáticas"
        );


        Categoria programacion = new Categoria(
                "Programación",
                "Cursos de programación"
        );


        Categoria matematicas = new Categoria(
                "Matemáticas",
                "Cursos de matemáticas"
        );


        Curso curso1 = new Curso(
                "PRG101",
                "Programación I",
                4,
                profesor1,
                programacion
        );


        Curso curso2 = new Curso(
                "MAT101",
                "Matemática I",
                4,
                profesor2,
                matematicas
        );


        Estudiante estudiante1 = new Estudiante(
                "Carlos López",
                "70345678",
                "carlos@gmail.com",
                "E001",
                2
        );


        Estudiante estudiante2 = new Estudiante(
                "María Flores",
                "70456789",
                "maria@gmail.com",
                "E002",
                2
        );


        Estudiante estudiante3 = new Estudiante(
                "Luis Quispe",
                "70567890",
                "luis@gmail.com",
                "E003",
                3
        );


        sistema.agregarProfesor(profesor1);
        sistema.agregarProfesor(profesor2);


        sistema.agregarEstudiante(estudiante1);
        sistema.agregarEstudiante(estudiante2);
        sistema.agregarEstudiante(estudiante3);


        sistema.agregarCurso(curso1);
        sistema.agregarCurso(curso2);


        System.out.println("REGISTRO DE MATRÍCULAS");
        curso1.inscribirEstudiante(estudiante1);
        curso1.inscribirEstudiante(estudiante2);
        curso2.inscribirEstudiante(estudiante1);
        curso2.inscribirEstudiante(estudiante3);


        System.out.println();
        System.out.println("CURSOS REGISTRADOS");
        sistema.mostrarCursos();


        System.out.println("CURSOS DISPONIBLES");
        sistema.mostrarCursosDisponibles();


        System.out.println();
        System.out.println("CANTIDAD DE ESTUDIANTES");


        System.out.println(curso1.getNombre()
                + ": " + curso1.cantidadEstudiantes()
                + " estudiantes");


        System.out.println(curso2.getNombre()
                + ": " + curso2.cantidadEstudiantes()
                + " estudiantes");


        System.out.println();
        System.out.println("DATOS DEL SISTEMA");


        System.out.println("Cantidad de cursos creados: "
                + Curso.getCantidadCursos());


        System.out.println("Cantidad de personas creadas: "
                + Persona.getCantidadPersonas());


        System.out.println("Capacidad máxima por curso: "
                + Curso.CAPACIDAD_MAXIMA);


        System.out.println("Costo por crédito: S/. "
                + Curso.COSTO_CREDITO);


        System.out.println();
        System.out.println("RETIRO DE ESTUDIANTE");


        curso1.retirarEstudiante(estudiante2);


        System.out.println();
        System.out.println("CANTIDAD ACTUALIZADA");


        System.out.println(curso1.getNombre()
                + ": " + curso1.cantidadEstudiantes()
                + " estudiantes");


        System.out.println();
        System.out.println("EJEMPLO DE POLIMORFISMO");


        Persona persona1 = estudiante1;
        Persona persona2 = profesor1;
        persona1.mostrarInformacion();
        System.out.println();
        persona2.mostrarInformacion();
    }
}
