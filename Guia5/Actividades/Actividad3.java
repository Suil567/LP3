public class Actividad3 {

    public static <T> boolean esIgualA(T a, T b) {
        return a.equals(b);
    }

    public static void main(String[] args) {
        System.out.println("5 y 5: " + esIgualA(5, 5));                   // true
        System.out.println("5 y 8: " + esIgualA(5, 8));                   // false
        System.out.println("3.5 y 3.5: " + esIgualA(3.5, 3.5));           // true

        Integer x = 100;
        Integer y = 100;
        System.out.println("Integer: " + esIgualA(x, y));                 // true

        System.out.println("hola y hola: " + esIgualA("hola", "hola"));   // true
        System.out.println("hola y adios: " + esIgualA("hola", "adios")); // false

        Object o1 = new Object();
        Object o2 = new Object();
        System.out.println("o1 y o1: " + esIgualA(o1, o1));               // true
        System.out.println("o1 y o2: " + esIgualA(o1, o2));               // false

        // ESTA LINEA DA ERROR (NullPointerException):
        System.out.println("null y null: " + esIgualA(null, null));
    }
}