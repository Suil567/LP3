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

    public F getPrimero() {
        return primero;
    }

    public S getSegundo() {
        return segundo;
    }

    public void setPrimero(F primero) {
        this.primero = primero;
    }

    public void setSegundo(S segundo) {
        this.segundo = segundo;
    }

    public boolean esIgual(Par<F, S> otro) {
        return primero.equals(otro.primero) &&
               segundo.equals(otro.segundo);
    }

    public String toString() {
        return "(Primero: " + primero + ", Segundo: " + segundo + ")";
    }
}

public class PruebaPar {

    public static void main(String[] args) {

        Par<String, Integer> par1 = new Par<>("Juan", 20);
        Par<String, Integer> par2 = new Par<>("Juan", 20);
        Par<String, Integer> par3 = new Par<>("Pedro", 25);

        System.out.println(par1);
        System.out.println(par2);
        System.out.println(par3);

        System.out.println("Par1 y Par2: " + par1.esIgual(par2));
        System.out.println("Par1 y Par3: " + par1.esIgual(par3));
    }
}