/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
class SaldoInsuficienteException extends Exception {

    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}

class CuentaBancaria {

    private String numeroCuenta;
    private String titular;
    private double saldo;

    public CuentaBancaria(String numeroCuenta, String titular, double saldo) {

        if (saldo < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }

        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    public void depositar(double monto) {

        if (monto <= 0) {
            throw new IllegalArgumentException("El deposito debe ser mayor que cero");
        }

        saldo = saldo + monto;

        System.out.println("Deposito realizado correctamente");
        System.out.println("Monto depositado: S/ " + monto);
    }

    public void retirar(double monto) throws SaldoInsuficienteException {

        if (monto <= 0) {
            throw new IllegalArgumentException("El retiro debe ser mayor que cero");
        }

        if (monto > saldo) {
            throw new SaldoInsuficienteException(
                "Saldo insuficiente para realizar el retiro"
            );
        }

        saldo = saldo - monto;

        System.out.println("Retiro realizado correctamente");
        System.out.println("Monto retirado: S/ " + monto);
    }

    public void mostrarSaldo() {
        System.out.println("Numero de cuenta: " + numeroCuenta);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo actual: S/ " + saldo);
    }
}

public class Sistemacuentabancaria {

    public static void main(String[] args) {

        System.out.println("SISTEMA DE CUENTA BANCARIA ");

        try {

            System.out.println("Creando cuenta bancaria");

            CuentaBancaria cuenta = new CuentaBancaria(
                "001",
                "Fabrizio",
                500
            );

            System.out.println("Cuenta creada correctamente");
            System.out.println();

            cuenta.mostrarSaldo();
            System.out.println();

            System.out.println("Realizando deposito de S/ 200");
            cuenta.depositar(200);
            System.out.println();

            cuenta.mostrarSaldo();
            System.out.println();

            System.out.println("Realizando retiro de S/ 100");
            cuenta.retirar(100);
            System.out.println();

            cuenta.mostrarSaldo();
            System.out.println();

            System.out.println("Probando retiro mayor al saldo");

            cuenta.retirar(1000);

        } catch (SaldoInsuficienteException e) {

            System.out.println("ERROR: " + e.getMessage());

        } catch (IllegalArgumentException e) {

            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println();
        System.out.println("PRUEBA DE SALDO INICIAL NEGATIVO ");

        try {

            CuentaBancaria cuenta2 = new CuentaBancaria(
                "002",
                "Luis",
                -100
            );

        } catch (IllegalArgumentException e) {

            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println();
        System.out.println("PRUEBA DE DEPOSITO INVALIDO");

        try {

            CuentaBancaria cuenta3 = new CuentaBancaria(
                "003",
                "Carlos",
                300
            );
            cuenta3.depositar(-50);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
