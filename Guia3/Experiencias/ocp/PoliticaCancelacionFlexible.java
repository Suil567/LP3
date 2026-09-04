package ocp;

public class PoliticaCancelacionFlexible implements PoliticaCancelacion {
	public boolean puedeCancelar(Reserva reserva) {
		return true;
	}

	public double calcularPenalizacion() {
		return 0.0;
	}
}