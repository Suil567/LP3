import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

class VocalException extends Exception {
    public VocalException(String mensaje) {
        super(mensaje);
    }
}

class NumeroException extends Exception {
    public NumeroException(String mensaje) {
        super(mensaje);
    }
}

class BlancoException extends Exception {
    public BlancoException(String mensaje) {
        super(mensaje);
    }
}

class SalidaException extends Exception {
    public SalidaException(String mensaje) {
        super(mensaje);
    }
}

class LeerEntrada {
    private Reader stream;

    public LeerEntrada(InputStream fuente) {
        stream = new InputStreamReader(fuente);
    }

    public char getChar() throws IOException {
        return (char) this.stream.read();
    }
}

class Procesador {
    private LeerEntrada lector;

    public Procesador(LeerEntrada lector) {
        this.lector = lector;
    }

    public void procesar() throws IOException, VocalException, NumeroException, BlancoException, SalidaException {
        char c = lector.getChar();
        if (c == 's' || c == 'S') {
            throw new SalidaException("Caracter de salida detectado");
        } else if ("aeiouAEIOU".indexOf(c) >= 0) {
            throw new VocalException("Se leyo una vocal: " + c);
        } else if (Character.isDigit(c)) {
            throw new NumeroException("Se leyo un numero: " + c);
        } else if (c == ' ') {
            throw new BlancoException("Se leyo un espacio en blanco");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LeerEntrada lector = new LeerEntrada(System.in);
        Procesador procesador = new Procesador(lector);
        boolean salir = false;
        while (!salir) {
            try {
                procesador.procesar();
            } catch (VocalException e) {
                System.out.println(e.getMessage());
            } catch (NumeroException e) {
                System.out.println(e.getMessage());
            } catch (BlancoException e) {
                System.out.println(e.getMessage());
            } catch (SalidaException e) {
                System.out.println(e.getMessage());
                salir = true;
            } catch (IOException e) {
                System.out.println("Error de lectura");
                salir = true;
            }
        }
    }
}