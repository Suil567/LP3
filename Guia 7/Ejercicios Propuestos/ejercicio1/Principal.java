package ejercicio1;

import java.util.InputMismatchException;
import java.util.Scanner;

// Programa principal: muestra un menu para probar el gestor
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
                } else if (opcion != 0) {
                    System.out.println("Opcion no valida.");
                }
            } catch (InputMismatchException e) {
                // Si el usuario escribe letras donde va un numero
                System.out.println("Error: debe escribir un numero entero.");
                sc.nextLine(); // limpia lo que se escribio mal
                opcion = -1;
            }
        }
        System.out.println("Programa terminado.");
    }
}
