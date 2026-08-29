public class CuentaCorriente extends Cuenta {
    private int retiros;


    public CuentaCorriente(int numeroCuenta, double saldoInicial) {
        super(numeroCuenta, saldoInicial);
        retiros = 0;
    }
    public void retirar(double importe) {
        retiros++;


        if (retiros > 3) {
            importe += 3.0;
        }


        super.retirar(importe);
    }
    public void consultar() {
        retiros = 0;
    }
}
