package actividad;

import java.sql.*;
import java.util.Scanner;

public class PrincipalPropuestos {

    static final String CLAVE = "1234";
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        Connection con = DriverManager.getConnection("jdbc:sqlite:empresa_propuestos.db");
        con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS empleados (id INTEGER PRIMARY KEY, nombre TEXT, edad INTEGER, sueldo REAL)");
        con.setAutoCommit(false);

        String opcion = "";
        while (!opcion.equals("0")) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Ingresar");
            System.out.println("2. Borrar");
            System.out.println("3. Actualizar");
            System.out.println("4. Mostrar");
            System.out.println("5. Consultar arreglo de objetos");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = teclado.nextLine().trim();

            try {
                if (opcion.equals("1")) ingresar(con);
                else if (opcion.equals("2")) borrar(con);
                else if (opcion.equals("3")) actualizar(con);
                else if (opcion.equals("4")) mostrar(con);
                else if (opcion.equals("5")) consultar(con);
                else if (!opcion.equals("0")) System.out.println("Opcion no valida.");
            } catch (Exception e) {
                con.rollback();
                System.out.println("Error: " + e.getMessage());
            }
        }
        con.close();
    }

    // Pide la clave: si es correcta hace COMMIT, si no hace ROLLBACK
    static void confirmar(Connection con) throws Exception {
        System.out.print("Ingrese la clave para confirmar: ");
        String clave = teclado.nextLine();
        if (clave.equals(CLAVE)) {
            con.commit();
            System.out.println("Cambios confirmados (COMMIT).");
        } else {
            con.rollback();
            System.out.println("Clave incorrecta. Cambios revertidos (ROLLBACK).");
        }
    }

    static void ingresar(Connection con) throws Exception {
        System.out.print("ID: ");
        int id = Integer.parseInt(teclado.nextLine().trim());
        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(teclado.nextLine().trim());
        System.out.print("Sueldo: ");
        double sueldo = Double.parseDouble(teclado.nextLine().trim());

        PreparedStatement ps = con.prepareStatement("INSERT INTO empleados VALUES (?, ?, ?, ?)");
        ps.setInt(1, id);
        ps.setString(2, nombre);
        ps.setInt(3, edad);
        ps.setDouble(4, sueldo);
        ps.executeUpdate();

        confirmar(con);
    }

    static void borrar(Connection con) throws Exception {
        System.out.print("ID a borrar: ");
        int id = Integer.parseInt(teclado.nextLine().trim());

        PreparedStatement ps = con.prepareStatement("DELETE FROM empleados WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();

        confirmar(con);
    }

    static void actualizar(Connection con) throws Exception {
        System.out.print("ID a actualizar: ");
        int id = Integer.parseInt(teclado.nextLine().trim());
        System.out.print("Nuevo nombre: ");
        String nombre = teclado.nextLine();
        System.out.print("Nueva edad: ");
        int edad = Integer.parseInt(teclado.nextLine().trim());
        System.out.print("Nuevo sueldo: ");
        double sueldo = Double.parseDouble(teclado.nextLine().trim());

        PreparedStatement ps = con.prepareStatement("UPDATE empleados SET nombre = ?, edad = ?, sueldo = ? WHERE id = ?");
        ps.setString(1, nombre);
        ps.setInt(2, edad);
        ps.setDouble(3, sueldo);
        ps.setInt(4, id);
        ps.executeUpdate();

        confirmar(con);
    }

    static void mostrar(Connection con) throws Exception {
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM empleados");
        System.out.println("\nID | Nombre | Edad | Sueldo");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " | " + rs.getString("nombre") + " | " + rs.getInt("edad") + " | " + rs.getDouble("sueldo"));
        }
    }

    // Pide los datos al usuario y llama al metodo del gestor
    static void consultar(Connection con) throws Exception {
        Gestor gestor = new Gestor();
        gestor.cargar(con);

        System.out.print("Campos a mostrar (id,nombre,edad,sueldo o *): ");
        String texto = teclado.nextLine().trim();
        if (texto.equals("*")) texto = "id,nombre,edad,sueldo";
        String[] campos = texto.split(",");

        System.out.print("Campo para la condicion (Enter = ninguno): ");
        String campoCond = teclado.nextLine().trim();
        String operador = "";
        String valor = "";
        if (!campoCond.equals("")) {
            System.out.print("Operador (=, !=, >, >=, <, <=): ");
            operador = teclado.nextLine().trim();
            System.out.print("Valor: ");
            valor = teclado.nextLine().trim();
        }

        System.out.print("Campo para ordenar (Enter = ninguno): ");
        String campoOrden = teclado.nextLine().trim();
        boolean ascendente = true;
        if (!campoOrden.equals("")) {
            System.out.print("Orden (ASC o DESC): ");
            ascendente = teclado.nextLine().trim().equalsIgnoreCase("ASC");
        }

        System.out.print("Limite de registros (0 = todos): ");
        int limite = Integer.parseInt(teclado.nextLine().trim());

        gestor.consultar(campos, campoCond, operador, valor, campoOrden, ascendente, limite);
    }
}

class Empleado {
    int id;
    String nombre;
    int edad;
    double sueldo;

    Empleado(int id, String nombre, int edad, double sueldo) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.sueldo = sueldo;
    }
}

// Clase gestora: guarda los registros en un arreglo y los consulta desde Java
class Gestor {

    Empleado[] lista;

    // Lee la tabla y llena el arreglo
    void cargar(Connection con) throws Exception {
        ResultSet rs = con.createStatement().executeQuery("SELECT COUNT(*) FROM empleados");
        rs.next();
        lista = new Empleado[rs.getInt(1)];

        rs = con.createStatement().executeQuery("SELECT * FROM empleados");
        int i = 0;
        while (rs.next()) {
            lista[i] = new Empleado(rs.getInt("id"), rs.getString("nombre"), rs.getInt("edad"), rs.getDouble("sueldo"));
            i++;
        }
        System.out.println("Registros cargados en el arreglo: " + lista.length);
    }

    // a) campos a mostrar  b) condicion  c) orden asc/desc  d) limite
    void consultar(String[] campos, String campoCond, String operador, String valor,
                   String campoOrden, boolean ascendente, int limite) throws Exception {

        for (int i = 0; i < campos.length; i++) {
            campos[i] = campos[i].trim();
            validar(campos[i]);
        }
        if (!campoCond.equals("")) validar(campoCond);
        if (!campoOrden.equals("")) validar(campoOrden);

        // 1. Filtrar
        Empleado[] r = new Empleado[lista.length];
        int n = 0;
        for (Empleado e : lista) {
            boolean cumple = true;
            if (!campoCond.equals("")) {
                int c = comparar(e, campoCond, valor);
                cumple = evaluar(c, operador);
            }
            if (cumple) {
                r[n] = e;
                n++;
            }
        }

        // 2. Ordenar (metodo burbuja)
        if (!campoOrden.equals("")) {
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - 1 - i; j++) {
                    int c = compararEmpleados(r[j], r[j + 1], campoOrden);
                    if ((ascendente && c > 0) || (!ascendente && c < 0)) {
                        Empleado aux = r[j];
                        r[j] = r[j + 1];
                        r[j + 1] = aux;
                    }
                }
            }
        }

        // 3. Limite
        if (limite > 0 && limite < n) n = limite;

        // 4. Mostrar solo los campos pedidos
        System.out.println("\nRESULTADO");
        System.out.println(String.join(" | ", campos));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < campos.length; j++) {
                if (j > 0) System.out.print(" | ");
                System.out.print(texto(r[i], campos[j]));
            }
            System.out.println();
        }
        System.out.println("Registros mostrados: " + n);
    }

    void validar(String campo) throws Exception {
        if (!campo.equals("id") && !campo.equals("nombre") && !campo.equals("edad") && !campo.equals("sueldo")) {
            throw new Exception("Campo no valido: " + campo);
        }
    }

    boolean evaluar(int c, String operador) throws Exception {
        if (operador.equals("=")) return c == 0;
        if (operador.equals("!=")) return c != 0;
        if (operador.equals(">")) return c > 0;
        if (operador.equals(">=")) return c >= 0;
        if (operador.equals("<")) return c < 0;
        if (operador.equals("<=")) return c <= 0;
        throw new Exception("Operador no valido: " + operador);
    }

    // Compara el campo de un empleado con un valor escrito por el usuario
    int comparar(Empleado e, String campo, String valor) {
        if (campo.equals("nombre")) return e.nombre.compareToIgnoreCase(valor);
        return Double.compare(numero(e, campo), Double.parseDouble(valor));
    }

    // Compara el mismo campo de dos empleados
    int compararEmpleados(Empleado a, Empleado b, String campo) {
        if (campo.equals("nombre")) return a.nombre.compareToIgnoreCase(b.nombre);
        return Double.compare(numero(a, campo), numero(b, campo));
    }

    double numero(Empleado e, String campo) {
        if (campo.equals("id")) return e.id;
        if (campo.equals("edad")) return e.edad;
        return e.sueldo;
    }

    String texto(Empleado e, String campo) {
        if (campo.equals("id")) return "" + e.id;
        if (campo.equals("nombre")) return e.nombre;
        if (campo.equals("edad")) return "" + e.edad;
        return "" + e.sueldo;
    }
}