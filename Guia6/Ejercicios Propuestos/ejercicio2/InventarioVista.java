package ejercicio2;

import java.util.List;
import java.util.Scanner;

public class InventarioVista {
    private Scanner scanner;

    public InventarioVista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\nOpciones:");
        System.out.println("1. Agregar item");
        System.out.println("2. Eliminar item");
        System.out.println("3. Ver inventario");
        System.out.println("4. Mostrar detalles de un item");
        System.out.println("5. Buscar item");
        System.out.println("6. Usar item");
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

    public void mostrarInventario(List<Item> items) {
        if (items.isEmpty()) {
            System.out.println("El inventario esta vacio.");
        } else {
            System.out.println("Inventario:");
            for (Item item : items) {
                System.out.println("- " + item.getNombre() + " x" + item.getCantidad());
            }
        }
    }

    public void mostrarDetallesItem(Item item) {
        System.out.println("Nombre: " + item.getNombre());
        System.out.println("Cantidad: " + item.getCantidad());
        System.out.println("Tipo: " + item.getTipo());
        System.out.println("Descripcion: " + item.getDescripcion());
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}