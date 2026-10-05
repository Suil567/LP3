package ejercicio3;

public class Jugador {
    private String nombre;
    private int salud;
    private int nivel;
    private InventarioModelo inventario;
    private Item arma; // arma equipada (null = punos)

    public Jugador(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.salud = 100;
        this.inventario = new InventarioModelo();
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public int getNivel() {
        return nivel;
    }

    public InventarioModelo getInventario() {
        return inventario;
    }

    public int atacar(Enemigo enemigo) {
        int dano = nivel + 2; // con los punos
        if (arma != null) {
            dano = nivel + arma.getPoder(); // con el arma
        }
        enemigo.recibirDano(dano);
        return dano;
    }

    public String usarObjeto(Item item) {
        if (item.getTipo().equals("Arma")) {
            arma = item;
            return "Equipaste " + item.getNombre();
        }
        salud = Math.min(100, salud + item.getPoder());
        item.setCantidad(item.getCantidad() - 1);
        if (item.getCantidad() == 0) {
            inventario.eliminarItem(item);
        }
        return "Bebiste " + item.getNombre() + " y recuperas " + item.getPoder() + " de salud";
    }

    public void recibirDano(int dano) {
        salud = Math.max(0, salud - dano);
    }
}