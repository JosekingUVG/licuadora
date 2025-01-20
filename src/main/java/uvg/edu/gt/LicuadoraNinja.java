package uvg.edu.gt;

/**
 * Implementación de la licuadora con lógica de negocio mejorada
 */
public class LicuadoraNinja implements Interfaz {
    private int velocidad;
    private boolean encendido;
    private boolean lleno;
    private static final int VELOCIDAD_MAXIMA = 10;

    public LicuadoraNinja() {
        this.velocidad = 0;
        this.encendido = false;
        this.lleno = false;
    }

    @Override
    public void encender() {
        if (encendido) {
            throw new IllegalStateException("La licuadora ya está encendida");
        }
        this.encendido = true;
        this.velocidad = 0;
    }

    @Override
    public void llenar() {
        if (!encendido) {
            throw new IllegalStateException("Debe encender la licuadora primero");
        }
        if (lleno) {
            throw new IllegalStateException("La licuadora ya está llena");
        }
        this.lleno = true;
    }

    @Override
    public void aumentarVelocidad() {
        if (!encendido) {
            throw new IllegalStateException("La licuadora debe estar encendida");
        }
        if (!lleno) {
            throw new IllegalStateException("No se puede operar la licuadora vacía");
        }
        if (velocidad >= VELOCIDAD_MAXIMA) {
            throw new IllegalStateException("Ya está en velocidad máxima");
        }
        velocidad++;
    }

    @Override
    public int consultarVelocidad() {
        if (!encendido) {
            throw new IllegalStateException("La licuadora está apagada");
        }
        return velocidad;
    }

    @Override
    public boolean consultarLlenado() {
        return lleno;
    }

    @Override
    public void vaciar() {
        if (!encendido) {
            throw new IllegalStateException("Debe encender la licuadora primero");
        }
        if (!lleno) {
            throw new IllegalStateException("La licuadora ya está vacía");
        }
        this.lleno = false;
        this.velocidad = 0;
    }
}