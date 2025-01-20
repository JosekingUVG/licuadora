package uvg.edu.gt;

import javax.swing.SwingUtilities;

public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaGUI vista = new VistaGUI();
            vista.setVisible(true);
        });
    }
}