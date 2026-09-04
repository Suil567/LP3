package ocp;

public interface PoliticaCancelacion {
	boolean puedeCancelar(Reserva reserva);
	double calcularPenalizacion();
}