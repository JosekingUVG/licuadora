package uvg.edu.gt;
/**
 * Implementación de la licuadora con lógica de negocio mejorada
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
        if (encendido) {
            // Si está encendida, la apagamos
            this.encendido = false;
            this.velocidad = 0;
            System.out.println("La licuadora se ha apagado.");
        } else {
            // Si está apagada, la encendemos
            this.encendido = true;
            this.velocidad = 0;
            System.out.println("La licuadora Ninja se ha encendido.");
        }
    }

    @Override
    public void llenar() {
        if (lleno) {
            System.out.println("La licuadora ya está llena");
        } else {
            this.lleno = true;
            System.out.println("La licuadora Ninja se ha llenado.");
        }
    }

    
    @Override
    public void aumentarVelocidad() {
        if (!lleno) {
            System.out.println("No se puede operar la licuadora vacía");
        }
        if (velocidad < 10) {
            velocidad++;
            System.out.println("Velocidad aumentada a: " + velocidad);
        } else {
            System.out.println("Ya ha alcanzado la velocidad máxima");
        }
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
        if (!lleno) {
            System.out.println("La licuadora ya está vacía");
        } else {
            this.lleno = false;
            this.velocidad = 0;
            System.out.println("La licuadora Ninja se ha vaciado.");
        }
    }
}