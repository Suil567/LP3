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

    // Actividad 2 (se queda en la clase)
    public boolean contains(E elemento) {
        for (int i = superior; i >= 0; i--) {
            if (elementos[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    // Actividad 4: ¿esta pila es igual a la otra?
    public boolean esIgual(Pila<E> otraPila) {
        if (this.superior != otraPila.superior) {
            return false;
        }

        for (int i = 0; i <= superior; i++) {
            if (!this.elementos[i].equals(otraPila.elementos[i])) {
                return false;
            }
        }

        return true;
    }
}

public class Actividad4 {
    public static void main(String[] args) {
        Pila<Integer> a = new Pila<>(5);
        a.push(1);
        a.push(2);
        a.push(3);

        Pila<Integer> b = new Pila<>(5);
        b.push(1);
        b.push(2);
        b.push(3);

        Pila<Integer> c = new Pila<>(5);
        c.push(3);
        c.push(2);
        c.push(1);   // mismos numeros pero en otro orden

        Pila<Integer> d = new Pila<>(5);
        d.push(1);
        d.push(2);   // solo 2 elementos

        System.out.println("a y b iguales? " + a.esIgual(b)); // true
        System.out.println("a y c iguales? " + a.esIgual(c)); // false
        System.out.println("a y d iguales? " + a.esIgual(d)); // false
    }
}