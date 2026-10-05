package ejercicio2;

public class InventarioControlador {
    private InventarioModelo modelo;
    private InventarioVista vista;

    public InventarioControlador(InventarioModelo modelo, InventarioVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void agregarItem() {
        String nombre = vista.solicitarTexto("Nombre del item: ");
        int cantidad = Integer.parseInt(vista.solicitarTexto("Cantidad: "));
        String tipo = vista.solicitarTexto("Tipo (Arma/Pocion): ");
        String descripcion = vista.solicitarTexto("Descripcion: ");
        modelo.agregarItem(new Item(nombre, cantidad, tipo, descripcion));
        vista.mostrarMensaje("Item agregado: " + nombre);
    }

    public void eliminarItem() {
        String nombre = vista.solicitarTexto("Nombre del item a eliminar: ");
        Item item = modelo.buscarItem(nombre);
        if (item != null) {
            modelo.eliminarItem(item);
            vista.mostrarMensaje("Item eliminado.");
        } else {
            vista.mostrarMensaje("No existe ese item.");
        }
    }

    public void verInventario() {
        vista.mostrarInventario(modelo.obtenerItems());
    }

    public void mostrarDetalles() {
        String nombre = vista.solicitarTexto("Nombre del item: ");
        Item item = modelo.buscarItem(nombre);
        if (item != null) {
            vista.mostrarDetallesItem(item);
        } else {
            vista.mostrarMensaje("No existe ese item.");
        }
    }

    public void buscarItem() {
        String nombre = vista.solicitarTexto("Nombre a buscar: ");
        Item item = modelo.buscarItem(nombre);
        if (item != null) {
            vista.mostrarMensaje("Encontrado: " + item.getNombre() + " x" + item.getCantidad());
        } else {
            vista.mostrarMensaje("No se encontro el item.");
        }
    }

    public void usarItem() {
        String nombre = vista.solicitarTexto("Nombre del item a usar: ");
        Item item = modelo.buscarItem(nombre);
        if (item != null) {
            vista.mostrarMensaje(item.usarItem());
        } else {
            vista.mostrarMensaje("No existe ese item.");
        }
    }

    public void iniciar() {
        String opcion;

        do {
            vista.mostrarMenu();
            opcion = vista.solicitarOpcion();
            switch (opcion) {
                case "1":
                    agregarItem();
                    break;
                case "2":
                    eliminarItem();
                    break;
                case "3":
                    verInventario();
                    break;
                case "4":
                    mostrarDetalles();
                    break;
                case "5":
                    buscarItem();
                    break;
                case "6":
                    usarItem();
                    break;
                case "0":
                    vista.mostrarMensaje("Saliendo...");
                    break;
                default:
                    vista.mostrarMensaje("Opcion no valida. Intentalo de nuevo.");
            }
        } while (!opcion.equals("0"));

        vista.cerrarScanner();
    }
}