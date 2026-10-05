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
    private String tipo;
    private String estado;

    public Pedido(String nombrePlato, String tipo) {
        this.nombrePlato = nombrePlato;
        this.tipo = tipo;
        this.estado = "Pendiente";
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

class PedidoModelo {
    private List<Pedido> pedidos;
    private List<Pedido> historial;

    public PedidoModelo() {
        pedidos = new ArrayList<>();
        historial = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public List<Pedido> getHistorial() {
        return historial;
    }

    public boolean completarPedido(String nombre) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNombrePlato().equalsIgnoreCase(nombre)
                    && pedido.getEstado().equals("Pendiente")) {

                pedido.setEstado("Completo");
                historial.add(pedido);
                return true;
            }
        }

        return false;
    }

    public boolean eliminarPedido(String nombre) {
        for (int i = 0; i < pedidos.size(); i++) {

            Pedido pedido = pedidos.get(i);

            if (pedido.getNombrePlato().equalsIgnoreCase(nombre)) {
                pedido.setEstado("Eliminado");
                historial.add(pedido);
                pedidos.remove(i);
                return true;
            }
        }

        return false;
    }

    public List<Pedido> obtenerPorEstado(String estado) {
        List<Pedido> resultados = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            if (pedido.getEstado().equalsIgnoreCase(estado)) {
                resultados.add(pedido);
            }
        }

        return resultados;
    }

    public int contarPendientes() {
        int contador = 0;

        for (Pedido pedido : pedidos) {
            if (pedido.getEstado().equalsIgnoreCase("Pendiente")) {
                contador++;
            }
        }

        return contador;
    }
}

// VISTA
class PedidoVista {
    private Scanner scanner;

    public PedidoVista() {
        scanner = new Scanner(System.in);
    }

    public String solicitarNombre() {
        System.out.print("Ingrese el nombre del plato: ");
        return scanner.nextLine();
    }

    public String solicitarTipo() {
        System.out.print("Ingrese el tipo de plato: ");
        return scanner.nextLine();
    }

    public void mostrarPedidos(List<Pedido> pedidos) {

        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos para mostrar.");
        } else {

            System.out.println("\nLista de pedidos:");

            for (Pedido pedido : pedidos) {
                System.out.println(
                        "Plato: " + pedido.getNombrePlato()
                        + " | Tipo: " + pedido.getTipo()
                        + " | Estado: " + pedido.getEstado()
                );
            }
        }
    }

    public void mostrarHistorial(List<Pedido> historial) {

        if (historial.isEmpty()) {
            System.out.println("El historial está vacío.");
        } else {

            System.out.println("\n===== HISTORIAL DE PEDIDOS =====");

            for (Pedido pedido : historial) {
                System.out.println(
                        "Plato: " + pedido.getNombrePlato()
                        + " | Tipo: " + pedido.getTipo()
                        + " | Estado: " + pedido.getEstado()
                );
            }
        }
    }

    public void mostrarMenu() {
        System.out.println("\n===== GESTIÓN DE PEDIDOS =====");
        System.out.println("1. Agregar Pedido");
        System.out.println("2. Mostrar Todos los Pedidos");
        System.out.println("3. Marcar Pedido como Completo");
        System.out.println("4. Mostrar Pedidos Pendientes");
        System.out.println("5. Mostrar Pedidos Completos");
        System.out.println("6. Contador de Pedidos Pendientes");
        System.out.println("7. Eliminar Pedido");
        System.out.println("8. Mostrar Historial de Pedidos");
        System.out.println("9. Salir");
    }

    public String solicitarOpcion() {
        System.out.print("Seleccione una opción: ");
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

    public void agregarPedido() {

        String nombre = vista.solicitarNombre();
        String tipo = vista.solicitarTipo();

        if (!nombre.isEmpty() && !tipo.isEmpty()) {

            Pedido pedido = new Pedido(nombre, tipo);
            modelo.agregarPedido(pedido);

            vista.mostrarMensaje("Pedido agregado correctamente.");
        } else {
            vista.mostrarMensaje("Los datos no pueden estar vacíos.");
        }
    }

    public void completarPedido() {

        String nombre = vista.solicitarNombre();

        if (modelo.completarPedido(nombre)) {
            vista.mostrarMensaje("Pedido marcado como completo.");
        } else {
            vista.mostrarMensaje("No se encontró un pedido pendiente con ese nombre.");
        }
    }

    public void eliminarPedido() {

        String nombre = vista.solicitarNombre();

        if (modelo.eliminarPedido(nombre)) {
            vista.mostrarMensaje("Pedido eliminado y agregado al historial.");
        } else {
            vista.mostrarMensaje("No se encontró el pedido.");
        }
    }

    public void mostrarPendientes() {

        List<Pedido> pendientes = modelo.obtenerPorEstado("Pendiente");

        vista.mostrarPedidos(pendientes);
    }

    public void mostrarCompletos() {

        List<Pedido> completos = modelo.obtenerPorEstado("Completo");

        vista.mostrarPedidos(completos);
    }

    public void mostrarContadorPendientes() {

        int cantidad = modelo.contarPendientes();

        vista.mostrarMensaje(
                "Cantidad de pedidos pendientes: " + cantidad
        );
    }

    public void mostrarHistorial() {

        vista.mostrarHistorial(modelo.getHistorial());
    }

    public void iniciar() {

        String opcion;

        do {

            vista.mostrarMenu();
            opcion = vista.solicitarOpcion();

            switch (opcion) {

                case "1":
                    agregarPedido();
                    break;

                case "2":
                    vista.mostrarPedidos(modelo.getPedidos());
                    break;

                case "3":
                    completarPedido();
                    break;

                case "4":
                    mostrarPendientes();
                    break;

                case "5":
                    mostrarCompletos();
                    break;

                case "6":
                    mostrarContadorPendientes();
                    break;

                case "7":
                    eliminarPedido();
                    break;

                case "8":
                    mostrarHistorial();
                    break;

                case "9":
                    vista.mostrarMensaje("Saliendo del programa...");
                    break;

                default:
                    vista.mostrarMensaje("Opción no válida.");
            }

        } while (!opcion.equals("9"));

        vista.cerrarScanner();
    }
}

// CLASE PRINCIPAL
public class Actividad3 {

    public static void main(String[] args) {

        PedidoModelo modelo = new PedidoModelo();
        PedidoVista vista = new PedidoVista();

        PedidoControlador controlador =
                new PedidoControlador(modelo, vista);

        controlador.iniciar();
    }
}

