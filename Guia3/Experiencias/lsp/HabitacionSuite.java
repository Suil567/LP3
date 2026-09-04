package lsp;

public class HabitacionSuite extends Habitacion {
	public HabitacionSuite(String numero, double precioBase) {
		super(numero, precioBase);
	}

	public double calcularPrecio(int dias) {
		return precioBase * dias * 1.3;
	}
}