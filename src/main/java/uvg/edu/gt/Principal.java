package uvg.edu.gt;

import javax.swing.SwingUtilities;

//----------CLASE PRINCIPAL-----------
public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //LINEA QUE SE PUEDE INTERCAMBIAR AMIGOS GRAAHHHH DEKFNAKDFNASFKDFKAFNAKFDNFJANFJ
            VistaGUI vista = new VistaGUI();
            vista.setVisible(true);
        });
    }
}