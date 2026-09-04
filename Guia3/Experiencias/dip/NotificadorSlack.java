package dip;

public class NotificadorSlack implements ICanalNotificacion {
	public void enviarNotificacion(String destinatario, String mensaje) {
		System.out.println("Slack enviado a " + destinatario + ": " + mensaje);
	}
}