package lsp;

public class HabitacionDeluxe extends Habitacion {
	public HabitacionDeluxe(String numero, double precioBase) {
		super(numero, precioBase);
	}

	public double calcularPrecio(int dias) {
		return precioBase * dias * 1.6;
	}
}