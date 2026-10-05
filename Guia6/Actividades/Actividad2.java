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

    public Pedido(String nombrePlato, String tipo) {
        this.nombrePlato = nombrePlato;
        this.tipo = tipo;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
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

    public boolean eliminarPedido(String nombre) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getNombrePlato().equalsIgnoreCase(nombre)) {
                pedidos.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean actualizarPedido(String nombreActual, String nuevoNombre) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNombrePlato().equalsIgnoreCase(nombreActual)) {
                pedido.setNombrePlato(nuevoNombre);
                return true;
            }
        }
        return false;
    }

    public List<Pedido> buscarPedido(String busqueda) {
        List<Pedido> resultados = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            if (pedido.getNombrePlato().equalsIgnoreCase(busqueda)
                    || pedido.getTipo().equalsIgnoreCase(busqueda)) {
                resultados.add(pedido);
            }
        }

        return resultados;
    }

    public int contarPedidos() {
        return pedidos.size();
    }

    public int contarPorTipo(String tipo) {
        int contador = 0;

        for (Pedido pedido : pedidos) {
            if (pedido.getTipo().equalsIgnoreCase(tipo)) {
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

    public String solicitarNombrePlato() {
        System.out.print("Ingrese el nombre del plato: ");
        return scanner.nextLine();
    }

    public String solicitarTipo() {
        System.out.print("Ingrese el tipo de plato: ");
        return scanner.nextLine();
    }

    public void mostrarPedidos(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos.");
        } else {
            System.out.println("\nLista de pedidos:");

            for (Pedido pedido : pedidos) {
                System.out.println("Plato: " + pedido.getNombrePlato()
                        + " | Tipo: " + pedido.getTipo());
            }
        }
    }

    public void mostrarMenu() {
        System.out.println("\n===== GESTIÓN DE PEDIDOS =====");
        System.out.println("1. Agregar Pedido");
        System.out.println("2. Mostrar Pedidos");
        System.out.println("3. Eliminar Pedido");
        System.out.println("4. Actualizar Pedido");
        System.out.println("5. Buscar Pedido");
        System.out.println("6. Contar Pedidos");
        System.out.println("7. Salir");
    }

    public String solicitarOpcion() {
        System.out.print("Seleccione una opción: ");
        return scanner.nextLine();
    }

    public String solicitarBusqueda() {
        System.out.print("Ingrese el nombre o tipo a buscar: ");
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
        String nombre = vista.solicitarNombrePlato();
        String tipo = vista.solicitarTipo();

        if (!nombre.isEmpty() && !tipo.isEmpty()) {
            modelo.agregarPedido(new Pedido(nombre, tipo));
            vista.mostrarMensaje("Pedido agregado correctamente.");
        } else {
            vista.mostrarMensaje("El nombre y el tipo no pueden estar vacíos.");
        }
    }

    public void eliminarPedido() {
        String nombre = vista.solicitarNombrePlato();

        if (modelo.eliminarPedido(nombre)) {
            vista.mostrarMensaje("Pedido eliminado correctamente.");
        } else {
            vista.mostrarMensaje("No se encontró el pedido.");
        }
    }

    public void actualizarPedido() {
        String nombreActual = vista.solicitarNombrePlato();
        String nuevoNombre = vista.solicitarNombrePlato();

        if (modelo.actualizarPedido(nombreActual, nuevoNombre)) {
            vista.mostrarMensaje("Pedido actualizado correctamente.");
        } else {
            vista.mostrarMensaje("No se encontró el pedido.");
        }
    }

    public void buscarPedido() {
        String busqueda = vista.solicitarBusqueda();
        List<Pedido> resultados = modelo.buscarPedido(busqueda);

        vista.mostrarPedidos(resultados);
    }

    public void contarPedidos() {
        vista.mostrarMensaje("Total de pedidos: " + modelo.contarPedidos());

        vista.mostrarMensaje("Pedidos por tipo:");
        vista.mostrarMensaje("Entrada: " + modelo.contarPorTipo("Entrada"));
        vista.mostrarMensaje("Plato principal: " + modelo.contarPorTipo("Plato principal"));
        vista.mostrarMensaje("Postre: " + modelo.contarPorTipo("Postre"));
        vista.mostrarMensaje("Bebida: " + modelo.contarPorTipo("Bebida"));
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
                    eliminarPedido();
                    break;

                case "4":
                    actualizarPedido();
                    break;

                case "5":
                    buscarPedido();
                    break;

                case "6":
                    contarPedidos();
                    break;

                case "7":
                    vista.mostrarMensaje("Saliendo del programa...");
                    break;

                default:
                    vista.mostrarMensaje("Opción no válida.");
            }

        } while (!opcion.equals("7"));

        vista.cerrarScanner();
    }
}

// CLASE PRINCIPAL
public class Actividad2 {
    public static void main(String[] args) {
        PedidoModelo modelo = new PedidoModelo();
        PedidoVista vista = new PedidoVista();
        PedidoControlador controlador = new PedidoControlador(modelo, vista);

        controlador.iniciar();
    }
}

