package JunitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ventanas.Conexion;
import ventanas.Sign_in;
import ventanas.TUS_PREGUNTAS;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

public class TUS_PREGUNTASTest {
	Conexion conexion ;
	Connection con;
	String id_jugador;
    private TUS_PREGUNTAS tusPreguntas;

    @BeforeEach
    public void setUp() {
    	conexion = new Conexion();
        tusPreguntas = new TUS_PREGUNTAS();
        conexion.conectar();
        Sign_in.usuario = "Souhail01";// usuario del jogador conectado 
		String consulta_id = "SELECT ID_JUGADOR FROM JUGADORES WHERE USUARIO='"+Sign_in.usuario+"'";
		
		
		 id_jugador = conexion.consulta(consulta_id,"ID_JUGADOR");

        
    }

    @Test
    public void testNombreTabla() {
        String nombreTabla = tusPreguntas.nombretabla();
        assertEquals("TABLA_SOUHAIL01_"+id_jugador, nombreTabla);

    }
}
