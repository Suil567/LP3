package dip;

public class ControladorReservas {
	private Reserva[] reservas = new Reserva[100];
	private int cantidadReservas = 0;

	public void registrarReserva(Reserva reserva) {
		if (cantidadReservas < reservas.length) {
			reservas[cantidadReservas] = reserva;
			cantidadReservas++;
		}
	}

	public void confirmarReserva(Reserva reserva, ICanalNotificacion canal) {
		NotificadorReserva notificador = new NotificadorReserva(canal);
		notificador.notificarConfirmacion(reserva);
	}

	public void confirmarReservaMulticanal(Reserva reserva, ICanalNotificacion[] canales) {
		if (canales.length > 0) {
			NotificadorReserva notificador = new NotificadorReserva(canales[0]);
			for (int i = 1; i < canales.length; i++) {
				notificador.agregarCanal(canales[i]);
			}
			notificador.notificarConfirmacion(reserva);
		}
	}
}