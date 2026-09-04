package ejercicio4;

public class GestorImpresion {
	private Imprimible[] equipos = new Imprimible[100];
	private int cantidad = 0;

	public void agregarEquipo(Imprimible equipo) {
		if (cantidad < equipos.length) {
			equipos[cantidad] = equipo;
			cantidad++;
		}
	}

	public void imprimirTodos() {
		imprimirArreglo(equipos, cantidad);
	}

	public static void imprimirArreglo(Imprimible[] arreglo, int limite) {
		for (int i = 0; i < limite; i++) {
			arreglo[i].imprimir();
		}
	}

	public int getCantidad() {
		return cantidad;
	}
}