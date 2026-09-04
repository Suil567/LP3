package dip;

public class NotificadorReserva {
	private ICanalNotificacion[] canales = new ICanalNotificacion[10];
	private int cantidadCanales = 0;

	public NotificadorReserva(ICanalNotificacion canalInicial) {
		agregarCanal(canalInicial);
	}

	public void agregarCanal(ICanalNotificacion canal) {
		if (cantidadCanales < canales.length) {
			canales[cantidadCanales] = canal;
			cantidadCanales++;
		}
	}

	public void notificarConfirmacion(Reserva reserva) {
		for (int i = 0; i < cantidadCanales; i++) {
			canales[i].enviarNotificacion("cliente", "Reserva confirmada: " + reserva.getIdReserva());
		}
	}
}