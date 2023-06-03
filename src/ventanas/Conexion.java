package ventanas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.swing.JOptionPane;

/**
 * La clase `Conexion` se encarga de establecer y administrar la conexion con la base de datos.
 */
public class Conexion {

    // Variables miembro:

    /**
     * La conexion con la base de datos.
     */
    public static Connection conexion;

    /**
     * El objeto Statement utilizado para ejecutar consultas SQL.
     */
    static Statement stmt = null;

    /**
     * El conjunto de resultados obtenidos de una consulta SQL.
     */
    ResultSet rs = null;


    // Driver para conectarse a Oracle
    String driver = "oracle.jdbc.driver.OracleDriver";

    // URL de la base de datos, en este caso una base de datos local
    String url = "jdbc:oracle:thin:@localhost:1521:xe";

    // Usuario de la base de datos
    String usuario = "spicyApp";

    // Contraseña de la base de datos
    String contrasena = "spicyApp";

    /**
     * Establece una conexion con la base de datos.
     *
     * @return La conexion establecida.
     */
    public Connection conectar() {

        try {
            // Carga del driver de Oracle
            Class.forName(driver);

            // Conexión a la base de datos
            Conexion.conexion = DriverManager.getConnection(url, usuario, contrasena);
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver: " + e);
        } catch (SQLException e) {
            System.err.println("Error al conectarse a la base de datos: " + e);
        }
        return conexion;
    }


    /**
     * Ejecuta una consulta SQL y devuelve el valor de la columna especificada de la primera fila de resultados.
     * Si no se encuentra ninguna fila, devuelve un mensaje indicando que no se encontraron resultados.
     *
     * @param sql     La consulta SQL a ejecutar.
     * @param columna El nombre de la columna de la cual se desea obtener el valor.
     * @return El valor de la columna especificada o un mensaje de no se encontraron resultados.
     */
    public String consulta(String sql, String columna) {

        try {
            // Creación de la sentencia
            stmt = conexion.createStatement();

            // Ejecución de la consulta
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String randomValue = rs.getString(columna);
                return randomValue;
            } else {
                return "No se encontraron resultados.";
            }
        } catch (Exception e) {
            System.err.println("Error de consulta: " + e);
        }
        return null;
    }

    /**
     * Cierra el objeto ResultSet, el objeto Statement y la conexion con la base de datos.
     */
    public void desconectar() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e);
        }
    }

    /**
     * Ejecuta una consulta SQL y devuelve una lista de mapas que representan las filas de resultados.
     * Cada mapa contiene las columnas y sus respectivos valores para una fila.
     *
     * @param sql       La consulta SQL a ejecutar.
     * @param orderBy   La columna por la cual ordenar los resultados.
     * @param columnas  Las columnas que se desean obtener de cada fila.
     * @return Una lista de mapas que representan las filas de resultados.
     */
    public List<Map<String, Object>> consulta(String sql, String orderBy, String... columnas) {
        List<Map<String, Object>> resultados = new ArrayList<>();

        try {
            // Creación de la sentencia
            stmt = conexion.createStatement();

            // Ejecución de la consulta
            String consultaOrdenada = sql + " ORDER BY " + orderBy;
            rs = stmt.executeQuery(consultaOrdenada);

            while (rs.next()) {
                Map<String, Object> fila = new HashMap<>();
                for (String columna : columnas) {
                    Object valor = rs.getObject(columna);
                    fila.put(columna, valor);
                }
                resultados.add(fila);
            }

        } catch (Exception e) {
            System.err.println("Error de consulta: " + e);
        }

        return resultados;
    }

    /**
     * Elimina registros de una tabla en la base de datos que cumplan con una condición dada.
     *
     * @param tabla     El nombre de la tabla.
     * @param condicion La condición para filtrar los registros a eliminar.
     * @return El numero de filas afectadas por la eliminacion.
     */
    public int eliminar(String tabla, String condicion) {
        try {
            // Creación de la sentencia
            stmt = conexion.createStatement();

            // Construcción de la consulta de eliminación
            String consulta = "DELETE FROM " + tabla + " WHERE " + condicion;

            // Ejecución de la consulta
            int filasAfectadas = stmt.executeUpdate(consulta);

            return filasAfectadas;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            // Cerrar los recursos
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar el Statement: " + e);
                }
            }
        }

        return 0;
    }
}
