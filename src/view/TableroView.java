    package view;

    import controller.MazeController;

    import javax.swing.*;
    import java.awt.*;

    public class TableroView extends JFrame {
        private JTextField campoRows, campoCols;

        public TableroView(MazeController controller) {
            setTitle("Proyecto Final - Laberinto");
            setSize(500, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Configuración del diseño principal
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(7, 1));

            // Titulo principal
            JLabel tituloLabel = new JLabel("GENERAR TABLERO", SwingConstants.CENTER);
            tituloLabel.setFont(new Font(" ", Font.BOLD, 24));
            panel.add(tituloLabel);

            // Subtitulo
            JLabel txtLabel = new JLabel("El ingreso del tamaño de la tabla debe ser entre 3x3 y 9x9", SwingConstants.CENTER);
            txtLabel.setFont(new Font(" ", Font.PLAIN, 14));
            panel.add(txtLabel);

            // Ingreso de filas
            JLabel rowsLabel = new JLabel("Numero de filas:", SwingConstants.CENTER);
            rowsLabel.setFont(new Font(" ", Font.PLAIN, 16));
            panel.add(rowsLabel);

            campoRows = new JTextField();
            panel.add(campoRows);

            // Ingreso de columnas
            JLabel colsLabel = new JLabel("Numero de columnas:", SwingConstants.CENTER);
            colsLabel.setFont(new Font(" ", Font.PLAIN, 16));
            panel.add(colsLabel);

            campoCols = new JTextField();
            panel.add(campoCols);

            // Boton siguiente
            JButton siguienteBtn = new JButton("Siguiente");
            siguienteBtn.addActionListener(e -> {
                try {
                    int rows = Integer.parseInt(campoRows.getText());
                    int cols = Integer.parseInt(campoCols.getText());
                    if (rows >= 3 && rows <= 9 && cols >= 3 && cols <= 9) {
                        controller.crearMaze(rows, cols);
                        new ObstaculosView(controller).setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Las dimensiones deben estar entre 3 y 9.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese numeros vulidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(siguienteBtn);

            add(panel);
        }
    }

