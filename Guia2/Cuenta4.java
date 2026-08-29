public class Cuenta {
    private int numeroCuenta;
    private double saldo;


    public Cuenta(int numeroCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }


    public void depositar(double importe) {
        saldo += importe;
    }


    public void retirar(double importe) {
        saldo -= importe;
    }


    public double getSaldo() {
        return saldo;
    }


    public int getNumeroCuenta() {
        return numeroCuenta;
    }


    public void consultar() {
    }
}
