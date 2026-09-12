class Numero {
    private double valor;

    public void setValor(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("El valor no puede ser negativo");
        }
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}

public class Ejercicio3 {
    public static void main(String[] args) {
        Numero numero = new Numero();
        try {
            numero.setValor(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}