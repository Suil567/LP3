package ejercicio2;

public class Main {
    public static void main(String[] args) {
        InventarioModelo modelo = new InventarioModelo();
        InventarioVista vista = new InventarioVista();
        InventarioControlador controlador = new InventarioControlador(modelo, vista);

        modelo.agregarItem(new Item("Espada", 1, "Arma", "Espada de hierro"));
        modelo.agregarItem(new Item("Pocion", 3, "Pocion", "Recupera salud"));

        controlador.iniciar();
    }
}