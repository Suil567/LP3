package ejercicio3;

import java.util.List;
import java.util.Scanner;

public class CombateVista {
    private Scanner scanner;

    public CombateVista() {
        scanner = new Scanner(System.in);
    }

    public String solicitarTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public void mostrarMenu() {
        System.out.println("\nOpciones:");
        System.out.println("1. Atacar");
        System.out.println("2. Usar objeto");
    }

    public String solicitarOpcion() {
        System.out.print("Selecciona una opcion: ");
        return scanner.nextLine();
    }

    public void mostrarEstado(Jugador jugador, List<Enemigo> enemigos) {
        System.out.println("\n--- ESTADO DEL COMBATE ---");
        System.out.println(jugador.getNombre() + " (nivel " + jugador.getNivel() + ") salud: " + jugador.getSalud());
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo e = enemigos.get(i);
            System.out.println((i + 1) + ". " + e.getNombre() + " (" + e.getTipo() + ") salud: " + e.getSalud());
        }
    }

    public void mostrarInventario(List<Item> items) {
        System.out.println("Inventario:");
        for (Item item : items) {
            System.out.println("- " + item.getNombre() + " x" + item.getCantidad());
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}