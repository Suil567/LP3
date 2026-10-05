package ejercicio3;

public class Enemigo {
    private String nombre;
    private int salud;
    private int nivel;
    private String tipo;

    public Enemigo(String nombre, int salud, int nivel, String tipo) {
        this.nombre = nombre;
        this.salud = salud;
        this.nivel = nivel;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public int atacar(Jugador jugador) {
        int dano = nivel * 3;
        jugador.recibirDano(dano);
        return dano;
    }

    public void recibirDano(int dano) {
        salud = Math.max(0, salud - dano);
    }
}