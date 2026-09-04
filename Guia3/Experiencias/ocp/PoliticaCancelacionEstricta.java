package ocp;

public class PoliticaCancelacionEstricta implements PoliticaCancelacion {
	public boolean puedeCancelar(Reserva reserva) {
		return false;
	}

	public double calcularPenalizacion() {
		return 1.0;
	}
}