package isp;

public class Main {
	public static void main(String[] args) {
		ControladorServicios controlador = new ControladorServicios();
		HabitacionEstandar estandar = new HabitacionEstandar();
		controlador.atenderLimpieza(estandar);

		SuiteLujo suite = new SuiteLujo();
		controlador.atenderComida(suite);

		String[] ropa = {"Camisa", "Pantalón"};
		suite.solicitarLavanderia(ropa);
	}
}