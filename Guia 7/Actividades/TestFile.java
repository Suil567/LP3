/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TestFile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("Escriba el nombre del archivo o directorio:");
        if (!sc.hasNextLine()) {
            System.out.println("No se ingresó ninguna ruta.");
            return;
        }


        try {
            String texto = sc.nextLine().trim();
            // "Copiar como ruta" de Windows agrega comillas; se quitan para que la ruta sea válida
            if (texto.length() >= 2 && texto.startsWith("\"") && texto.endsWith("\"")) {
                texto = texto.substring(1, texto.length() - 1);
            }
            // crear objeto Path con base en la entrada del usuario
            Path ruta = Paths.get(texto);
            mostrarInformacion(ruta);
        } catch (InvalidPathException e) {
            System.out.println("La ruta ingresada no es válida: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }


   
    private static void mostrarInformacion(Path ruta) throws IOException {
        if (Files.exists(ruta)) {
                       Path nombre = (ruta.getFileName() != null) ? ruta.getFileName() : ruta;


            System.out.printf("%n%s existe%n", nombre);
            System.out.printf("%s un directorio%n", Files.isDirectory(ruta) ? "Es" : "No es");
            System.out.printf("%s una ruta absoluta%n", ruta.isAbsolute() ? "Es" : "No es");
            System.out.printf("Fecha de última modificación: %s%n", Files.getLastModifiedTime(ruta));
            System.out.printf("Tamaño: %s bytes%n", Files.size(ruta));
            System.out.printf("Ruta: %s%n", ruta);
            System.out.printf("Ruta absoluta: %s%n", ruta.toAbsolutePath());


            if (Files.isDirectory(ruta)) {
                System.out.printf("%nContenido del directorio:%n");


                // se cierra automáticamente
                try (DirectoryStream<Path> flujoDirectorio = Files.newDirectoryStream(ruta)) {
                    for (Path p : flujoDirectorio) {
                        System.out.println(p);
                    }
                }
            }
        } else {
            System.out.printf("%s no existe%n", ruta);
        }
    }
}
