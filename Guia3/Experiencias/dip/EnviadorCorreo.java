package dip;

public class EnviadorCorreo implements ICanalNotificacion {
	public void enviarNotificacion(String destinatario, String mensaje) {
		System.out.println("Correo enviado a " + destinatario + ": " + mensaje);
	}
}