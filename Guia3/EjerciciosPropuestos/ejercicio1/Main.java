package ejercicio1;

public class Main {
	public static void main(String[] args) {
		Empleado emp1 = new Empleado("Juan Chocano", 2500.0, "Ventas");
		Empleado emp2 = new Empleado("Leonardo Melo", 3200.0, "Sistemas");

		CalculadoraPago calculadora = new CalculadoraPago();

		calculadora.agregarEmpleado(emp1);
		calculadora.agregarEmpleado(emp2);

		System.out.println("Pago mensual de " + emp1.getNombre() + ": " + calculadora.calcularPagoMensual(emp1));
		System.out.println("Pago mensual de " + emp2.getNombre() + ": " + calculadora.calcularPagoMensual(emp2));

		System.out.println("Total de la planilla: " + calculadora.calcularTotalPlanilla());
	}
}