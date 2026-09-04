package ejercicio2;

public class Lienzo {
	private Forma[] formas = new Forma[100];
	private int cantidad = 0;

	public void agregarForma(Forma forma) {
		if (cantidad < formas.length) {
			formas[cantidad] = forma;
			cantidad++;
		}
	}

	public void dibujarTodas() {
		dibujarArreglo(formas, cantidad);
	}

	public static void dibujarArreglo(Forma[] arreglo, int limite) {
		for (int i = 0; i < limite; i++) {
			arreglo[i].dibujar();
		}
	}

	public int getCantidad() {
		return cantidad;
	}
}