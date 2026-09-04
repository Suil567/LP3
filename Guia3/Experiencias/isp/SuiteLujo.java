package isp;

public class SuiteLujo implements IServicioLimpieza, IServicioComida, IServicioLavanderia {
	public void solicitarLimpieza() {
		System.out.println("Limpieza solicitada");
	}

	public void solicitarComida(String opcion) {
		System.out.println("Comida solicitada: " + opcion);
	}

	public void solicitarLavanderia(String[] prendas) {
		System.out.println("Lavanderia solicitada para " + prendas.length + " prendas");
	}
}