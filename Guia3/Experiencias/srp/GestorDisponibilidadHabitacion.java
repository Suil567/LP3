package srp;

public class GestorDisponibilidadHabitacion {
	private Reserva[] reservas = new Reserva[100];
	private int cantidad = 0;

	public boolean verificarDisponibilidad(String fechaInicio, String fechaFin) {
		for (int i = 0; i < cantidad; i++) {
			Reserva r = reservas[i];
			if (haySolapamiento(r.getFechaInicio(), r.getFechaFin(), fechaInicio, fechaFin)) {
				return false;
			}
		}
		return true;
	}

	public static boolean haySolapamiento(String inicio1, String fin1, String inicio2, String fin2) {
		return inicio1.compareTo(fin2) < 0 && fin1.compareTo(inicio2) > 0;
	}

	public void marcarComoReservada(String fechaInicio, String fechaFin) {
		if (cantidad < reservas.length) {
			reservas[cantidad] = new Reserva(fechaInicio, fechaFin);
			cantidad++;
		}
	}

	public void marcarComoDisponible(String fechaInicio, String fechaFin) {
		for (int i = 0; i < cantidad; i++) {
			if (reservas[i].getFechaInicio().equals(fechaInicio) && reservas[i].getFechaFin().equals(fechaFin)) {
				for (int j = i; j < cantidad - 1; j++) {
					reservas[j] = reservas[j + 1];
				}
				reservas[cantidad - 1] = null;
				cantidad--;
				break;
			}
		}
	}

	public int getCantidadReservas() {
		return cantidad;
	}
}