package view;

import model.Maze;
import model.Cell;
import model.SearchAlgorithms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TablaView extends JFrame {
    private Maze maze;
    private JTextField startField, endField;
    private JLabel timeLabel;
    private JPanel tablePanel;

    public TablaView(Maze maze) {
        this.maze = maze;
        setTitle("Proyecto Final - Laberinto");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior con la tabla
        tablePanel = new JPanel(new GridLayout(maze.getGrid().length, maze.getGrid()[0].length));
        for (int i = 0; i < maze.getGrid().length; i++) {
            for (int j = 0; j < maze.getGrid()[0].length; j++) {
                JLabel cell = new JLabel(i + "," + j, SwingConstants.CENTER);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if (maze.isBlocked(i, j)) {
                    cell.setOpaque(true);
                    cell.setBackground(Color.RED);
                }
                tablePanel.add(cell);
            }
        }
        add(tablePanel, BorderLayout.CENTER);

        // Panel intermedio con configuración de inicio/fin y botones
        JPanel configPanel = new JPanel(new GridLayout(5, 2));
        configPanel.add(new JLabel("Celda de inicio (fila,columna):"));
        startField = new JTextField();
        configPanel.add(startField);

        configPanel.add(new JLabel("Celda de fin (fila,columna):"));
        endField = new JTextField();
        configPanel.add(endField);

        JButton bfsButton = new JButton("BFS");
        bfsButton.addActionListener(new AlgorithmButtonListener("BFS"));
        JButton dfsButton = new JButton("DFS");
        dfsButton.addActionListener(new AlgorithmButtonListener("DFS"));
        JButton recButton = new JButton("Recursivo");
        recButton.addActionListener(new AlgorithmButtonListener("Recursivo"));
        JButton cacheButton = new JButton("Cache");
        cacheButton.addActionListener(new AlgorithmButtonListener("Cache"));
        JButton clearButton = new JButton("Limpiar");
        clearButton.addActionListener(e -> resetTable());

        configPanel.add(bfsButton);
        configPanel.add(dfsButton);
        configPanel.add(recButton);
        configPanel.add(cacheButton);
        configPanel.add(clearButton);

        add(configPanel, BorderLayout.SOUTH);

        // Pie de página con tiempos de ejecución en nanosegundos con 7 decimales
        timeLabel = new JLabel("Tiempo BFS:0.0000000ns | Tiempo DFS:0.0000000ns | Tiempo Recursivo:0.0000000ns | Tiempo Cache:0.0000000ns", SwingConstants.CENTER);
        timeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(timeLabel, BorderLayout.NORTH);
    }

    private class AlgorithmButtonListener implements ActionListener {
        private String method;

        public AlgorithmButtonListener(String method) {
            this.method = method;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] start = startField.getText().split(",");
            String[] end = endField.getText().split(",");
            if (start.length == 2 && end.length == 2) {
                int startRow = Integer.parseInt(start[0]);
                int startCol = Integer.parseInt(start[1]);
                int endRow = Integer.parseInt(end[0]);
                int endCol = Integer.parseInt(end[1]);

                // Verificar si las celdas inicial y final no son transitables
                if (maze.isBlocked(startRow, startCol)) {
                    JOptionPane.showMessageDialog(null, "La celda inicial es no transitable. Elija una celda válida.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (maze.isBlocked(endRow, endCol)) {
                    JOptionPane.showMessageDialog(null, "La celda final es no transitable. Elija una celda válida.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Cell startCell = new Cell(startRow, startCol, false);
                Cell endCell = new Cell(endRow, endCol, false);

                long startTime = System.nanoTime();
                List<Cell> path = switch (method) {
                    case "BFS" -> SearchAlgorithms.bfs(maze, startCell, endCell);
                    case "DFS" -> SearchAlgorithms.dfs(maze, startCell, endCell);
                    case "Recursivo" -> SearchAlgorithms.recursiveSearch(maze, startCell, endCell, new java.util.HashSet<>());
                    case "Cache" -> SearchAlgorithms.cachedSearch(maze, startCell, endCell, new java.util.HashMap<>());
                    default -> List.of();
                };
                long endTime = System.nanoTime();
                long duration = endTime - startTime;

                // Pintar celdas de inicio y fin de verde
                int cellCount = maze.getGrid()[0].length;
                if (tablePanel.getComponent(startRow * cellCount + startCol) instanceof JLabel startLabel) {
                    startLabel.setBackground(Color.GREEN);
                    startLabel.setOpaque(true);
                }
                if (tablePanel.getComponent(endRow * cellCount + endCol) instanceof JLabel endLabel) {
                    endLabel.setBackground(Color.GREEN);
                    endLabel.setOpaque(true);
                }

                // Cambiar el color de las celdas del camino a amarillo
                for (Cell cell : path) {
                    Component comp = tablePanel.getComponent(cell.row * maze.getGrid()[0].length + cell.col);
                    if (comp instanceof JLabel label && !maze.isBlocked(cell.row, cell.col)) {
                        label.setBackground(Color.YELLOW);
                        label.setOpaque(true);
                    }
                }

                // Actualizar la etiqueta del tiempo con 7 decimales
                updateTimeLabel(method, duration);
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese las celdas en formato fila,columna", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void updateTimeLabel(String method, long duration) {
            String formattedTime = String.format("%.7f", duration / 1_000_000_000.0);
            String currentText = timeLabel.getText();
            String newText;
            if (method.equals("BFS")) {
                newText = currentText.replaceAll("Tiempo BFS:[^|]+", "Tiempo BFS:" + formattedTime + "ns");
            } else if (method.equals("DFS")) {
                newText = currentText.replaceAll("Tiempo DFS:[^|]+", "Tiempo DFS:" + formattedTime + "ns");
            } else if (method.equals("Recursivo")) {
                newText = currentText.replaceAll("Tiempo Recursivo:[^|]+", "Tiempo Recursivo:" + formattedTime + "ns");
            } else {
                newText = currentText.replaceAll("Tiempo Cache:[^|]+", "Tiempo Cache:" + formattedTime + "ns");
            }
            timeLabel.setText(newText);
        }
    }

    private void resetTable() {
        int rows = maze.getGrid().length;
        int cols = maze.getGrid()[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Component comp = tablePanel.getComponent(i * cols + j);
                if (comp instanceof JLabel label) {
                    if (maze.isBlocked(i, j)) {
                        label.setBackground(Color.RED);
                        label.setOpaque(true);
                    } else {
                        label.setBackground(null);
                        label.setOpaque(false);
                    }
                }
            }
        }

        timeLabel.setText("Tiempo BFS:0.0000000ns | Tiempo DFS:0.0000000ns | Tiempo Recursivo:0.0000000ns | Tiempo Cache:0.0000000ns");
    }
}
