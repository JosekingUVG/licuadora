package uvg.edu.gt;

//----------------------------------IMPORTACIONES----------------------------------
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


//----------------------------------CLASE SONIDO LICUADORA NINJA----------------------------------
public class SonidoLicuadoraNinja {
    private Clip clip;  

    // Reproducir sonido de encendido
    public void playStartSound() {
        try {
            if (clip != null) {
                clip.close();  // Detener sonido anterior si existe
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                getClass().getResourceAsStream("/sounds/start.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para detener el sonido
    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}