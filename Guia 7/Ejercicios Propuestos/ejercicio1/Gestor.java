package ejercicio1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

// Clase que administra los personajes y los guarda en un archivo de texto
public class Gestor {

    private Personaje[] lista = new Personaje[100]; // arreglo de personajes
    private int tamano = 0;                          // cuantos personajes hay
    private String nombreArchivo = "personajes1.txt";

    // Al crear el gestor se cargan los personajes del archivo
    public Gestor() {
        cargar();
    }

    // Lee el archivo de texto y llena el arreglo
    private void cargar() {
        File archivo = new File(nombreArchivo);

        // Si el archivo no existe, se crean los 3 personajes de la guia
        if (!archivo.exists()) {
            lista[0] = new Personaje("Caballero", 4, 2, 4, 2);
            lista[1] = new Personaje("Guerrero", 2, 4, 2, 4);
            lista[2] = new Personaje("Arquero", 2, 4, 1, 8);
            tamano = 3;
            guardar();
            return;
        }

        // Si existe, se lee con Scanner: nombre vida ataque defensa alcance
        try (Scanner sc = new Scanner(archivo)) {
            while (sc.hasNext()) {
                String nombre = sc.next();
                int vida = sc.nextInt();
                int ataque = sc.nextInt();
                int defensa = sc.nextInt();
                int alcance = sc.nextInt();
                lista[tamano] = new Personaje(nombre, vida, ataque, defensa, alcance);
                tamano++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("El archivo tiene un formato incorrecto.");
        }
    }

    // Escribe todos los personajes en el archivo (se llama despues de cada cambio)
    private void guardar() {
        try (PrintWriter salida = new PrintWriter(nombreArchivo)) {
            for (int i = 0; i < tamano; i++) {
                Personaje p = lista[i];
                salida.println(p.getNombre() + " " + p.getVida() + " " + p.getAtaque()
                        + " " + p.getDefensa() + " " + p.getAlcance());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo guardar el archivo: " + e.getMessage());
        }
    }

    // Devuelve la posicion del personaje con ese nombre, o -1 si no existe
    private int buscar(String nombre) {
        for (int i = 0; i < tamano; i++) {
            if (lista[i].getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

    // Los valores deben ser numeros enteros mayores que cero
    private boolean sonValidos(int vida, int ataque, int defensa, int alcance) {
        return vida > 0 && ataque > 0 && defensa > 0 && alcance > 0;
    }

    // Anade un personaje nuevo
    public void agregar(String nombre, int vida, int ataque, int defensa, int alcance) {
        if (!sonValidos(vida, ataque, defensa, alcance)) {
            System.out.println("Error: los valores deben ser mayores que cero.");
        } else if (buscar(nombre) != -1) {
            System.out.println("Error: el personaje ya existe, no se creo.");
        } else if (tamano == lista.length) {
            System.out.println("Error: el gestor esta lleno.");
        } else {
            lista[tamano] = new Personaje(nombre, vida, ataque, defensa, alcance);
            tamano++;
            guardar();
            System.out.println("Personaje agregado correctamente.");
        }
    }

    // Muestra todos los personajes
    public void mostrar() {
        if (tamano == 0) {
            System.out.println("No hay personajes.");
        } else {
            for (int i = 0; i < tamano; i++) {
                System.out.println(lista[i]);
            }
        }
    }

    // Cambia los valores de un personaje que ya existe
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

    // Borra un personaje usando su nombre
    public void borrar(String nombre) {
        int posicion = buscar(nombre);
        if (posicion == -1) {
            System.out.println("Error: el personaje no existe.");
        } else {
            // Se mueven los siguientes una posicion hacia atras
            for (int i = posicion; i < tamano - 1; i++) {
                lista[i] = lista[i + 1];
            }
            lista[tamano - 1] = null;
            tamano--;
            guardar();
            System.out.println("Personaje borrado correctamente.");
        }
    }
}
