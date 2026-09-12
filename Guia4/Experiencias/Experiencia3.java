/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
class LimiteCreditoExcedidoException extends Exception {
    public LimiteCreditoExcedidoException(String mensaje) {
        super(mensaje);
    }
}

class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}

class CuentaBancaria {

    protected String numeroCuenta;
    protected String titular;
    protected double saldo;

    public CuentaBancaria(String numeroCuenta, String titular, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    public void retirar(double monto)
            throws SaldoInsuficienteException,
                   LimiteCreditoExcedidoException {

        if (monto > saldo) {
            throw new SaldoInsuficienteException(
                "Saldo insuficiente para realizar el retiro"
            );
        }

        saldo = saldo - monto;

        System.out.println("Retiro realizado: S/ " + monto);
    }

    public void mostrarSaldo() {
        System.out.println("Cuenta: " + numeroCuenta);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: S/ " + saldo);
    }
}

class CuentaCredito extends CuentaBancaria {

    private double limiteCredito;

    public CuentaCredito(String numeroCuenta, String titular,
                         double saldo, double limiteCredito) {

        super(numeroCuenta, titular, saldo);
        this.limiteCredito = limiteCredito;
    }

    @Override
    public void retirar(double monto)
            throws LimiteCreditoExcedidoException {

        double dineroDisponible = saldo + limiteCredito;

        if (monto > dineroDisponible) {
            throw new LimiteCreditoExcedidoException(
                "El retiro supera el limite de credito disponible"
            );
        }

        saldo = saldo - monto;

        System.out.println("Retiro realizado correctamente");
        System.out.println("Monto retirado: S/ " + monto);
    }

    public void transferir(CuentaBancaria destino, double monto)
            throws LimiteCreditoExcedidoException {

        double dineroDisponible = saldo + limiteCredito;

        if (monto > dineroDisponible) {
            throw new LimiteCreditoExcedidoException(
                "La transferencia supera el limite de credito"
            );
        }

        saldo = saldo - monto;
        destino.saldo = destino.saldo + monto;

        System.out.println("Transferencia realizada correctamente");
        System.out.println("Monto transferido: S/ " + monto);
    }

    public void mostrarCredito() {
        System.out.println("Limite de credito: S/ " + limiteCredito);
        System.out.println("Saldo disponible considerando credito: S/ "
                           + (saldo + limiteCredito));
    }
}

public class Experiencia3 {

    public static void main(String[] args) {

        System.out.println("CUENTA DE CREDITO");

        CuentaCredito cuenta =
            new CuentaCredito("001", "Fabrizio", 500, 1000);

        cuenta.mostrarSaldo();
        cuenta.mostrarCredito();

        System.out.println();
        System.out.println("RETIRO DENTRO DEL LIMITE");

        try {
            cuenta.retirar(1200);
        } catch (LimiteCreditoExcedidoException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println();
        cuenta.mostrarSaldo();
        cuenta.mostrarCredito();

        System.out.println();
        System.out.println("RETIRO QUE SUPERA EL LIMITE");

        try {
            cuenta.retirar(500);
        } catch (LimiteCreditoExcedidoException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println();
        System.out.println("TRANSFERENCIA");

        CuentaBancaria destino =
            new CuentaBancaria("002", "Luis", 300);

        try {
            cuenta.transferir(destino, 200);
        } catch (LimiteCreditoExcedidoException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println();
        System.out.println("SALDOS FINALES");

        cuenta.mostrarSaldo();

        System.out.println();

        destino.mostrarSaldo();
    }
}