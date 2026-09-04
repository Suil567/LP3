package dip;

public class EnviadorSMS implements ICanalNotificacion {
	public void enviarNotificacion(String destinatario, String mensaje) {
		System.out.println("SMS enviado a " + destinatario + ": " + mensaje);
	}
}