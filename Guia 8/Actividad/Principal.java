package actividad;

import java.sql.*;

public class Principal {

    public static void main(String[] args) throws Exception {

        // 1. Conectar con la base de datos
        Connection con = DriverManager.getConnection("jdbc:sqlite:empresa.db");
        Statement st = con.createStatement();

        // 2. Crear la tabla con 3 campos y dejarla vacia
        st.executeUpdate("CREATE TABLE IF NOT EXISTS empleados (id INTEGER PRIMARY KEY, nombre TEXT, edad INTEGER)");
        st.executeUpdate("DELETE FROM empleados");

        // 3. INSERTAR con PreparedStatement
        PreparedStatement insertar = con.prepareStatement("INSERT INTO empleados VALUES (?, ?, ?)");
        insertar.setInt(1, 1);
        insertar.setString(2, "Carlos");
        insertar.setInt(3, 25);
        insertar.executeUpdate();

        insertar.setInt(1, 2);
        insertar.setString(2, "Maria");
        insertar.setInt(3, 30);
        insertar.executeUpdate();

        System.out.println("--- Despues de INSERTAR ---");
        mostrar(con);

        // 4. ACTUALIZAR con PreparedStatement
        PreparedStatement actualizar = con.prepareStatement("UPDATE empleados SET edad = ? WHERE id = ?");
        actualizar.setInt(1, 26);
        actualizar.setInt(2, 1);
        actualizar.executeUpdate();

        System.out.println("--- Despues de ACTUALIZAR ---");
        mostrar(con);

        // 5. BORRAR con PreparedStatement
        PreparedStatement borrar = con.prepareStatement("DELETE FROM empleados WHERE id = ?");
        borrar.setInt(1, 2);
        borrar.executeUpdate();

        System.out.println("--- Despues de BORRAR ---");
        mostrar(con);

        // 6. TRANSACCIONES
        con.setAutoCommit(false); // ahora los cambios no se guardan solos

        // Insertamos a Pedro y lo deshacemos (ROLLBACK)
        insertar.setInt(1, 3);
        insertar.setString(2, "Pedro");
        insertar.setInt(3, 40);
        insertar.executeUpdate();
        con.rollback();

        System.out.println("--- Despues de ROLLBACK (Pedro no se guardo) ---");
        mostrar(con);

        // Insertamos a Diana y la guardamos (COMMIT)
        insertar.setInt(1, 4);
        insertar.setString(2, "Diana");
        insertar.setInt(3, 32);
        insertar.executeUpdate();
        con.commit();

        System.out.println("--- Despues de COMMIT (Diana si se guardo) ---");
        mostrar(con);

        con.close();
    }

    // RECUPERAR: muestra todos los registros de la tabla
    static void mostrar(Connection con) throws Exception {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM empleados");

        while (rs.next()) {
            System.out.println(rs.getInt("id") + " | " + rs.getString("nombre") + " | " + rs.getInt("edad"));
        }
    }
}