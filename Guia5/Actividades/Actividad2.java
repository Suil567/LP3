class ExcepcionPilaLlena extends RuntimeException {
    ExcepcionPilaLlena(String mensaje) {
        super(mensaje);
    }
}

class ExcepcionPilaVacia extends RuntimeException {
    ExcepcionPilaVacia(String mensaje) {
        super(mensaje);
    }
}

class Pila<E> {
    private int tamanio;
    private int superior;
    private E[] elementos;

    public Pila() {
        this(10);
    }

    public Pila(int s) {
        if (s > 0) {
            tamanio = s;
        } else {
            tamanio = 10;
        }
        superior = -1;
        elementos = (E[]) new Object[tamanio];
    }

    public void push(E valor) {
        if (superior == tamanio - 1) {
            throw new ExcepcionPilaLlena("La Pila esta llena, no se puede meter " + valor);
        }
        superior = superior + 1;
        elementos[superior] = valor;
    }

    public E pop() {
        if (superior == -1) {
            throw new ExcepcionPilaVacia("Pila vacia, no se puede sacar");
        }
        E elemento = elementos[superior];
        superior = superior - 1;
        return elemento;
    }

    // Busca desde arriba hacia abajo, sin sacar nada
    public boolean contains(E elemento) {
        for (int i = superior; i >= 0; i--) {
            if (elementos[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }
}

public class Actividad2 {
    public static void main(String[] args) {
        Pila<Integer> pila = new Pila<>(5);
        pila.push(10);
        pila.push(20);
        pila.push(30);

        System.out.println("Contiene 20? " + pila.contains(20)); // true
        System.out.println("Contiene 99? " + pila.contains(99)); // false
    }
}