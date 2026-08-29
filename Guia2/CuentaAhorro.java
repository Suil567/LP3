class CuentaAhorro extends Cuenta {


    @Override
    public void retirar(double importe) {
        super.retirar(importe);
        // Actualizar el saldo mínimo
    }


    @Override
    public void consultar() {
        // Calcular y agregar intereses
        // Reiniciar el saldo mínimo
    }
}
