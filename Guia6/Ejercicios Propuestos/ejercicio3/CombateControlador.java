package ejercicio3;

import java.util.List;
import java.util.Random;

public class CombateControlador {
    private Jugador jugador;
    private List<Enemigo> enemigos;
    private CombateVista vista;
    private Random random;

    public CombateControlador(Jugador jugador, List<Enemigo> enemigos, CombateVista vista) {
        this.jugador = jugador;
        this.enemigos = enemigos;
        this.vista = vista;
        this.random = new Random();
    }

    // Devuelve true si el jugador gasto su turno
    public boolean atacarEnemigo() {
        int numero = Integer.parseInt(vista.solicitarTexto("Numero del enemigo: ")) - 1;
        if (numero < 0 || numero >= enemigos.size()) {
            vista.mostrarMensaje("Ese enemigo no existe.");
            return false;
        }
        Enemigo enemigo = enemigos.get(numero);
        int dano = jugador.atacar(enemigo);
        vista.mostrarMensaje("Atacas a " + enemigo.getNombre() + " y le haces " + dano + " de dano.");
        if (enemigo.getSalud() == 0) {
            vista.mostrarMensaje(enemigo.getNombre() + " fue derrotado.");
            enemigos.remove(enemigo);
        }
        return true;
    }

    // Devuelve true si el jugador gasto su turno
    public boolean usarObjeto() {
        vista.mostrarInventario(jugador.getInventario().obtenerItems());
        String nombre = vista.solicitarTexto("Objeto a usar: ");
        Item item = jugador.getInventario().buscarItem(nombre);
        if (item == null) {
            vista.mostrarMensaje("No tienes ese objeto.");
            return false;
        }
        vista.mostrarMensaje(jugador.usarObjeto(item));
        return true;
    }

    // Cada enemigo hace una accion al azar
    public void turnoEnemigos() {
        for (Enemigo enemigo : enemigos) {
            if (jugador.getSalud() == 0) {
                break;
            }
            if (random.nextInt(3) < 2) {
                int dano = enemigo.atacar(jugador);
                vista.mostrarMensaje(enemigo.getNombre() + " te ataca y te hace " + dano + " de dano.");
            } else {
                vista.mostrarMensaje(enemigo.getNombre() + " falla su ataque.");
            }
        }
    }

    public void iniciar() {
        while (jugador.getSalud() > 0 && !enemigos.isEmpty()) {
            vista.mostrarEstado(jugador, enemigos);
            vista.mostrarMenu();
            String opcion = vista.solicitarOpcion();
            boolean gastoTurno = false;

            switch (opcion) {
                case "1":
                    gastoTurno = atacarEnemigo();
                    break;
                case "2":
                    gastoTurno = usarObjeto();
                    break;
                default:
                    vista.mostrarMensaje("Opcion no valida. Intentalo de nuevo.");
            }

            if (gastoTurno) {
                turnoEnemigos();
            }
        }

        if (jugador.getSalud() > 0) {
            vista.mostrarMensaje("GANASTE!");
        } else {
            vista.mostrarMensaje("PERDISTE...");
        }
        vista.cerrarScanner();
    }
}