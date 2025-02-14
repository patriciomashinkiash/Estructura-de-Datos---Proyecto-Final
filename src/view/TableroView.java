package view;

import controller.MazeController;

import javax.swing.*;
import java.awt.*;

public class TableroView extends JFrame {
    private JTextField rowsField, colsField;

    public TableroView(MazeController controller) {
        setTitle("Proyecto Final - Laberinto");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración del diseño principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));

        // Titulo principal
        JLabel titleLabel = new JLabel("GENERAR TABLERO", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        panel.add(titleLabel);

        // Subtitulo
        JLabel infoLabel = new JLabel("El ingreso del tamaño de la tabla debe ser entre 3x3 y 9x9", SwingConstants.CENTER);
        infoLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel.add(infoLabel);

        // Ingreso de filas
        JLabel rowsLabel = new JLabel("Numero de filas:", SwingConstants.CENTER);
        rowsLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel.add(rowsLabel);

        rowsField = new JTextField();
        panel.add(rowsField);

        // Ingreso de columnas
        JLabel colsLabel = new JLabel("Numero de columnas:", SwingConstants.CENTER);
        colsLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel.add(colsLabel);

        colsField = new JTextField();
        panel.add(colsField);

        // Botón siguiente
        JButton nextButton = new JButton("Siguiente");
        nextButton.addActionListener(e -> {
            try {
                int rows = Integer.parseInt(rowsField.getText());
                int cols = Integer.parseInt(colsField.getText());
                if (rows >= 3 && rows <= 9 && cols >= 3 && cols <= 9) {
                    controller.createMaze(rows, cols);
                    new ObstacleView(controller).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Las dimensiones deben estar entre 3 y 9.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese numeros vulidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(nextButton);

        add(panel);
    }
}

