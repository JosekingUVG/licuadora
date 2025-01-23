//Dirección del paquete de Mayven donde está JUnit
package uvg.edu.gt;
//Librerias necesarias para ejecutar las pruebas
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para la licuadora
 */
public class LicuadoraNinjaTest {
    
    private Interfaz licuador;
    
    @BeforeEach
    public void setUp() {
        licuador = new LicuadoraNinja();
    }
    
    
    /**
     * Test 1: Verifica que la velocidad máxima sea 10
     */
    @Test
    public void testVelocidadMaxima() {
        // Preparación
        licuador.encender();
        licuador.llenar();
        
        // Aumentar la velocidad más de 10 veces
        for(int i = 0; i < 10; i++) {
            licuador.aumentarVelocidad();
        }
        
        // Verificación
        assertEquals(10, licuador.consultarVelocidad(), 
            "La velocidad máxima no debe ser mayor a 10");
    }
    
    /**
     * Test 2: Verifica el incremento de velocidad uno en uno
     */
    @Test
    public void testIncrementoUnoEnUno() {
        // Preparación
        licuador.encender();
        licuador.llenar();
        
        // Verificación inicial
        assertEquals(0, licuador.consultarVelocidad(), 
            "La velocidad inicial debe ser 0");
        
        // Primer incremento
        licuador.aumentarVelocidad();
        assertEquals(1, licuador.consultarVelocidad(), 
            "La velocidad debe aumentar de uno en uno");
        
        // Segundo incremento
        licuador.aumentarVelocidad();
        assertEquals(2, licuador.consultarVelocidad(), 
            "La velocidad debe aumentar de uno en uno");
    }

    /**
     * Test 3: Verifica que la licuadora se vacíe correctamente
     */
    @Test
    public void testVaciarLicuadora() {
        // Preparación
        licuador.encender();
        licuador.llenar();
        licuador.aumentarVelocidad();
        
        // Acción
        licuador.vaciar();
        
        // Verificación
        assertFalse(licuador.consultarLlenado(), 
            "La licuadora debe estar vacía después de vaciarla");
        assertEquals(0, licuador.consultarVelocidad(), 
            "La velocidad debe ser 0 después de vaciar");
    }

    /**
     * Test 4: Verifica el estado de llenado
     */
    @Test
    public void testEstadoLlenado() {
        // Estado inicial
        licuador.encender();
        assertFalse(licuador.consultarLlenado(), 
            "La licuadora debe estar vacía al inicio");
        
        // Después de llenar
        licuador.llenar();
        assertTrue(licuador.consultarLlenado(), 
            "La licuadora debe estar llena después de llenarla");
        
        // Después de vaciar
        licuador.vaciar();
        assertFalse(licuador.consultarLlenado(), 
            "La licuadora debe estar vacía después de vaciarla");
    }
}