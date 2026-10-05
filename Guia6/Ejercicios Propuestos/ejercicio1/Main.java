package ejercicio1;

public class Main {
    public static void main(String[] args) {
        CarritoModelo modelo = new CarritoModelo();
        CarritoVista vista = new CarritoVista();
        CarritoControlador controlador = new CarritoControlador(modelo, vista);

        modelo.agregarProducto(new Producto("Mouse", 30));
        modelo.agregarProducto(new Producto("Teclado", 80));
        modelo.agregarProducto(new Producto("Monitor", 450));

        controlador.iniciar();
    }
}