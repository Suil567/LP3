import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Se pide la ruta por teclado (en vez de usar JFileChooser)
        System.out.println("Escriba el nombre del archivo o directorio:");
        Path ruta = Paths.get(sc.nextLine());

        if (Files.exists(ruta)) {
            // Se muestra la informacion del archivo o directorio
            System.out.printf("%n%s existe%n", ruta.getFileName());
            System.out.printf("%s un directorio%n", Files.isDirectory(ruta) ? "Es" : "No es");
            System.out.printf("%s una ruta absoluta%n", ruta.isAbsolute() ? "Es" : "No es");
            System.out.printf("Ultima modificacion: %s%n", Files.getLastModifiedTime(ruta));
            System.out.printf("Tamano: %s%n", Files.size(ruta));
            System.out.printf("Ruta: %s%n", ruta);
            System.out.printf("Ruta absoluta: %s%n", ruta.toAbsolutePath());

            // Si es un directorio, se muestra su contenido
            if (Files.isDirectory(ruta)) {
                System.out.printf("%nContenido del directorio:%n");
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