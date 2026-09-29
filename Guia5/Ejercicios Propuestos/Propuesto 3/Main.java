/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
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

class Persona {

    String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return nombre;
    }
}

public class Main {

    // Método genérico
    public static <F, S> void imprimirPar(Par<F, S> par) {
        System.out.println(par);
    }

    public static void main(String[] args) {

        Par<String, Integer> par1 = new Par<>("Edad", 20);
        Par<Double, Boolean> par2 = new Par<>(3.5, true);
        Par<Persona, Integer> par3 = new Par<>(new Persona("Juan"), 25);

        imprimirPar(par1);
        imprimirPar(par2);
        imprimirPar(par3);
    }
}