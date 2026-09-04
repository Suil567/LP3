package ejercicio4;

public class GestorEscaneo {
	private Escaneable[] equipos = new Escaneable[100];
	private int cantidad = 0;

	public void agregarEquipo(Escaneable equipo) {
		if (cantidad < equipos.length) {
			equipos[cantidad] = equipo;
			cantidad++;
		}
	}

	public void escanearTodos() {
		escanearArreglo(equipos, cantidad);
	}

	public static void escanearArreglo(Escaneable[] arreglo, int limite) {
		for (int i = 0; i < limite; i++) {
			arreglo[i].escanear();
		}
	}

	public int getCantidad() {
		return cantidad;
	}
}