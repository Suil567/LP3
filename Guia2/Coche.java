class Coche {
    // Atributos
    private String marca;
    private String modelo;
    private int añoFabricacion;
    private double precio;
    private boolean encendido;
    private int velocidad;
 
    public Coche() {
        marca = "";
        modelo = "";
        añoFabricacion = 0;
        precio = 0.0;
        encendido = false;
        velocidad = 0;
    }
    // Constructor 
    public Coche(String marca, String modelo, int añoFabricacion, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.añoFabricacion = añoFabricacion;
        this.precio = precio;
        encendido = false;
        velocidad = 0;
    }
    // Aplicar descuento
    public boolean aplicarDescuento(double descuento) {
        if (añoFabricacion < 2010) {
            precio = precio - (precio * descuento / 100);
            return true;
        }
        return false;
    }
    // Encender coche
    public void encender() {
        if (!encendido) {
            encendido = true;
            System.out.println(modelo + " está encendido.");
        } else {
            System.out.println(modelo + " ya está encendido.");
        }
    }
    // Acelerar
    public void acelerar(int cantidad) {
        if (encendido) {
            velocidad = velocidad + cantidad;
            System.out.println(modelo + " aceleró. Velocidad: " + velocidad + " km/h");
        } else {
            System.out.println(modelo + " está apagado.");
        }
    }
    // Frenar
    public void frenar(int cantidad) {
        if (encendido) {
            velocidad = velocidad - cantidad;
            if (velocidad < 0) {
                velocidad = 0;
            }
            System.out.println(modelo + " frenó. Velocidad: " + velocidad + " km/h");
        } else {
            System.out.println(modelo + " está apagado.");
        }
    }
    // Apagar coche
    public void apagar() {
        if (velocidad == 0 && encendido) {
            encendido = false;
            System.out.println(modelo + " está apagado.");
        } else if (velocidad > 0) {
            System.out.println("No se puede apagar " + modelo + " porque está en movimiento.");
        } else {
            System.out.println(modelo + " ya está apagado.");
        }
    }
    // Getters
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public int getAñoFabricacion() {
        return añoFabricacion;
    }
    public double getPrecio() {
        return precio;
    }
    public boolean isEncendido() {
        return encendido;
    }
    public int getVelocidad() {
        return velocidad;
    }
    // Setters
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setAñoFabricacion(int añoFabricacion) {
        this.añoFabricacion = añoFabricacion;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setEncendido(boolean encendido) {
        this.encendido = encendido;
    }
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    // Mostrar datos
    public void mostrarDatos() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Año: " + añoFabricacion);
        System.out.println("Precio: S/ " + precio);
    }
}


public class EjemploCoche {
    public static void main(String[] args) {
        // Crear los dos coches
        Coche cocheDeportivo = new Coche("Ferrari", "488 GTB", 2008, 250000);
        Coche cocheTodoTerreno = new Coche("Toyota", "Land Cruiser", 2020, 180000);
        // Mostrar los datos
        System.out.println("Coche deportivo");
        cocheDeportivo.mostrarDatos();
        System.out.println("Coche todo terreno");
        cocheTodoTerreno.mostrarDatos();
        // Encender los coches
        System.out.println("Encendiendo los coches");
        cocheDeportivo.encender();
        cocheTodoTerreno.encender();
        // Acelerar
        System.out.println("Acelerando");
        cocheDeportivo.acelerar(100);
        cocheTodoTerreno.acelerar(60);
        // Frenar
        System.out.println("Frenando");
        cocheDeportivo.frenar(100);
        cocheTodoTerreno.frenar(60);
        // Apagar
        System.out.println("Apagando los coches");
        cocheDeportivo.apagar();
        cocheTodoTerreno.apagar();
        // Probar getters
        System.out.println("Probando getters");
        System.out.println("Marca: " + cocheDeportivo.getMarca());
        System.out.println("Modelo: " + cocheDeportivo.getModelo());
        System.out.println("Precio: " + cocheDeportivo.getPrecio());
        // Probar setters
        System.out.println("Probando setters");
        cocheDeportivo.setPrecio(230000);
        System.out.println("Nuevo precio: " + cocheDeportivo.getPrecio());
        // Probar descuento
        System.out.println("Probando descuento");
        if (cocheDeportivo.aplicarDescuento(10)) {
            System.out.println("Se aplicó el descuento al coche deportivo.");
            System.out.println("Precio final: " + cocheDeportivo.getPrecio());
        } else {
            System.out.println("No se aplicó el descuento.");
        }
        if (cocheTodoTerreno.aplicarDescuento(10)) {
            System.out.println("Se aplicó el descuento al coche todo terreno.");
        } else {
            System.out.println("No se aplicó el descuento al coche todo terreno.");
        }
    }
}