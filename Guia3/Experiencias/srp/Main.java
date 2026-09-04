package srp;

public class Main {
	public static void main(String[] args) {
		Habitacion h1 = new Habitacion("101", "Individual", 100.0);
		ControladorReservas controlador = new ControladorReservas();

		controlador.agregarHabitacion(h1);

		controlador.crearReserva("C001", h1, "2026-09-10", "2026-09-12");

		controlador.crearReserva("C002", h1, "2026-09-11", "2026-09-15");

		System.out.println(h1.generarInformeOcupacion());
	}
}
