package uvg.edu.gt;


//-----importación del paquete de sonido------
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;




//------Clase VistaGUI-----------------------------------
public class VistaGUI extends JFrame {
    private Interfaz licuadora;
    private JLabel estadoLabel;
    private JLabel velocidadLabel;
    private JProgressBar velocidadBar;
    private RoundButton powerBtn, llenarBtn, velocidadBtn, vaciarBtn;
    private Timer glowTimer;
    private float glowAlpha = 0.0f;
    private boolean glowIncreasing = true;
    private SonidoLicuadoraNinja sonido;


    public VistaGUI() {
        this.licuadora = new LicuadoraNinja(); //ESTA ES LA LINEA QUE DEBE CAMBIARSEE
        this.sonido = new SonidoLicuadoraNinja();
        setupGUI();
        initGlowEffect();
    }


    // Efecto de brillo en la barra de velocidad
    private void initGlowEffect() {
        glowTimer = new Timer(50, e -> {
            if (glowIncreasing) {
                glowAlpha += 0.05f;
                if (glowAlpha >= 1.0f) {
                    glowAlpha = 1.0f;
                    glowIncreasing = false;
                }
            } else {
                glowAlpha -= 0.05f;
                if (glowAlpha <= 0.0f) {
                    glowAlpha = 0.0f;
                    glowIncreasing = true;
                }
            }
            velocidadBar.repaint();
        });
        glowTimer.start();
    }


    // Configuración de la interfaz gráfica
    private void setupGUI() {
        setTitle("Licuadora Ninja - Grupo 4");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);

        // Panel principal con fondo blanco y bordes suaves
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 255, 255), 
                                                    0, getHeight(), new Color(240, 240, 245));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Panel de estado
        JPanel statusPanel = new JPanel(new BorderLayout(15, 15));
        statusPanel.setOpaque(false);
        
        estadoLabel = new JLabel("LICUADORA APAGADA", SwingConstants.CENTER);
        estadoLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 24));
        estadoLabel.setForeground(new Color(50, 50, 50));
        
        velocidadLabel = new JLabel("VELOCIDAD ACTUAL: 0", SwingConstants.CENTER);
        velocidadLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        velocidadLabel.setForeground(new Color(100, 100, 100));
        
        // Barra de velocidad personalizada
        velocidadBar = new JProgressBar(0, 10) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
            }
        };

        // Estilo de la barra de velocidad
        velocidadBar.setPreferredSize(new Dimension(getWidth(), 30));
        velocidadBar.setBorderPainted(false);
        velocidadBar.setStringPainted(false);
        

        // Panel de estado
        statusPanel.add(estadoLabel, BorderLayout.NORTH);
        statusPanel.add(velocidadLabel, BorderLayout.CENTER);
        statusPanel.add(velocidadBar, BorderLayout.SOUTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setOpaque(false);

        // Botones redondeados personalizados
        powerBtn = new RoundButton("LICUADORA PRENDIDA", new Color(0, 122, 204));
        llenarBtn = new RoundButton("CARGAR INGREDIENTES", new Color(0, 180, 160));
        velocidadBtn = new RoundButton("AUMENTAR POTENCIA", new Color(0, 160, 200));
        vaciarBtn = new RoundButton("VACIAR LICUADORA", new Color(207, 89, 0));

        // Añadir botones al panel
        buttonPanel.add(powerBtn);
        buttonPanel.add(llenarBtn);
        buttonPanel.add(velocidadBtn);
        buttonPanel.add(vaciarBtn);

        // Añadir paneles
        mainPanel.add(statusPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Añadir acciones a los botones
        setupButtonActions();
        setContentPane(mainPanel);
    }

    private static class velocidadBar {

        public velocidadBar() {
        }
    }

    // Clase para botones redondeados con efecto hover
    private class RoundButton extends JButton {
        private Color baseColor;
        private boolean isHovered = false;

    // Constructor con texto y color base
        public RoundButton(String text, Color color) {
            super(text);
            this.baseColor = color;
            setupButton();
        }

        // Configuración de botón PARA EL EFECTO HOVER
        private void setupButton() {
            setFont(new Font("Helvetica Neue", Font.BOLD, 14));
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setForeground(Color.WHITE);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    isHovered = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    isHovered = false;
                    repaint();
                }
            });
        }

        // Dibuja el botón con efecto hover
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Color base con efecto hover
            Color buttonColor = isHovered ? baseColor.brighter() : baseColor;
            
            // Dibuja el fondo del botón
            g2d.setColor(buttonColor);
            g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));

            // Añade efecto de brillo
            GradientPaint shine = new GradientPaint(0, 0, 
                new Color(255, 255, 255, 100), 
                0, getHeight()/2, 
                new Color(255, 255, 255, 0));
            g2d.setPaint(shine);
            g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight()/2, 20, 20));

            // Dibuja el texto
            g2d.setColor(Color.WHITE);
            FontMetrics metrics = g2d.getFontMetrics(getFont());
            int x = (getWidth() - metrics.stringWidth(getText())) / 2;
            int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
            g2d.setFont(getFont());
            g2d.drawString(getText(), x, y);
        }
    }

//------------------------ACCIONES--------------------------------------
    //Boton de encender licuadora 
    private void setupButtonActions() {
        powerBtn.addActionListener(e -> {
            licuadora.encender();
            estadoLabel.setText("LICUADORA ENCENDIDA");
            estadoLabel.setForeground(new Color(0, 150, 255));
            powerBtn.setEnabled(false);
            sonido.playStartSound();
        });
    //Boton de llenar la licuadora
        llenarBtn.addActionListener(e -> {
            licuadora.llenar();
            estadoLabel.setText("COMIDA CARGADA");
            estadoLabel.setForeground(new Color(0, 180, 160));
            velocidadBtn.setEnabled(true);
        });
    //Boton de aumentar la velocidad de la licuadora
        velocidadBtn.addActionListener(e -> {
            try {
                licuadora.aumentarVelocidad();
                int velocidad = licuadora.consultarVelocidad();
                velocidadLabel.setText("VELOCIDAD ACTUAL: " + velocidad);
                velocidadBar.setValue(velocidad);
                if (velocidad == 10) {
                    estadoLabel.setText("POTENCIA MÁXIMA");
                    estadoLabel.setForeground(new Color(220, 50, 50));
                }
            } catch (IllegalStateException ex) {
                showErrorDialog(ex.getMessage());
            }
        });

    // Botón para vaciar la licuadora
        vaciarBtn.addActionListener(e -> {
            licuadora.vaciar();
            estadoLabel.setText("LICUADORA VACÍA");
            estadoLabel.setForeground(new Color(100, 100, 100));
            velocidadLabel.setText("VELOCIDAD ACTUAL: 0");
            velocidadBar.setValue(0);
            powerBtn.setEnabled(true);
            velocidadBtn.setEnabled(false);
        });

        velocidadBtn.setEnabled(false);
    }

    // Método para mostrar un diálogo de error
    private void showErrorDialog(String message) {
        JDialog dialog = new JDialog(this, "Error", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);

        JLabel errorLabel = new JLabel(message, SwingConstants.CENTER);
        errorLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
        errorLabel.setBorder(new EmptyBorder(20, 20, 20, 20));

        RoundButton okButton = new RoundButton("ENTENDIDO", new Color(0, 150, 255));
        okButton.addActionListener(e -> dialog.dispose());

        dialog.add(errorLabel, BorderLayout.CENTER);
        dialog.add(okButton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // Método para actualizar la velocidad
    public void actualizarVelocidad(int velocidad) {
        velocidadLabel.setText("VELOCIDAD ACTUAL: " + velocidad);
        velocidadBar.setValue(velocidad);
    }

}