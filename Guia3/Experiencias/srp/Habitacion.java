package srp;

public class Habitacion {
	private String numero;
	private String tipo;
	private double precioBase;
	private GestorDisponibilidadHabitacion gestorDisponibilidad;

	public Habitacion(String numero, String tipo, double precioBase) {
		this.numero = numero;
		this.tipo = tipo;
		this.precioBase = precioBase;
		this.gestorDisponibilidad = new GestorDisponibilidadHabitacion();
	}

	public double calcularPrecio(String temporada) {
		if (temporada.equals("ALTA")) {
			return precioBase * 1.5;
		}
		return precioBase;
	}

	public String generarInformeOcupacion() {
		return "Habitacion " + numero + " - Reservas activas: " + gestorDisponibilidad.getCantidadReservas();
	}

	public GestorDisponibilidadHabitacion getGestorDisponibilidad() {
		return gestorDisponibilidad;
	}
}