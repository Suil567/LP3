package ejercicio3;

public class GestorVehiculos {
	private Vehiculo[] vehiculos = new Vehiculo[100];
	private int cantidad = 0;

	public void agregarVehiculo(Vehiculo vehiculo) {
		if (cantidad < vehiculos.length) {
			vehiculos[cantidad] = vehiculo;
			cantidad++;
		}
	}

	public void acelerarTodos() {
		acelerarArreglo(vehiculos, cantidad);
	}

	public static void acelerarArreglo(Vehiculo[] arreglo, int limite) {
		for (int i = 0; i < limite; i++) {
			arreglo[i].acelerar();
		}
	}

	public int getCantidad() {
		return cantidad;
	}
}