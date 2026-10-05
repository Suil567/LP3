package ejercicio3;

public class Item {
    private String nombre;
    private int cantidad;
    private String tipo;
    private String descripcion;
    private int poder; // dano del arma o curacion de la pocion

    public Item(String nombre, int cantidad, String tipo, String descripcion, int poder) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.poder = poder;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPoder() {
        return poder;
    }
}