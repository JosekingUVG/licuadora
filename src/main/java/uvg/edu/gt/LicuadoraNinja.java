// Objetivo: Implementación de la licuadora
package uvg.edu.gt;

/**
 * Implementación de la licuadora
 */
public class LicuadoraNinja implements Interfaz {
    private int velocidad;
    private boolean encendido;
    private boolean lleno;

    public LicuadoraNinja() {
        this.velocidad = 0;
        this.encendido = false;
        this.lleno = false;
    }

    @Override
    public void encender() {
        this.encendido = true;
        this.velocidad = 0;
    }

    @Override
    public void llenar() {
        this.lleno = true;
    }

    @Override
    public void aumentarVelocidad() {
        if (!lleno) {
            throw new IllegalStateException("No se puede operar la licuadora vacía");
        }
        if (velocidad < 10) {
            velocidad++;
        }
    }

    @Override
    public int consultarVelocidad() {
        return velocidad;
    }

    @Override
    public boolean consultarLlenado() {
        return lleno;
    }

    @Override
    public void vaciar() {
        this.lleno = false;
        this.velocidad = 0;
    }
}