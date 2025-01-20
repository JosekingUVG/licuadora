package uvg.edu.gt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para la licuadora
 */
public class LicuadoraNinjaTest {
    
    private Interfaz licuadora;
    
    @BeforeEach
    public void setUp() {
        licuadora = new LicuadoraNinja();
    }
    
    /**
     * Test 1: Verifica que no se pueda operar la licuadora vacía
     */
    @Test
    public void testLicuadoraVacia() {
        licuadora.encender();
        assertThrows(IllegalStateException.class, () -> {
            licuadora.aumentarVelocidad();
        }, "No se puede operar la licuadora vacía");
    }
    
    /**
     * Test 2: Verifica que la velocidad máxima sea 10
     */
    @Test
    public void testVelocidadMaxima() {
        // Preparación
        licuadora.encender();
        licuadora.llenar();
        
        // Aumentar la velocidad más de 10 veces
        for(int i = 0; i < 12; i++) {
            licuadora.aumentarVelocidad();
        }
        
        // Verificación
        assertEquals(10, licuadora.consultarVelocidad(), 
            "La velocidad no debe superar 10");
    }
    
    /**
     * Test 3: Verifica el incremento de velocidad uno en uno
     */
    @Test
    public void testIncrementoUnoEnUno() {
        // Preparación
        licuadora.encender();
        licuadora.llenar();
        
        // Verificación inicial
        assertEquals(0, licuadora.consultarVelocidad(), 
            "La velocidad inicial debe ser 0");
        
        // Primer incremento
        licuadora.aumentarVelocidad();
        assertEquals(1, licuadora.consultarVelocidad(), 
            "La velocidad debe aumentar de uno en uno");
        
        // Segundo incremento
        licuadora.aumentarVelocidad();
        assertEquals(2, licuadora.consultarVelocidad(), 
            "La velocidad debe aumentar de uno en uno");
    }

    /**
     * Test 4: Verifica que la licuadora se vacíe correctamente
     */
    @Test
    public void testVaciarLicuadora() {
        // Preparación
        licuadora.encender();
        licuadora.llenar();
        licuadora.aumentarVelocidad();
        
        // Acción
        licuadora.vaciar();
        
        // Verificación
        assertFalse(licuadora.consultarLlenado(), 
            "La licuadora debe estar vacía después de vaciarla");
        assertEquals(0, licuadora.consultarVelocidad(), 
            "La velocidad debe ser 0 después de vaciar");
    }

    /**
     * Test 5: Verifica el estado de llenado
     */
    @Test
    public void testEstadoLlenado() {
        // Estado inicial
        assertFalse(licuadora.consultarLlenado(), 
            "La licuadora debe estar vacía al inicio");
        
        // Después de llenar
        licuadora.llenar();
        assertTrue(licuadora.consultarLlenado(), 
            "La licuadora debe estar llena después de llenarla");
        
        // Después de vaciar
        licuadora.vaciar();
        assertFalse(licuadora.consultarLlenado(), 
            "La licuadora debe estar vacía después de vaciarla");
    }
}