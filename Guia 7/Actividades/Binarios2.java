/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Binarios2 {

    private static final String ARCHIVO = "matriz.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US); // los decimales se escriben con punto: 2.5

        try {
            int filas = leerEnteroPositivo(sc, "Número de filas: ");
            int columnas = leerEnteroPositivo(sc, "Número de columnas: ");

            double[][] matriz = new double[filas][columnas]; // se crea la matriz

            // lectura de datos por teclado
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    matriz[i][j] = leerDouble(sc, "matriz[" + i + "][" + j + "]: ");
                }
            }

            if (guardarMatriz(matriz)) {
                mostrarTamanoArchivo(filas, columnas);
                leerMatriz(); // comprueba que lo escrito se puede leer en el mismo orden
            }
        } catch (NoSuchElementException e) {
            System.out.println("\nNo hay más datos de entrada. Programa terminado.");
        }
    }

    // Escribe filas, columnas y valores. try-with-resources cierra los flujos automáticamente.
    private static boolean guardarMatriz(double[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        try (FileOutputStream fos = new FileOutputStream(ARCHIVO);
             DataOutputStream salida = new DataOutputStream(fos)) {

            // escribir el número de filas y columnas (una sola vez)
            salida.writeInt(filas);
            salida.writeInt(columnas);

            // escribir la matriz fila por fila
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    salida.writeDouble(matriz[i][j]);
                }
            }
            System.out.println("\nMatriz guardada en " + ARCHIVO);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo crear el archivo: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
        return false;
    }

    // Lee el archivo en el mismo orden en que se escribió: int, int, double 
    private static void leerMatriz() {
        try (FileInputStream fis = new FileInputStream(ARCHIVO);
             DataInputStream entrada = new DataInputStream(fis)) {

            int filas = entrada.readInt();
            int columnas = entrada.readInt();
            System.out.println("\nLectura del archivo -> filas: " + filas + ", columnas: " + columnas);

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    System.out.print(entrada.readDouble() + "\t");
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existe el archivo: " + e.getMessage());
        } catch (EOFException e) {
            System.out.println("El archivo está incompleto (se llegó al final antes de lo esperado).");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Un archivo correcto pesa 8 bytes (2 int) + 8 bytes por cada double. 
    private static void mostrarTamanoArchivo(int filas, int columnas) {
        try {
            long real = Files.size(Paths.get(ARCHIVO));
            long esperado = 8L + 8L * filas * columnas;
            System.out.println("Tamaño del archivo: " + real + " bytes (esperado: " + esperado + ")");
        } catch (IOException e) {
            System.out.println("No se pudo obtener el tamaño del archivo: " + e.getMessage());
        }
    }

    private static int leerEnteroPositivo(Scanner sc, String mensaje) {
        int valor = 0;
        do {
            System.out.print(mensaje);
            try {
                valor = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next(); // descarta lo escrito
                System.out.println("Debe escribir un número entero.");
                continue;  // vuelve a pedir el dato
            }
            if (valor <= 0) {
                System.out.println("El valor debe ser mayor que 0.");
            }
        } while (valor <= 0);
        return valor;
    }

    private static double leerDouble(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Debe escribir un número (use punto para los decimales).");
            }
        }
    }
}