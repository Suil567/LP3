package ejercicio1;

import java.util.List;
import java.util.Scanner;

public class CarritoVista {
    private Scanner scanner;

    public CarritoVista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\nOpciones:");
        System.out.println("1. Agregar producto a la tienda");
        System.out.println("2. Listar productos");
        System.out.println("3. Agregar producto al carrito");
        System.out.println("4. Ver carrito");
        System.out.println("5. Eliminar producto del carrito");
        System.out.println("6. Aplicar descuento");
        System.out.println("7. Calcular envio");
        System.out.println("8. Ver historial de compras");
        System.out.println("9. Realizar compra");
        System.out.println("0. Salir");
    }

    public String solicitarOpcion() {
        System.out.print("Selecciona una opcion: ");
        return scanner.nextLine();
    }

    public String solicitarTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public void mostrarProductos(String titulo, List<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos en la lista.");
        } else {
            System.out.println(titulo);
            for (Producto p : productos) {
                System.out.println("- " + p.getNombre() + " : S/ " + p.getPrecio());
            }
        }
    }

    public void mostrarHistorial(List<String> historial) {
        if (historial.isEmpty()) {
            System.out.println("No hay compras todavia.");
        } else {
            System.out.println("Historial de compras:");
            for (String compra : historial) {
                System.out.println("- " + compra);
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}