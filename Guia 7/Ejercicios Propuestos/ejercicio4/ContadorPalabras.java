package ejercicio4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

// Lee un archivo de texto y cuenta lineas, palabras y caracteres
public class ContadorPalabras {

    // Datos que se llenan al leer el archivo
    static String[] palabras = new String[1000]; // palabras distintas
    static int[] conteos = new int[1000];        // cuantas veces aparece cada una
    static int diferentes = 0;                   // cuantas palabras distintas hay
    static int totalPalabras = 0;

    public static void main(String[] args) {
        JFileChooser selector = new JFileChooser();
        boolean terminado = false;

        // Se repite hasta que se lea un archivo bien o el usuario cancele
        while (!terminado) {
            int resultado = selector.showOpenDialog(null);

            if (resultado != JFileChooser.APPROVE_OPTION) {
                System.out.println("No se selecciono ningun archivo. Fin del programa.");
                terminado = true;
            } else {
                File archivo = selector.getSelectedFile();

                if (!archivo.exists() || !archivo.isFile()) {
                    JOptionPane.showMessageDialog(null,
                            "El archivo no existe o no es valido. Elija otro.",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        contar(archivo);
                        terminado = true;
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null,
                                "No se pudo leer el archivo: " + e.getMessage(),
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    // Lee el archivo linea por linea y muestra los resultados en consola
    public static void contar(File archivo) throws IOException {
        int totalLineas = 0;
        int totalCaracteres = 0;
        totalPalabras = 0;
        diferentes = 0;

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                totalLineas++;
                totalCaracteres = totalCaracteres + linea.length();

                // Se agrega un espacio al final para cerrar la ultima palabra de la linea
                String texto = linea + " ";
                String palabra = "";
                for (int i = 0; i < texto.length(); i++) {
                    char c = texto.charAt(i);
                    if (Character.isLetterOrDigit(c)) {
                        palabra = palabra + c; // sigue la palabra
                    } else if (!palabra.equals("")) {
                        registrarPalabra(palabra); // termino la palabra
                        palabra = "";
                    }
                }
            }
        }

        double promedio = 0;
        if (totalLineas > 0) {
            promedio = (double) totalPalabras / totalLineas;
        }

        System.out.println("Archivo: " + archivo.getName());
        System.out.println("Total de lineas: " + totalLineas);
        System.out.println("Total de palabras: " + totalPalabras);
        System.out.println("Total de caracteres: " + totalCaracteres);
        System.out.printf("Promedio de palabras por linea: %.2f%n", promedio);
        mostrarMasFrecuentes();
    }

    // Suma 1 al conteo de la palabra (sin diferenciar mayusculas y minusculas)
    private static void registrarPalabra(String palabra) {
        totalPalabras++;
        String minuscula = palabra.toLowerCase();

        for (int i = 0; i < diferentes; i++) {
            if (palabras[i].equals(minuscula)) {
                conteos[i]++;
                return;
            }
        }
        // Si no estaba, se agrega como palabra nueva
        if (diferentes < palabras.length) {
            palabras[diferentes] = minuscula;
            conteos[diferentes] = 1;
            diferentes++;
        }
    }

    // Ordena de mayor a menor conteo y muestra las 5 primeras
    private static void mostrarMasFrecuentes() {
        // Ordenamiento burbuja (se cambian los dos arreglos a la vez)
        for (int i = 0; i < diferentes - 1; i++) {
            for (int j = 0; j < diferentes - 1 - i; j++) {
                if (conteos[j] < conteos[j + 1]) {
                    int tempConteo = conteos[j];
                    conteos[j] = conteos[j + 1];
                    conteos[j + 1] = tempConteo;

                    String tempPalabra = palabras[j];
                    palabras[j] = palabras[j + 1];
                    palabras[j + 1] = tempPalabra;
                }
            }
        }

        System.out.println("Palabras mas frecuentes:");
        for (int i = 0; i < 5 && i < diferentes; i++) {
            System.out.println("  " + palabras[i] + ": " + conteos[i]);
        }
    }
}
