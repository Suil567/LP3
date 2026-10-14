package ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// CONTROLADOR: conecta la vista con el modelo y maneja el archivo
public class ControladorEmpleado {

    private VistaEmpleado vista = new VistaEmpleado();
    private Empleado[] lista = new Empleado[100]; // empleados en memoria
    private int tamano = 0;
    private String nombreArchivo = "empleados.dat";

    // Muestra el menu y ejecuta la opcion elegida
    public void iniciar() {
        leerEmpleados();
        vista.mostrarMensaje("Empleados guardados en el archivo:");
        vista.mostrarEmpleados(lista, tamano);

        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerOpcion();

            if (opcion == 1) {
                vista.mostrarEmpleados(lista, tamano);
            } else if (opcion == 2) {
                agregarEmpleado();
            } else if (opcion == 3) {
                buscarEmpleado();
            } else if (opcion == 4) {
                eliminarEmpleado();
            } else if (opcion != 5) {
                vista.mostrarMensaje("Opcion no valida.");
            }
        } while (opcion != 5);

        vista.mostrarMensaje("Programa terminado.");
    }

    // Lee el archivo binario y llena el arreglo
    public void leerEmpleados() {
        tamano = 0;
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            vista.mostrarMensaje("Todavia no existe el archivo, se creara al agregar empleados.");
            return;
        }

        try (FileInputStream fis = new FileInputStream(archivo);
                DataInputStream entrada = new DataInputStream(fis)) {
            while (tamano < lista.length) {
                int numero = entrada.readInt();
                String nombre = entrada.readUTF();
                double sueldo = entrada.readDouble();
                lista[tamano] = new Empleado(numero, nombre, sueldo);
                tamano++;
            }
        } catch (EOFException e) {
            // Se llego al final del archivo: es normal, no es un error
        } catch (IOException e) {
            vista.mostrarMensaje("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Escribe todos los empleados en el archivo (lo crea si no existe)
    private boolean guardarArchivo() {
        try (FileOutputStream fos = new FileOutputStream(nombreArchivo);
                DataOutputStream salida = new DataOutputStream(fos)) {
            for (int i = 0; i < tamano; i++) {
                salida.writeInt(lista[i].getNumero());
                salida.writeUTF(lista[i].getNombre());
                salida.writeDouble(lista[i].getSueldo());
            }
            return true;
        } catch (IOException e) {
            vista.mostrarMensaje("Error al guardar el archivo: " + e.getMessage());
            return false;
        }
    }

    // Devuelve la posicion del empleado con ese numero, o -1 si no existe
    private int posicionDe(int numero) {
        for (int i = 0; i < tamano; i++) {
            if (lista[i].getNumero() == numero) {
                return i;
            }
        }
        return -1;
    }

    public void agregarEmpleado() {
        Empleado nuevo = vista.pedirEmpleado();
        if (nuevo == null) {
            return; // el usuario escribio un dato invalido
        }
        if (posicionDe(nuevo.getNumero()) != -1) {
            vista.mostrarMensaje("Error: ya existe un empleado con ese numero.");
        } else if (tamano == lista.length) {
            vista.mostrarMensaje("Error: no hay espacio para mas empleados.");
        } else {
            lista[tamano] = nuevo;
            tamano++;
            if (guardarArchivo()) {
                vista.mostrarMensaje("Empleado agregado correctamente.");
            }
        }
    }

    public void buscarEmpleado() {
        int numero = vista.pedirNumero();
        if (numero == -1) {
            return;
        }
        int posicion = posicionDe(numero);
        if (posicion == -1) {
            vista.mostrarMensaje("No se encontro un empleado con el numero " + numero);
        } else {
            vista.mostrarEmpleado(lista[posicion]);
        }
    }

    public void eliminarEmpleado() {
        int numero = vista.pedirNumero();
        if (numero == -1) {
            return;
        }
        int posicion = posicionDe(numero);
        if (posicion == -1) {
            vista.mostrarMensaje("No se encontro un empleado con el numero " + numero);
        } else {
            // Se mueven los siguientes una posicion hacia atras
            for (int i = posicion; i < tamano - 1; i++) {
                lista[i] = lista[i + 1];
            }
            lista[tamano - 1] = null;
            tamano--;
            if (guardarArchivo()) {
                vista.mostrarMensaje("Empleado eliminado correctamente.");
            }
        }
    }
}
