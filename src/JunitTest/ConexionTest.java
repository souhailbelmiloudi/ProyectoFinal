package JunitTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ventanas.Conexion;

import static org.junit.Assert.*;

import java.sql.SQLException;

public class ConexionTest {
    
    private Conexion conexion;
    
    @Before
    public void setUp() {
        // Se crea una instancia de Conexion antes de cada prueba
        conexion = new Conexion();
    }
    
    @After
    public void tearDown() {
        // Se cierra la conexión después de cada prueba
        conexion.desconectar();
    }
    
    @Test
    public void testConexionExitosa() throws SQLException {
        // Se establece la conexión
        conexion.conectar();
        
        // Se verifica que la conexión no sea nula
        assertNotNull(Conexion.conexion);
        
        // Se verifica que la conexión no esté cerrada
        assertFalse(Conexion.conexion.isClosed());
    }
    
    
    
    
    @Test
    public void testCierreConexion() throws SQLException {
        // Se establece la conexión
        conexion.conectar();
        
        // Se cierra la conexión
        conexion.desconectar();
        
        // Se verifica que la conexión esté cerrada
        assertTrue(Conexion.conexion.isClosed());
    }
}
