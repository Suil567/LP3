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

class CuentaNoEncontradaException extends Exception {
    public CuentaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}

class SaldoNoCeroException extends Exception {
    public SaldoNoCeroException(String mensaje) {
        super(mensaje);
    }
}

class CuentaBancaria {

    private String numeroCuenta;
    private String titular;
    private double saldo;
    private boolean activa;

    public CuentaBancaria(String numeroCuenta, String titular, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
        this.activa = true;
    }

    public void depositar(double monto) {
        saldo = saldo + monto;
        System.out.println("Deposito realizado: S/ " + monto);
    }

    public void retirar(double monto) throws SaldoInsuficienteException {

        if (monto > saldo) {
            throw new SaldoInsuficienteException(
                "No hay suficiente saldo para realizar el retiro"
            );
        }

        saldo = saldo - monto;
        System.out.println("Retiro realizado: S/ " + monto);
    }

    public void transferir(CuentaBancaria destino, double monto)
            throws SaldoInsuficienteException, CuentaNoEncontradaException {

        if (destino == null) {
            throw new CuentaNoEncontradaException(
                "La cuenta destino no existe"
            );
        }

        if (monto > saldo) {
            throw new SaldoInsuficienteException(
                "Saldo insuficiente para realizar la transferencia"
            );
        }

        saldo = saldo - monto;
        destino.saldo = destino.saldo + monto;

        System.out.println("Transferencia realizada correctamente");
        System.out.println("Monto transferido: S/ " + monto);
    }

    public void cerrarCuenta() throws SaldoNoCeroException {

        if (saldo != 0) {
            throw new SaldoNoCeroException(
                "No se puede cerrar la cuenta porque tiene saldo"
            );
        }

        activa = false;
        System.out.println("La cuenta fue cerrada correctamente");
    }

    public void mostrarDatos() {
        System.out.println("Cuenta: " + numeroCuenta);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: S/ " + saldo);
        System.out.println("Estado: " + (activa ? "Activa" : "Cerrada"));
    }
}

public class Experiencia2 {

    public static void main(String[] args) {

        System.out.println("SISTEMA BANCARIO");

        CuentaBancaria cuenta1 =
            new CuentaBancaria("001", "Fabrizio", 1000);

        CuentaBancaria cuenta2 =
            new CuentaBancaria("002", "Luis", 500);

        System.out.println();
        System.out.println("DATOS DE LAS CUENTAS");

        cuenta1.mostrarDatos();
        System.out.println();

        cuenta2.mostrarDatos();

        System.out.println();
        System.out.println("TRANSFERENCIA");

        try {
            cuenta1.transferir(cuenta2, 300);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println();
        cuenta1.mostrarDatos();

        System.out.println();
        cuenta2.mostrarDatos();

        System.out.println();
        System.out.println("TRANSFERENCIA CON SALDO INSUFICIENTE");

        try {
            cuenta1.transferir(cuenta2, 2000);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println();
        System.out.println("CUENTA DESTINO INEXISTENTE");

        try {
            cuenta1.transferir(null, 100);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println();
        System.out.println("CIERRE DE CUENTA ");

        try {
            cuenta1.cerrarCuenta();
        } catch (SaldoNoCeroException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println();
        System.out.println("Retirando todo el saldo de la cuenta 1");

        try {
            cuenta1.retirar(700);
            cuenta1.cerrarCuenta();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println();
        System.out.println("ESTADO FINAL ");

        cuenta1.mostrarDatos();
    }
}
