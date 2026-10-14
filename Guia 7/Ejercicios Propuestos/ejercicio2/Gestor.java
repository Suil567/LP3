package ejercicio2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

// Gestor del ejercicio 1 + las nuevas funcionalidades
public class Gestor {

    private Personaje[] lista = new Personaje[100];
    private int tamano = 0;
    private String nombreArchivo = "personajes2.txt";

    public Gestor() {
        cargar();
    }

    // ---------------------------------------------------------------
    // PARTE IGUAL AL EJERCICIO 1 (con nivel en el archivo)
    // ---------------------------------------------------------------

    // Lee el archivo: nombre vida ataque defensa alcance nivel
    private void cargar() {
        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) {
            lista[0] = new Personaje("Caballero", 4, 2, 4, 2, 1);
            lista[1] = new Personaje("Guerrero", 2, 4, 2, 4, 1);
            lista[2] = new Personaje("Arquero", 2, 4, 1, 8, 1);
            tamano = 3;
            guardar();
            return;
        }

        try (Scanner sc = new Scanner(archivo)) {
            while (sc.hasNext()) {
                String nombre = sc.next();
                int vida = sc.nextInt();
                int ataque = sc.nextInt();
                int defensa = sc.nextInt();
                int alcance = sc.nextInt();
                int nivel = sc.nextInt();
                lista[tamano] = new Personaje(nombre, vida, ataque, defensa, alcance, nivel);
                tamano++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("El archivo tiene un formato incorrecto.");
        }
    }

    // Guarda todos los personajes en el archivo
    private void guardar() {
        try (PrintWriter salida = new PrintWriter(nombreArchivo)) {
            for (int i = 0; i < tamano; i++) {
                Personaje p = lista[i];
                salida.println(p.getNombre() + " " + p.getVida() + " " + p.getAtaque()
                        + " " + p.getDefensa() + " " + p.getAlcance() + " " + p.getNivel());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo guardar el archivo: " + e.getMessage());
        }
    }

    // Devuelve la posicion del personaje, o -1 si no existe
    private int buscar(String nombre) {
        for (int i = 0; i < tamano; i++) {
            if (lista[i].getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

    private boolean sonValidos(int vida, int ataque, int defensa, int alcance) {
        return vida > 0 && ataque > 0 && defensa > 0 && alcance > 0;
    }

    // Agrega un personaje nuevo (empieza en nivel 1)
    public void agregar(String nombre, int vida, int ataque, int defensa, int alcance) {
        if (!sonValidos(vida, ataque, defensa, alcance)) {
            System.out.println("Error: los valores deben ser mayores que cero.");
        } else if (buscar(nombre) != -1) {
            System.out.println("Error: el personaje ya existe, no se creo.");
        } else if (tamano == lista.length) {
            System.out.println("Error: el gestor esta lleno.");
        } else {
            lista[tamano] = new Personaje(nombre, vida, ataque, defensa, alcance, 1);
            tamano++;
            guardar();
            System.out.println("Personaje agregado correctamente.");
        }
    }

    public void mostrar() {
        if (tamano == 0) {
            System.out.println("No hay personajes.");
        } else {
            for (int i = 0; i < tamano; i++) {
                System.out.println(lista[i]);
            }
        }
    }

    // Cambia los 4 valores de un personaje
    public void modificar(String nombre, int vida, int ataque, int defensa, int alcance) {
        int posicion = buscar(nombre);
        if (posicion == -1) {
            System.out.println("Error: el personaje no existe.");
        } else if (!sonValidos(vida, ataque, defensa, alcance)) {
            System.out.println("Error: los valores deben ser mayores que cero.");
        } else {
            lista[posicion].setVida(vida);
            lista[posicion].setAtaque(ataque);
            lista[posicion].setDefensa(defensa);
            lista[posicion].setAlcance(alcance);
            guardar();
            System.out.println("Personaje modificado correctamente.");
        }
    }

    public void borrar(String nombre) {
        int posicion = buscar(nombre);
        if (posicion == -1) {
            System.out.println("Error: el personaje no existe.");
        } else {
            for (int i = posicion; i < tamano - 1; i++) {
                lista[i] = lista[i + 1];
            }
            lista[tamano - 1] = null;
            tamano--;
            guardar();
            System.out.println("Personaje borrado correctamente.");
        }
    }

    // ---------------------------------------------------------------
    // NUEVAS FUNCIONALIDADES
    // ---------------------------------------------------------------

    // Devuelve el valor de un atributo: 1=vida, 2=ataque, 3=defensa, 4=alcance
    private int valor(Personaje p, int atributo) {
        if (atributo == 1) {
            return p.getVida();
        } else if (atributo == 2) {
            return p.getAtaque();
        } else if (atributo == 3) {
            return p.getDefensa();
        } else {
            return p.getAlcance();
        }
    }

    // 1) Muestra los personajes ordenados de mayor a menor segun un atributo
    public void mostrarOrdenado(int atributo) {
        if (atributo < 1 || atributo > 4) {
            System.out.println("Error: atributo no valido (use 1 a 4).");
            return;
        }
        // Ordenamiento burbuja
        for (int i = 0; i < tamano - 1; i++) {
            for (int j = 0; j < tamano - 1 - i; j++) {
                if (valor(lista[j], atributo) < valor(lista[j + 1], atributo)) {
                    Personaje temporal = lista[j];
                    lista[j] = lista[j + 1];
                    lista[j + 1] = temporal;
                }
            }
        }
        mostrar();
    }

    // 2) Agrega personajes con valores al azar del 1 al 10
    public void cargarAleatorios(int cantidad) {
        int numero = 1;
        for (int i = 0; i < cantidad; i++) {
            if (tamano == lista.length) {
                System.out.println("Error: el gestor esta lleno.");
                break;
            }
            // Busca un nombre que no exista: Aleatorio1, Aleatorio2, ...
            while (buscar("Aleatorio" + numero) != -1) {
                numero++;
            }
            int vida = (int) (Math.random() * 10) + 1;
            int ataque = (int) (Math.random() * 10) + 1;
            int defensa = (int) (Math.random() * 10) + 1;
            int alcance = (int) (Math.random() * 10) + 1;
            lista[tamano] = new Personaje("Aleatorio" + numero, vida, ataque, defensa, alcance, 1);
            tamano++;
        }
        guardar();
        System.out.println("Personajes aleatorios agregados.");
    }

    // 3) Cambia solo un atributo: 1=vida, 2=ataque, 3=defensa, 4=alcance
    public void actualizarAtributo(String nombre, int atributo, int nuevoValor) {
        int posicion = buscar(nombre);
        if (posicion == -1) {
            System.out.println("Error: el personaje no existe.");
        } else if (nuevoValor <= 0) {
            System.out.println("Error: el valor debe ser mayor que cero.");
        } else if (atributo == 1) {
            lista[posicion].setVida(nuevoValor);
            guardar();
            System.out.println("Vida actualizada.");
        } else if (atributo == 2) {
            lista[posicion].setAtaque(nuevoValor);
            guardar();
            System.out.println("Ataque actualizado.");
        } else if (atributo == 3) {
            lista[posicion].setDefensa(nuevoValor);
            guardar();
            System.out.println("Defensa actualizada.");
        } else if (atributo == 4) {
            lista[posicion].setAlcance(nuevoValor);
            guardar();
            System.out.println("Alcance actualizado.");
        } else {
            System.out.println("Error: atributo no valido (use 1 a 4).");
        }
    }

    // 4) Muestra el total de personajes y el promedio de cada atributo
    public void mostrarEstadisticas() {
        if (tamano == 0) {
            System.out.println("No hay personajes.");
            return;
        }
        double sumaVida = 0;
        double sumaAtaque = 0;
        double sumaDefensa = 0;
        double sumaAlcance = 0;
        for (int i = 0; i < tamano; i++) {
            sumaVida = sumaVida + lista[i].getVida();
            sumaAtaque = sumaAtaque + lista[i].getAtaque();
            sumaDefensa = sumaDefensa + lista[i].getDefensa();
            sumaAlcance = sumaAlcance + lista[i].getAlcance();
        }
        System.out.println("Total de personajes: " + tamano);
        System.out.printf("Vida promedio: %.2f%n", sumaVida / tamano);
        System.out.printf("Ataque promedio: %.2f%n", sumaAtaque / tamano);
        System.out.printf("Defensa promedio: %.2f%n", sumaDefensa / tamano);
        System.out.printf("Alcance promedio: %.2f%n", sumaAlcance / tamano);
    }

    // 5) Importa personajes desde otro archivo: nombre vida ataque defensa alcance
    public void importar(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        int agregados = 0;
        try (Scanner sc = new Scanner(archivo)) {
            while (sc.hasNext()) {
                String nombre = sc.next();
                int vida = sc.nextInt();
                int ataque = sc.nextInt();
                int defensa = sc.nextInt();
                int alcance = sc.nextInt();
                // Solo se agrega si es valido, no existe y hay espacio
                if (sonValidos(vida, ataque, defensa, alcance) && buscar(nombre) == -1
                        && tamano < lista.length) {
                    lista[tamano] = new Personaje(nombre, vida, ataque, defensa, alcance, 1);
                    tamano++;
                    agregados++;
                }
            }
            guardar();
            System.out.println("Personajes importados: " + agregados);
        } catch (FileNotFoundException e) {
            System.out.println("Error: no se encontro el archivo " + rutaArchivo);
        } catch (Exception e) {
            System.out.println("Error: el archivo tiene un formato incorrecto.");
        }
    }

    // 6) Sube un nivel al personaje y mejora sus 4 atributos en +1
    public void subirNivel(String nombre) {
        int posicion = buscar(nombre);
        if (posicion == -1) {
            System.out.println("Error: el personaje no existe.");
        } else {
            Personaje p = lista[posicion];
            p.setNivel(p.getNivel() + 1);
            p.setVida(p.getVida() + 1);
            p.setAtaque(p.getAtaque() + 1);
            p.setDefensa(p.getDefensa() + 1);
            p.setAlcance(p.getAlcance() + 1);
            guardar();
            System.out.println(p.getNombre() + " subio al nivel " + p.getNivel());
        }
    }
}
