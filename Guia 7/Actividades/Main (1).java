/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    private static final String ARCHIVO = "datos.txt";

    public static void main(String[] args) {
        int lineasEscritas = 0;

        // try-with-resources: Scanner y PrintWriter se cierran solos al terminar el bloque.
        try (Scanner sc = new Scanner(System.in);
             PrintWriter salida = new PrintWriter(new FileWriter(ARCHIVO, true))) {

            System.out.println("Introduce texto. Para acabar introduce la cadena FIN:");
            String cadena = leerLinea(sc);
            while (!cadena.trim().equalsIgnoreCase("FIN")) {
                salida.println(cadena);
                lineasEscritas++;
                cadena = leerLinea(sc);
            }

            if (salida.checkError()) {
                System.out.println("Ocurrió un error al escribir en el archivo.");
            }
        } catch (IOException e) {
            System.out.println("No se pudo abrir o escribir el archivo " + ARCHIVO + ": " + e.getMessage());
            return;
        }

        System.out.println("Se añadieron " + lineasEscritas + " línea(s) al final de " + ARCHIVO);
        mostrarContenido();
    }

    // Lee una línea; si ya no hay más entrada devuelve FIN para terminar de forma ordenada.
    private static String leerLinea(Scanner sc) {
        return sc.hasNextLine() ? sc.nextLine() : "FIN";
    }

    // Muestra el contenido completo de datos.txt para comprobar que se conservó lo anterior. 
    private static void mostrarContenido() {
        System.out.println("\nContenido actual de " + ARCHIVO + ":");
        try (BufferedReader lector = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo: " + e.getMessage());
        }
    }
}