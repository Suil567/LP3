class CuentaCorriente extends Cuenta {


    @Override
    public void retirar(double importe) {
        // Actualizar contador de retiros
        super.retirar(importe);
    }


    @Override
    public void consultar() {
        // Reiniciar contador de retiros
    }
}
