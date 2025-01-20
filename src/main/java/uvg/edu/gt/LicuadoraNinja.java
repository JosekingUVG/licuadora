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
        System.out.println("La licuadora Ninja se ha encendido.");
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
        System.out.println("La licuadora Ninja se ha llenado.");
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
        System.out.println("Velocidad aumentada. Velocidad actual: " + velocidad);
    }

    @Override
    public int consultarVelocidad() {
        if (!encendido) {
            throw new IllegalStateException("La licuadora está apagada");
        }
        System.out.println("La velocidad actual es: " + velocidad);
        return velocidad;
    }

    @Override
    public boolean consultarLlenado() {
        System.out.println("La licuadora Ninja está " + (lleno ? "llena." : "vacía."));
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
        System.out.println("La licuadora Ninja se ha vaciado.");
    }
}