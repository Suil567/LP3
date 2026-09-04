package lsp;

public class ControladorReservas {
	private Habitacion[] habitaciones = new Habitacion[100];
	private int cantidadHabitaciones = 0;

	public void agregarHabitacion(Habitacion habitacion) {
		if (cantidadHabitaciones < habitaciones.length) {
			habitaciones[cantidadHabitaciones] = habitacion;
			cantidadHabitaciones++;
		}
	}

	public void procesarReserva(Habitacion h, String fInicio, String fFin) {
		if (h.esReservable()) {
			h.reservar(fInicio, fFin);
		} else {
			System.out.println("Esta habitacion no se puede reservar");
		}
	}

	public void procesarTodasLasReservas(String fInicio, String fFin) {
		for (int i = 0; i < cantidadHabitaciones; i++) {
			procesarReserva(habitaciones[i], fInicio, fFin);
		}
	}
}