package ejercicio2;

import java.util.InputMismatchException;
import java.util.Scanner;

// Programa principal con el menu ampliado
public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gestor gestor = new Gestor();
        int opcion = -1;
        String nombre;

        while (opcion != 0) {
            System.out.println("\n--- GESTOR DE PERSONAJES ---");
            System.out.println("1. Mostrar personajes");
            System.out.println("2. Agregar personaje");
            System.out.println("3. Modificar personaje");
            System.out.println("4. Borrar personaje");
            System.out.println("5. Mostrar ordenados por un atributo");
            System.out.println("6. Cargar personajes aleatorios");
            System.out.println("7. Actualizar un solo atributo");
            System.out.println("8. Mostrar estadisticas");
            System.out.println("9. Importar personajes desde un archivo");
            System.out.println("10. Subir de nivel un personaje");
            System.out.println("0. Salir");
            System.out.print("Elija una opcion: ");

            try {
                opcion = sc.nextInt();

                if (opcion == 1) {
                    gestor.mostrar();
                } else if (opcion == 2) {
                    System.out.print("Nombre (una sola palabra): ");
                    nombre = sc.next();
                    System.out.print("Vida: ");
                    int vida = sc.nextInt();
                    System.out.print("Ataque: ");
                    int ataque = sc.nextInt();
                    System.out.print("Defensa: ");
                    int defensa = sc.nextInt();
                    System.out.print("Alcance: ");
                    int alcance = sc.nextInt();
                    gestor.agregar(nombre, vida, ataque, defensa, alcance);
                } else if (opcion == 3) {
                    System.out.print("Nombre del personaje a modificar: ");
                    nombre = sc.next();
                    System.out.print("Nueva vida: ");
                    int vida = sc.nextInt();
                    System.out.print("Nuevo ataque: ");
                    int ataque = sc.nextInt();
                    System.out.print("Nueva defensa: ");
                    int defensa = sc.nextInt();
                    System.out.print("Nuevo alcance: ");
                    int alcance = sc.nextInt();
                    gestor.modificar(nombre, vida, ataque, defensa, alcance);
                } else if (opcion == 4) {
                    System.out.print("Nombre del personaje a borrar: ");
                    nombre = sc.next();
                    gestor.borrar(nombre);
                } else if (opcion == 5) {
                    System.out.print("Atributo (1=vida, 2=ataque, 3=defensa, 4=alcance): ");
                    int atributo = sc.nextInt();
                    gestor.mostrarOrdenado(atributo);
                } else if (opcion == 6) {
                    System.out.print("Cuantos personajes aleatorios desea agregar: ");
                    int cantidad = sc.nextInt();
                    gestor.cargarAleatorios(cantidad);
                } else if (opcion == 7) {
                    System.out.print("Nombre del personaje: ");
                    nombre = sc.next();
                    System.out.print("Atributo (1=vida, 2=ataque, 3=defensa, 4=alcance): ");
                    int atributo = sc.nextInt();
                    System.out.print("Nuevo valor: ");
                    int valor = sc.nextInt();
                    gestor.actualizarAtributo(nombre, atributo, valor);
                } else if (opcion == 8) {
                    gestor.mostrarEstadisticas();
                } else if (opcion == 9) {
                    System.out.print("Nombre del archivo (ejemplo: importar.txt): ");
                    String ruta = sc.next();
                    gestor.importar(ruta);
                } else if (opcion == 10) {
                    System.out.print("Nombre del personaje: ");
                    nombre = sc.next();
                    gestor.subirNivel(nombre);
                } else if (opcion != 0) {
                    System.out.println("Opcion no valida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: debe escribir un numero entero.");
                sc.nextLine();
                opcion = -1;
            }
        }
        System.out.println("Programa terminado.");
    }
}
