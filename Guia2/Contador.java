class Contador {
    static int acumulador = 0;
    private int valor;
    // Constructor
    public Contador(int valor) {
        this.valor = valor;
        acumulador += valor;
    }
    // Método para aumentar el contador
    public void inc() {
        valor++;
        acumulador++;
    }
    // Obtener el valor
    public int getValor() {
        return this.valor;
    }
}
public class ContadorTest {
    public static void main(String[] args) {
        Contador c1, c2;
        System.out.println(Contador.acumulador);
        c1 = new Contador(3);
        c2 = new Contador(10);
        c1.inc();
        c1.inc();
        c2.inc();
        System.out.println(c1.getValor());
        System.out.println(c2.getValor());
        System.out.println(Contador.acumulador);
    }
}
