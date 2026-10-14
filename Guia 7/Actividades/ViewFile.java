/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// Ventana que muestra un texto dentro de un JTextArea. 
public class ViewFile extends JFrame {

    private final JTextArea areaTexto;

    public ViewFile(String s) {
        super("Mostrando el contenido de un archivo");
        areaTexto = new JTextArea(s, 5, 40);
        areaTexto.setEditable(false);     // solo se visualiza
        areaTexto.setCaretPosition(0);    // empieza mostrando el inicio del archivo
        add(new JScrollPane(areaTexto));  // barras de desplazamiento
    }
}