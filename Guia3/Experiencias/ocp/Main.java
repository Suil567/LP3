package ocp;

public class Main {
	public static void main(String[] args) {
		ControladorReservas controlador = new ControladorReservas();
		
		controlador.crearReserva("R001", "2026-09-10", new PoliticaCancelacionFlexible());
		controlador.crearReserva("R002", "2026-09-15", new PoliticaCancelacionEstricta());
		
		controlador.procesarCancelacion("R001");
		controlador.procesarCancelacion("R002");
	}
}