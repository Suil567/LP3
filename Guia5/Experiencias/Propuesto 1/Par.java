/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Par<F, S> {

    private F primero;
    private S segundo;

    // Constructor
    public Par(F primero, S segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    // Getters
    public F getPrimero() {
        return primero;
    }

    public S getSegundo() {
        return segundo;
    }

    // Setters
    public void setPrimero(F primero) {
        this.primero = primero;
    }

    public void setSegundo(S segundo) {
        this.segundo = segundo;
    }

    // toString
    @Override
    public String toString() {
        return "(Primero: " + primero + ", Segundo: " + segundo + ")";
    }

    // Método principal
    public static void main(String[] args) {

        Par<String, Integer> par = new Par<>("Edad", 20);

        System.out.println(par);

        par.setPrimero("Nombre");
        par.setSegundo(25);

        System.out.println(par);
    }
}