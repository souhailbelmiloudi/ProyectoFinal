package JunitTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ventanas.Registrartee;

public class RegistrarteeTest {

    private Registrartee registrartee;

    @BeforeEach
    public void setUp() {
        registrartee = new Registrartee();
    }

    @Test
    public void validarCorreo_ReturnsTrue() {
        String correo = "test@example.com";
        boolean result = registrartee.validarCorreo(correo);
        Assertions.assertTrue(result);
    }

    @Test
    public void validarCorreo_ReturnsFalse() {
        String correo = "testexample";
        boolean result = registrartee.validarCorreo(correo);
        Assertions.assertFalse(result);
    }

    @Test
    public void validarContrasena_ReturnsTrue() {
        String contrasena = "Password123";
        boolean result = registrartee.validarContrasena(contrasena);
        Assertions.assertTrue(result);
    }

    @Test
    public void validarContrasena_ReturnsFalse() {
        String contrasena = "password";
        boolean result = registrartee.validarContrasena(contrasena);
        Assertions.assertFalse(result);
    }
}
