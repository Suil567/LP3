package ejercicio3;

import java.util.InputMismatchException;
import java.util.Scanner;

// VISTA: solo muestra mensajes y pide datos al usuario (no toca el archivo)
public class VistaEmpleado {

    private Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n--- MENU DE EMPLEADOS ---");
        System.out.println("1. Listar todos los empleados");
        System.out.println("2. Agregar un nuevo empleado");
        System.out.println("3. Buscar un empleado por su numero");
        System.out.println("4. Eliminar un empleado por su numero");
        System.out.println("5. Salir del programa");
        System.out.print("Elija una opcion: ");
    }

    // Lee la opcion del menu. Devuelve -1 si el usuario no escribio un numero
    public int leerOpcion() {
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine(); // limpia lo que se escribio mal
            return -1;
        }
    }

    // Pide los datos de un empleado. Devuelve null si algun dato no es valido
    public Empleado pedirEmpleado() {
        try {
            System.out.print("Numero: ");
            int numero = sc.nextInt();
            sc.nextLine(); // limpia el Enter que queda despues del numero
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Sueldo (ejemplo 1500.50): ");
            double sueldo = sc.nextDouble();
            return new Empleado(numero, nombre, sueldo);
        } catch (InputMismatchException e) {
            sc.nextLine();
            mostrarMensaje("Error: el dato escrito no es valido.");
            return null;
        }
    }

    // Pide un numero de empleado. Devuelve -1 si no es valido
    public int pedirNumero() {
        System.out.print("Numero del empleado: ");
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine();
            mostrarMensaje("Error: debe escribir un numero entero.");
            return -1;
        }
    }

    public void mostrarEmpleados(Empleado[] lista, int tamano) {
        if (tamano == 0) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (int i = 0; i < tamano; i++) {
                System.out.println(lista[i]);
            }
        }
    }

    public void mostrarEmpleado(Empleado empleado) {
        System.out.println(empleado);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
