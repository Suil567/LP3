/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.ArrayList;

class Par<F, S> {

    private F primero;
    private S segundo;

    public Par(F primero, S segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    public String toString() {
        return "(Primero: " + primero + ", Segundo: " + segundo + ")";
    }
}

class Contenedor<F, S> {

    private ArrayList<Par<F, S>> pares;

    public Contenedor() {
        pares = new ArrayList<>();
    }

    // Agrega un nuevo par
    public void agregarPar(F primero, S segundo) {
        pares.add(new Par<>(primero, segundo));
    }

    // Obtiene un par por su posición
    public Par<F, S> obtenerPar(int indice) {
        return pares.get(indice);
    }

    // Devuelve todos los pares
    public ArrayList<Par<F, S>> obtenerTodosLosPares() {
        return pares;
    }

    // Muestra todos los pares
    public void mostrarPares() {
        for (Par<F, S> par : pares) {
            System.out.println(par);
        }
    }
}

public class Propuesto4 {

    public static void main(String[] args) {

        Contenedor<String, Integer> contenedor = new Contenedor<>();

        contenedor.agregarPar("Juan", 20);
        contenedor.agregarPar("Pedro", 25);
        contenedor.agregarPar("Ana", 22);

        System.out.println("Todos los pares:");
        contenedor.mostrarPares();

        System.out.println("\nPar en la posición 1:");
        System.out.println(contenedor.obtenerPar(1));
    }
}