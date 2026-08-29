class Cuenta {
    private int numCuenta;
    private double saldo;


    public Cuenta(int numCuenta, double saldo) {
        this.numCuenta = numCuenta;
        this.saldo = saldo;
    }
    public Cuenta(int numCuenta) {
        this(numCuenta, 0);
    }
    public int getNumCuenta() {
        return numCuenta;
    }
    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    @Override
    public String toString() {
        return "Numero de cuenta: " + numCuenta +
               ", Saldo: S/. " + saldo;
    }
}


class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private Cuenta cuenta;


    public Persona(int id, String nombre, String apellido, int numCuenta) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;


   // Composicion
        this.cuenta = new Cuenta(numCuenta);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public Cuenta getCuenta() {
        return cuenta;
    }
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }


    @Override
    public String toString() {
        return "ID: " + id +
               ", Nombre: " + nombre +
               ", Apellido: " + apellido +
               ", " + cuenta;
    }
}


public class Main {
    public static void main(String[] args) {
        Persona persona = new Persona(1, "Fabrizio", "Meza", 1001);
        persona.getCuenta().setSaldo(1500.50);
        System.out.println("Datos de la persona:");
        System.out.println(persona);
    }
}
