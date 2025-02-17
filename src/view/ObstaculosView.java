package view;

import controller.MazeController;
import model.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ObstaculosView extends JFrame {
    private JTextField[] camposObs = new JTextField[4];
    private MazeController controller;

    public ObstaculosView(MazeController controller) {
        this.controller = controller;
        setTitle("Proyecto Final - Laberinto");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel instLabel = new JLabel("Es posible configurar hasta 4 celdas no transitables, elige correctamente:", SwingConstants.CENTER);
        instLabel.setFont(new Font(" ", Font.BOLD, 14));
        add(instLabel, BorderLayout.NORTH);

        // Mostrar tabla generada
        JPanel tablaPanel = new JPanel(new GridLayout(controller.getMaze().getGrid().length, controller.getMaze().getGrid()[0].length));
        Maze maze = controller.getMaze();
        for (int i = 0; i < maze.getGrid().length; i++) {
            for (int j = 0; j < maze.getGrid()[0].length; j++) {
                JLabel celdaLabel = new JLabel(i + "," + j, SwingConstants.CENTER);
                celdaLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tablaPanel.add(celdaLabel);
            }
        }
        add(tablaPanel, BorderLayout.CENTER);

        // Panel de ingreso de celdas no transitables
        JPanel entradaPanel = new JPanel(new GridLayout(5, 2));
        for (int i = 0; i < 4; i++) {
            entradaPanel.add(new JLabel("Celda no transitable " + (i + 1) + " (fila,columna):"));
            camposObs[i] = new JTextField();
            entradaPanel.add(camposObs[i]);
        }

        JButton siguienteBtn = new JButton("Siguiente");
        siguienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<int[]> obstaculos = new ArrayList<>();
                for (JTextField campos : camposObs) {
                    String[] partes = campos.getText().split(",");
                    if (partes.length == 2) {
                        int row = Integer.parseInt(partes[0]);
                        int col = Integer.parseInt(partes[1]);
                        obstaculos.add(new int[]{row, col});
                    }
                }
                controller.setObstaculos(obstaculos);
                new TablaView(controller.getMaze()).setVisible(true);
                dispose();
            }
        });
        entradaPanel.add(siguienteBtn);
        add(entradaPanel, BorderLayout.SOUTH);
    }
}
