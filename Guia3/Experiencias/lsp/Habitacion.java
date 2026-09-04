package lsp;

public abstract class Habitacion {
	protected String numero;
	protected double precioBase;
	protected boolean disponibleParaReserva;

	public Habitacion(String numero, double precioBase) {
		this.numero = numero;
		this.precioBase = precioBase;
		this.disponibleParaReserva = true;
	}

	public boolean esReservable() {
		return disponibleParaReserva;
	}

	public void reservar(String fInicio, String fFin) {
		System.out.println("Habitacion " + numero + " reservada");
	}

	public abstract double calcularPrecio(int dias);
}