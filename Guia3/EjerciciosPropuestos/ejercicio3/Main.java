package ejercicio3;

public class Main {
	public static void main(String[] args) {
		GestorVehiculos gestor = new GestorVehiculos();

		gestor.agregarVehiculo(new Coche());
		gestor.agregarVehiculo(new Bicicleta());

		System.out.println("--- Acelerando vehículos desde el gestor ---");
		gestor.acelerarTodos();

		System.out.println("\n--- Acelerando desde arreglo nativo directo ---");
		Vehiculo[] vehiculosDirectos = { new Coche(), new Bicicleta() };
		GestorVehiculos.acelerarArreglo(vehiculosDirectos, vehiculosDirectos.length);
	}
}