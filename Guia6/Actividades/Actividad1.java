/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// MODELO
class Pedido {
    private String nombrePlato;

    public Pedido(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }
}

class PedidoModelo {
    private List<Pedido> pedidos;

    public PedidoModelo() {
        pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}

// VISTA
class PedidoVista {
    private Scanner scanner;

    public PedidoVista() {
        scanner = new Scanner(System.in);
    }

    public String solicitarNombrePlato() {
        System.out.print("Introduce el nombre del plato: ");
        return scanner.nextLine();
    }

    public void mostrarPedidos(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos en la lista.");
        } else {
            System.out.println("\nLista de Pedidos:");

            for (Pedido pedido : pedidos) {
                System.out.println("- " + pedido.getNombrePlato());
            }
        }
    }

    public void mostrarMenu() {
        System.out.println("\n===== RESTAURANTE =====");
        System.out.println("1. Agregar Pedido");
        System.out.println("2. Mostrar Pedidos");
        System.out.println("3. Salir");
    }

    public String solicitarOpcion() {
        System.out.print("Selecciona una opción: ");
        return scanner.nextLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}

// CONTROLADOR
class PedidoControlador {
    private PedidoModelo modelo;
    private PedidoVista vista;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void agregarPedido(String nombrePlato) {
        if (!nombrePlato.isEmpty()) {
            modelo.agregarPedido(new Pedido(nombrePlato));
            vista.mostrarMensaje("Pedido agregado: " + nombrePlato);
        } else {
            vista.mostrarMensaje("El nombre del plato no puede estar vacío.");
        }
    }

    public void mostrarPedidos() {
        List<Pedido> pedidos = modelo.getPedidos();
        vista.mostrarPedidos(pedidos);
    }

    public void iniciar() {
        String opcion;

        do {
            vista.mostrarMenu();
            opcion = vista.solicitarOpcion();

            switch (opcion) {
                case "1":
                    String nombrePlato = vista.solicitarNombrePlato();
                    agregarPedido(nombrePlato);
                    break;

                case "2":
                    mostrarPedidos();
                    break;

                case "3":
                    vista.mostrarMensaje("Saliendo...");
                    break;

                default:
                    vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
            }

        } while (!opcion.equals("3"));

        vista.cerrarScanner();
    }
}

// CLASE PRINCIPAL
public class Actividad1 {
    public static void main(String[] args) {
        PedidoModelo modelo = new PedidoModelo();
        PedidoVista vista = new PedidoVista();
        PedidoControlador controlador = new PedidoControlador(modelo, vista);

        controlador.iniciar();
    }
}

