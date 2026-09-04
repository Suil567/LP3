package ocp;

public class Reserva {
	private String idReserva;
	private String fechaInicio;
	private PoliticaCancelacion politicaCancelacion;

	public Reserva(String idReserva, String fechaInicio, PoliticaCancelacion politicaCancelacion) {
		this.idReserva = idReserva;
		this.fechaInicio = fechaInicio;
		this.politicaCancelacion = politicaCancelacion;
	}

	public String getIdReserva() {
		return idReserva;
	}

	public boolean cancelar() {
		if (politicaCancelacion.puedeCancelar(this)) {
			double penalizacion = politicaCancelacion.calcularPenalizacion();
			System.out.println("Cancelada con penalizacion: " + penalizacion);
			return true;
		}
		System.out.println("No se puede cancelar");
		return false;
	}
}