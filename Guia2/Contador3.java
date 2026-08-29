class Contador {
    static int acumulador = 0;
    final static int valor_inicial = 10;
    static int nContadores = 0;
    static int ultimoContador = 0;
    private int valor;
    public static int acumulador() {
        return acumulador;
    }
    // Constructor con parámetro
    public Contador(int valor) {
        this.valor = valor;
        acumulador += valor;
        nContadores++;
        ultimoContador = valor;
    }


    // Constructor por defecto
    public Contador() {
        this(Contador.valor_inicial);
    }
    // Aumentar contador
    public void inc() {
        valor++;
        acumulador++;
    }
    // Obtener valor
    public int getValor() {
        return this.valor;
    }
}


public class ContadorTest {


    public static void main(String[] args) {  Contador c1, c2, c3;
        System.out.println("Cantidad de contadores: "  + Contador.nContadores);
        c1 = new Contador(3);
        System.out.println("Último contador creado: " + Contador.ultimoContador);


        c2 = new Contador(10);
        System.out.println("Último contador creado: " + Contador.ultimoContador);
        c3 = new Contador();
        System.out.println("Último contador creado: "+ Contador.ultimoContador);
        c1.inc();
        c1.inc();
        c2.inc();
        System.out.println("Valor de c1: " + c1.getValor());
        System.out.println("Valor de c2: " + c2.getValor());
        System.out.println("Valor de c3: " + c3.getValor());
        System.out.println("Cantidad de contadores: " + Contador.nContadores);
        System.out.println("Último contador creado: " + Contador.ultimoContador);
        System.out.println("Acumulador: "  + Contador.acumulador);
    }
}


