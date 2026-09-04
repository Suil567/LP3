package isp;

public class ControladorServicios {
	public void atenderLimpieza(IServicioLimpieza servicio) {
		servicio.solicitarLimpieza();
	}

	public void atenderComida(IServicioComida servicio) {
		servicio.solicitarComida("Menu del dia");
	}
}