package srp;

public class ControladorReservas {
	private Habitacion[] habitaciones = new Habitacion[100];
	private int cantidadHabitaciones = 0;

	public void agregarHabitacion(Habitacion habitacion) {
		if (cantidadHabitaciones < habitaciones.length) {
			habitaciones[cantidadHabitaciones] = habitacion;
			cantidadHabitaciones++;
		}
	}

	public void crearReserva(String idCliente, Habitacion habitacion, String fInicio, String fFin) {
		GestorDisponibilidadHabitacion gestor = habitacion.getGestorDisponibilidad();
		if (gestor.verificarDisponibilidad(fInicio, fFin)) {
			gestor.marcarComoReservada(fInicio, fFin);
			System.out.println("Reserva creada para cliente " + idCliente);
		} else {
			System.out.println("Habitacion no disponible");
		}
	}
}