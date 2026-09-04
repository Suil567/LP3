package lsp;

public class HabitacionEstandar extends Habitacion {
	public HabitacionEstandar(String numero, double precioBase) {
		super(numero, precioBase);
	}

	public double calcularPrecio(int dias) {
		return precioBase * dias;
	}
}