package ocp;

public class PoliticaCancelacionModerada implements PoliticaCancelacion {
	public boolean puedeCancelar(Reserva reserva) {
		return true;
	}

	public double calcularPenalizacion() {
		return 0.5;
	}
}