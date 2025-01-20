package uvg.edu.gt;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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