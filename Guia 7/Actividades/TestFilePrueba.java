import java.awt.GraphicsEnvironment;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class TestFilePrueba {

    private static final String RUTA_POR_DEFECTO = "src/archivos/TestFile.java";

    public static void main(String[] args) {
        String ruta = (args.length > 0) ? args[0] : RUTA_POR_DEFECTO;

        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Este programa necesita un entorno gráfico (ventanas Swing).");
            System.out.println("Ejecútelo en Visual Studio Code o en otro equipo con pantalla.");
            return;
        }

        // try-with-resources: el FileInputStream se cierra solo
        try (FileInputStream file = new FileInputStream(ruta)) {
            byte[] b = file.readAllBytes();                       // lee TODO el archivo
            String s = new String(b, StandardCharsets.UTF_8);

            SwingUtilities.invokeLater(() -> {
                ViewFile view = new ViewFile(s);
                view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                view.setSize(700, 500);
                view.setLocationRelativeTo(null);
                view.setVisible(true);
            });
        } catch (FileNotFoundException e) {
            mostrarError("No se encontró el archivo: " + ruta
                    + "\nCree el archivo o indique otra ruta.\nDetalle: " + e.getMessage());
        } catch (IOException e) {
            mostrarError("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static void mostrarError(String mensaje) {
        System.out.println(mensaje);
        JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}