/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

class HistorialVacioException extends Exception {
    public HistorialVacioException(String mensaje) {
        super(mensaje);
    }
}

class CuentaBancaria {

    private String numeroCuenta;
    private String titular;
    private double saldo;
    private boolean tieneTransacciones;

    public CuentaBancaria(String numeroCuenta, String titular, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
        this.tieneTransacciones = false;
    }

    public void depositar(double monto) {
        saldo = saldo + monto;
        tieneTransacciones = true;

        System.out.println("Deposito realizado: S/ " + monto);
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean tieneTransacciones() {
        return tieneTransacciones;
    }
}

class ReporteTransacciones {

    public void generarReporte(CuentaBancaria cuenta, String nombreArchivo)
            throws HistorialVacioException, IOException {

        if (!cuenta.tieneTransacciones()) {
            throw new HistorialVacioException(
                "La cuenta no tiene transacciones"
            );
        }

        try (FileWriter archivo = new FileWriter(nombreArchivo)) {

            archivo.write("Numero de cuenta: "
                    + cuenta.getNumeroCuenta());
            archivo.write(System.lineSeparator());

            archivo.write("Titular: "
                    + cuenta.getTitular());
            archivo.write(System.lineSeparator());

            archivo.write("Saldo: S/ "
                    + cuenta.getSaldo());
            archivo.write(System.lineSeparator());

            System.out.println("Reporte generado correctamente");
        }
    }

    public void leerReporte(String nombreArchivo)
            throws FileNotFoundException {

        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {

            System.out.println("Contenido del reporte:");

            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
    }
}

public class Experiencia4 {

    public static void main(String[] args) {

        CuentaBancaria cuenta1 =
            new CuentaBancaria("001", "Fabrizio", 500);

        CuentaBancaria cuenta2 =
            new CuentaBancaria("002", "Luis", 1000);

        ReporteTransacciones reporte =
            new ReporteTransacciones();

        System.out.println("GENERACION DE REPORTE");

        try {

            reporte.generarReporte(cuenta1, "reporte.txt");

        } catch (HistorialVacioException e) {

            System.out.println("ERROR: " + e.getMessage());

        } catch (IOException e) {

            System.out.println("ERROR AL ESCRIBIR: "
                    + e.getMessage());
        }

        System.out.println();
        System.out.println("REALIZANDO TRANSACCION");

        cuenta2.depositar(300);

        System.out.println();
        System.out.println("GENERANDO REPORTE DE LA CUENTA 2");

        try {

            reporte.generarReporte(cuenta2, "reporte.txt");

        } catch (HistorialVacioException e) {

            System.out.println("ERROR: " + e.getMessage());

        } catch (IOException e) {

            System.out.println("ERROR AL ESCRIBIR: "
                    + e.getMessage());
        }

        System.out.println();
        System.out.println("LEYENDO REPORTE");

        try {

            reporte.leerReporte("reporte.txt");

        } catch (FileNotFoundException e) {

            System.out.println(
                "ERROR: El archivo no existe"
            );
        }

        System.out.println();
        System.out.println("PRUEBA DE ARCHIVO INEXISTENTE");

        try {

            reporte.leerReporte("archivo_inexistente.txt");

        } catch (FileNotFoundException e) {

            System.out.println(
                "ERROR: No se encontro el archivo"
            );
        }
    }
}
