package ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CombateVista vista = new CombateVista();
        String nombre = vista.solicitarTexto("Nombre de tu jugador: ");
        Jugador jugador = new Jugador(nombre, 1);
        jugador.getInventario().agregarItem(new Item("Espada", 1, "Arma", "Espada de hierro", 10));
        jugador.getInventario().agregarItem(new Item("Pocion", 3, "Pocion", "Cura 30", 30));

        List<Enemigo> enemigos = new ArrayList<>();
        enemigos.add(new Enemigo("Goblin", 40, 1, "Bestia"));
        enemigos.add(new Enemigo("Esqueleto", 60, 2, "No-muerto"));

        CombateControlador controlador = new CombateControlador(jugador, enemigos, vista);
        controlador.iniciar();
    }
}