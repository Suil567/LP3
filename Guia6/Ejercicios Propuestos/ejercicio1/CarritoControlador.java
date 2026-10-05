package ejercicio1;

public class CarritoControlador {
    private CarritoModelo modelo;
    private CarritoVista vista;

    public CarritoControlador(CarritoModelo modelo, CarritoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void agregarProducto() {
        String nombre = vista.solicitarTexto("Nombre del producto: ");
        double precio = Double.parseDouble(vista.solicitarTexto("Precio: "));
        modelo.agregarProducto(new Producto(nombre, precio));
        vista.mostrarMensaje("Producto agregado: " + nombre);
    }

    public void listarProductos() {
        vista.mostrarProductos("Productos de la tienda:", modelo.getProductos());
    }

    public void agregarAlCarrito() {
        String nombre = vista.solicitarTexto("Producto a comprar: ");
        Producto producto = modelo.buscarProducto(nombre);
        if (producto != null) {
            modelo.agregarAlCarrito(producto);
            vista.mostrarMensaje("Agregado al carrito: " + nombre);
        } else {
            vista.mostrarMensaje("Ese producto no existe.");
        }
    }

    public void verCarrito() {
        vista.mostrarProductos("Productos en el carrito:", modelo.getCarrito());
        vista.mostrarMensaje("Subtotal: S/ " + modelo.calcularSubtotal());
        vista.mostrarMensaje("Envio: S/ " + modelo.calcularEnvio());
        vista.mostrarMensaje("TOTAL: S/ " + modelo.calcularTotal());
    }

    public void eliminarDelCarrito() {
        String nombre = vista.solicitarTexto("Producto a eliminar: ");
        Producto producto = modelo.buscarProducto(nombre);
        if (producto != null && modelo.eliminarDelCarrito(producto)) {
            vista.mostrarMensaje("Producto eliminado del carrito.");
        } else {
            vista.mostrarMensaje("Ese producto no esta en el carrito.");
        }
    }

    public void aplicarDescuento() {
        String codigo = vista.solicitarTexto("Codigo de descuento (DESC10): ");
        if (codigo.equalsIgnoreCase("DESC10")) {
            modelo.setDescuento(0.10);
            vista.mostrarMensaje("Descuento del 10% aplicado.");
        } else {
            vista.mostrarMensaje("Codigo no valido.");
        }
    }

    public void calcularEnvio() {
        vista.mostrarMensaje("Envio: S/ " + modelo.calcularEnvio());
    }

    public void verHistorial() {
        vista.mostrarHistorial(modelo.getHistorial());
    }

    public void realizarCompra() {
        if (modelo.getCarrito().isEmpty()) {
            vista.mostrarMensaje("El carrito esta vacio.");
        } else {
            modelo.realizarCompra();
            vista.mostrarMensaje("Compra realizada con exito.");
        }
    }

    public void iniciar() {
        String opcion;

        do {
            vista.mostrarMenu();
            opcion = vista.solicitarOpcion();
            switch (opcion) {
                case "1":
                    agregarProducto();
                    break;
                case "2":
                    listarProductos();
                    break;
                case "3":
                    agregarAlCarrito();
                    break;
                case "4":
                    verCarrito();
                    break;
                case "5":
                    eliminarDelCarrito();
                    break;
                case "6":
                    aplicarDescuento();
                    break;
                case "7":
                    calcularEnvio();
                    break;
                case "8":
                    verHistorial();
                    break;
                case "9":
                    realizarCompra();
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