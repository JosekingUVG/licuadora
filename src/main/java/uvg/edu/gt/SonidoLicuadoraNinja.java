package uvg.edu.gt;


// -------importación de librerías-------
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//SONIDO DE LA LICUADORA NINJA YEEHAAW
public class SonidoLicuadoraNinja {
    // Reproducir sonido de encendido
    public void playStartSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                getClass().getResourceAsStream("/sounds/start.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}