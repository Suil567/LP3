package lsp;

public class Main {
	public static void main(String[] args) {
		ControladorReservas controlador = new ControladorReservas();

		Habitacion estandar = new HabitacionEstandar("101", 80.0);
		Habitacion suite = new HabitacionSuite("201", 150.0);
		Habitacion deluxe = new HabitacionDeluxe("301", 220.0);

		controlador.agregarHabitacion(estandar);
		controlador.agregarHabitacion(suite);
		controlador.agregarHabitacion(deluxe);

		System.out.println("--- Procesando reserva individual ---");
		controlador.procesarReserva(suite, "2026-09-10", "2026-09-12");

		System.out.println("\n--- Procesando lista de habitaciones ---");
		controlador.procesarTodasLasReservas("2026-10-01", "2026-10-05");
	}
}