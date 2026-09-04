package ocp;

public class ControladorReservas {
	private Reserva[] reservas = new Reserva[100];
	private int cantidadReservas = 0;

	public void crearReserva(String idReserva, String fechaInicio, PoliticaCancelacion politica) {
		if (cantidadReservas < reservas.length) {
			reservas[cantidadReservas] = new Reserva(idReserva, fechaInicio, politica);
			cantidadReservas++;
		}
	}

	public void procesarCancelacion(String idReserva) {
		for (int i = 0; i < cantidadReservas; i++) {
			if (reservas[i].getIdReserva().equals(idReserva)) {
				reservas[i].cancelar();
				break;
			}
		}
	}
}