package view;

import controller.MazeController;
import model.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ObstacleView extends JFrame {
    private JTextField[] obstacleFields = new JTextField[4];
    private MazeController controller;

    public ObstacleView(MazeController controller) {
        this.controller = controller;
        setTitle("Proyecto Final - Laberinto");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel instructionLabel = new JLabel("Es posible configurar hasta 4 celdas no transitables, elige correctamente:", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(instructionLabel, BorderLayout.NORTH);

        // Mostrar tabla generada
        JPanel tablePanel = new JPanel(new GridLayout(controller.getMaze().getGrid().length, controller.getMaze().getGrid()[0].length));
        Maze maze = controller.getMaze();
        for (int i = 0; i < maze.getGrid().length; i++) {
            for (int j = 0; j < maze.getGrid()[0].length; j++) {
                JLabel cellLabel = new JLabel(i + "," + j, SwingConstants.CENTER);
                cellLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tablePanel.add(cellLabel);
            }
        }
        add(tablePanel, BorderLayout.CENTER);

        // Panel de ingreso de celdas no transitables
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        for (int i = 0; i < 4; i++) {
            inputPanel.add(new JLabel("Celda no transitable " + (i + 1) + " (fila,columna):"));
            obstacleFields[i] = new JTextField();
            inputPanel.add(obstacleFields[i]);
        }

        JButton submitButton = new JButton("Siguiente");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<int[]> obstacles = new ArrayList<>();
                for (JTextField field : obstacleFields) {
                    String[] parts = field.getText().split(",");
                    if (parts.length == 2) {
                        int row = Integer.parseInt(parts[0]);
                        int col = Integer.parseInt(parts[1]);
                        obstacles.add(new int[]{row, col});
                    }
                }
                controller.setObstacles(obstacles);
                new TablaView(controller.getMaze()).setVisible(true);
                dispose();
            }
        });
        inputPanel.add(submitButton);
        add(inputPanel, BorderLayout.SOUTH);
    }
}
