class InvalidSubscriptException extends RuntimeException {
    InvalidSubscriptException(String mensaje) {
        super(mensaje);
    }
}

public class Actividad1 {

    // Imprime TODO el arreglo
    public static <E> void imprimirArreglo(E[] arreglo) {
        for (E x : arreglo) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    // Imprime solo de un índice a otro y devuelve cuántos imprimió
    public static <E> int imprimirArreglo(E[] arreglo, int inferior, int superior) {

        if (inferior < 0 || inferior >= arreglo.length) {
            throw new InvalidSubscriptException("inferior fuera de rango");
        }

        if (superior < 0 || superior >= arreglo.length) {
            throw new InvalidSubscriptException("superior fuera de rango");
        }

        if (superior <= inferior) {
            throw new InvalidSubscriptException("superior debe ser mayor que inferior");
        }

        int contador = 0;
        for (int i = inferior; i <= superior; i++) {
            System.out.print(arreglo[i] + " ");
            contador = contador + 1;
        }
        System.out.println();
        return contador;
    }

    public static void main(String[] args) {
        Integer[] arregloInteger = {1, 2, 3, 4, 5, 6};
        Double[] arregloDouble = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        Character[] arregloCharacter = {'H', 'O', 'L', 'A'};

        System.out.println("--- Version 1: todo el arreglo ---");
        imprimirArreglo(arregloInteger);
        imprimirArreglo(arregloDouble);
        imprimirArreglo(arregloCharacter);

        System.out.println("--- Version 2: solo una parte ---");
        int c1 = imprimirArreglo(arregloInteger, 1, 3);
        System.out.println("Impresos: " + c1);

        int c2 = imprimirArreglo(arregloDouble, 2, 5);
        System.out.println("Impresos: " + c2);

        int c3 = imprimirArreglo(arregloCharacter, 0, 3);
        System.out.println("Impresos: " + c3);

        System.out.println("--- Probando un error ---");
        try {
            imprimirArreglo(arregloInteger, 2, 10);
        } catch (InvalidSubscriptException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}