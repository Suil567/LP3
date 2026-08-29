public class CuentaAhorro extends Cuenta {
    private double tasaInteres;
    private double saldoMinimo;


    public CuentaAhorro(int numeroCuenta, double saldoInicial, double tasaInteres) {
        super(numeroCuenta, saldoInicial);
        this.tasaInteres = tasaInteres;
        this.saldoMinimo = saldoInicial;
    }


    public void retirar(double importe) {
        super.retirar(importe);


        if (getSaldo() < saldoMinimo) {
            saldoMinimo = getSaldo();
        }
    }


    public void consultar() {
        double intereses = saldoMinimo * tasaInteres;
        depositar(intereses);
        saldoMinimo = getSaldo();
    }
}


