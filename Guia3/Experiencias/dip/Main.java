//package dip;
//
//public class Main {
//	public static void main(String[] args) {
//		ControladorReservas controlador = new ControladorReservas();
//		Reserva reserva1 = new Reserva("R001");
//		Reserva reserva2 = new Reserva("R002");
//
//		controlador.registrarReserva(reserva1);
//		controlador.registrarReserva(reserva2);
//
//		System.out.println("--- Notificación individual ---");
//		controlador.confirmarReserva(reserva1, new EnviadorCorreo());
//
//		System.out.println("\n--- Notificación multicanal usando arreglo ---");
//		ICanalNotificacion[] canales = {
//			new EnviadorCorreo(),
//			new EnviadorSMS(),
//			new NotificadorSlack()
//		};
//		controlador.confirmarReservaMulticanal(reserva2, canales);
//	}
//}