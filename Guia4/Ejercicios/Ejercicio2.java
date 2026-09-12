class DivisionPorCeroException extends Exception {
    public DivisionPorCeroException(String mensaje) {
        super(mensaje);
    }
}

class Calculadora {
    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) throws DivisionPorCeroException {
        if (b == 0) {
            throw new DivisionPorCeroException("No se puede dividir entre cero");
        }
        return a / b;
    }
}

public class Ejercicio2 {
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        try {
            System.out.println(calc.sumar(5, 3));
            System.out.println(calc.dividir(10, 0));
        } catch (IllegalArgumentException e) {
            System.out.println("Argumento invalido: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Error aritmetico: " + e.getMessage());
        } catch (DivisionPorCeroException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}