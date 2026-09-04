package ejercicio1;

public class CalculadoraPago {
	private Empleado[] empleados = new Empleado[100];
	private int cantidadEmpleado = 0;

	public void agregarEmpleado(Empleado empleado) {
		if (cantidadEmpleado < empleados.length) {
			empleados[cantidadEmpleado] = empleado;
			cantidadEmpleado++;
		}
	}

	public double calcularPagoMensual(Empleado empleado) {
		return empleado.getSalario();
	}

	public double calcularTotalPlanilla() {
		double total = 0.0;
		for (int i = 0; i < cantidadEmpleado; i++) {
			total += empleados[i].getSalario();
		}
		return total;
	}

	public int getCantidadEmpleado() {
		return cantidadEmpleado;
	}
}