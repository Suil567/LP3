package ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class CarritoModelo {
    private List<Producto> productos;
    private List<Producto> carrito;
    private List<String> historial;
    private double descuento; // 0.10 = 10%

    public CarritoModelo() {
        productos = new ArrayList<>();
        carrito = new ArrayList<>();
        historial = new ArrayList<>();
        descuento = 0;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Producto> getCarrito() {
        return carrito;
    }

    public List<String> getHistorial() {
        return historial;
    }

    public Producto buscarProducto(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public void agregarAlCarrito(Producto producto) {
        carrito.add(producto);
    }

    public boolean eliminarDelCarrito(Producto producto) {
        return carrito.remove(producto);
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double calcularSubtotal() {
        double suma = 0;
        for (Producto p : carrito) {
            suma = suma + p.getPrecio();
        }
        return suma;
    }

    public double calcularEnvio() {
        if (carrito.isEmpty() || calcularSubtotal() * (1 - descuento) >= 100) {
            return 0;
        }
        return 10;
    }

    public double calcularTotal() {
        return calcularSubtotal() * (1 - descuento) + calcularEnvio();
    }

    public void realizarCompra() {
        historial.add("Compra #" + (historial.size() + 1) + " - Total: S/ " + calcularTotal());
        carrito.clear();
        descuento = 0;
    }
}