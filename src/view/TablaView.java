package view;

import model.Maze;
import model.Cell;
import model.AlgoritmosBusqueda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TablaView extends JFrame {
    private Maze maze;
    private JTextField campoInicio, campoFin;
    private JLabel tiempoLabel;
    private JPanel tablaPanel;

    public TablaView(Maze maze) {
        this.maze = maze;
        setTitle("Proyecto Final - Laberinto");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior con la tabla
        tablaPanel = new JPanel(new GridLayout(maze.getGrid().length, maze.getGrid()[0].length));
        for (int i = 0; i < maze.getGrid().length; i++) {
            for (int j = 0; j < maze.getGrid()[0].length; j++) {
                JLabel celda = new JLabel(i + "," + j, SwingConstants.CENTER);
                celda.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if (maze.isBlocked(i, j)) {
                    celda.setOpaque(true);
                    celda.setBackground(Color.RED);
                }
                tablaPanel.add(celda);
            }
        }
        add(tablaPanel, BorderLayout.CENTER);

        // Panel intermedio con configuracion de inicio/fin y botones
        JPanel configuracionPanel = new JPanel(new GridLayout(5, 2));
        configuracionPanel.add(new JLabel("Celda de inicio (fila,columna):"));
        campoInicio = new JTextField();
        configuracionPanel.add(campoInicio);

        configuracionPanel.add(new JLabel("Celda de fin (fila,columna):"));
        campoFin = new JTextField();
        configuracionPanel.add(campoFin);

        JButton bfsBtn = new JButton("BFS");
        bfsBtn.addActionListener(new AccionBtnsAlgoritmos("BFS"));
        JButton dfsBtn = new JButton("DFS");
        dfsBtn.addActionListener(new AccionBtnsAlgoritmos("DFS"));
        JButton recBtn = new JButton("Recursivo");
        recBtn.addActionListener(new AccionBtnsAlgoritmos("Recursivo"));
        JButton cacheBtn = new JButton("Cache");
        cacheBtn.addActionListener(new AccionBtnsAlgoritmos("Cache"));
        JButton limpiarBtn = new JButton("Limpiar");
        limpiarBtn.addActionListener(e -> limpiarTabla());

        configuracionPanel.add(bfsBtn);
        configuracionPanel.add(dfsBtn);
        configuracionPanel.add(recBtn);
        configuracionPanel.add(cacheBtn);
        configuracionPanel.add(limpiarBtn);

        add(configuracionPanel, BorderLayout.SOUTH);

        // Pie de página con tiempos de ejecución en nanosegundos
        tiempoLabel = new JLabel("Tiempo BFS:0.0000000ns | Tiempo DFS:0.0000000ns | Tiempo Recursivo:0.0000000ns | Tiempo Cache:0.0000000ns", SwingConstants.CENTER);
        tiempoLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(tiempoLabel, BorderLayout.NORTH);
    }

    private class AccionBtnsAlgoritmos implements ActionListener {
        private String meth;

        public AccionBtnsAlgoritmos(String meth) {
            this.meth = meth;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            limpiarTabla(); // Limpia la tabla antes de ejecutar cualquier algoritmo
            String[] inicio = campoInicio.getText().split(",");
            String[] fin = campoFin.getText().split(",");
            if (inicio.length == 2 && fin.length == 2) {
                int inicioRow = Integer.parseInt(inicio[0]);
                int inicioCol = Integer.parseInt(inicio[1]);
                int finRow = Integer.parseInt(fin[0]);
                int finCol = Integer.parseInt(fin[1]);

                // Verificar si las celdas inicial y final no son transitables
                if (maze.isBlocked(inicioRow, inicioCol)) {
                    JOptionPane.showMessageDialog(null, "La celda inicial es no transitable. Elija una celda válida.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (maze.isBlocked(finRow, finCol)) {
                    JOptionPane.showMessageDialog(null, "La celda final es no transitable. Elija una celda válida.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Cell inicioCelda = new Cell(inicioRow, inicioCol, false);
                Cell finCelda = new Cell(finRow, finCol, false);

                long inicioTime = System.nanoTime();
                List<Cell> path = switch (meth) {
                    case "BFS" -> AlgoritmosBusqueda.bfsSearch(maze, inicioCelda, finCelda);
                    case "DFS" -> AlgoritmosBusqueda.dfsSearch(maze, inicioCelda, finCelda);
                    case "Recursivo" -> AlgoritmosBusqueda.recursiveSearch(maze, inicioCelda, finCelda, new java.util.HashSet<>());
                    case "Cache" -> AlgoritmosBusqueda.cacheSearch(maze, inicioCelda, finCelda, new java.util.HashMap<>());
                    default -> List.of();
                };
                long finTime = System.nanoTime();
                long duracion = finTime - inicioTime;

                // Pintar celdas de inicio y fin de verde
                int cantidadCeldas = maze.getGrid()[0].length;
                if (tablaPanel.getComponent(inicioRow * cantidadCeldas + inicioCol) instanceof JLabel inicioLabel) {
                    inicioLabel.setBackground(Color.GREEN);
                    inicioLabel.setOpaque(true);
                }
                if (tablaPanel.getComponent(finRow * cantidadCeldas + finCol) instanceof JLabel finLabel) {
                    finLabel.setBackground(Color.GREEN);
                    finLabel.setOpaque(true);
                }

                // Cambiar el color de las celdas del camino a amarillo
                for (Cell celda : path) {
                    if ((celda.row != inicioRow || celda.col != inicioCol) && (celda.row != finRow || celda.col != finCol)) {
                        Component comp = tablaPanel.getComponent(celda.row * maze.getGrid()[0].length + celda.col);
                        if (comp instanceof JLabel label && !maze.isBlocked(celda.row, celda.col)) {
                            label.setBackground(Color.YELLOW);
                            label.setOpaque(true);
                        }
                    }
                }

                // Actualizar la etiqueta del tiempo
                actualizarTiempo(meth, duracion);
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese las celdas en formato fila,columna", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void actualizarTiempo(String meth, long duracion) {
            String reinicioTiempo = String.format("%.7f", duracion / 1_000_000_000.0);
            String txtActual = tiempoLabel.getText();
            String newTxt;
            if (meth.equals("BFS")) {
                newTxt = txtActual.replaceAll("Tiempo BFS:[^|]+", " Tiempo BFS:" + reinicioTiempo + "ns ");
            } else if (meth.equals("DFS")) {
                newTxt = txtActual.replaceAll("Tiempo DFS:[^|]+", " Tiempo DFS:" + reinicioTiempo + "ns ");
            } else if (meth.equals("Recursivo")) {
                newTxt = txtActual.replaceAll("Tiempo Recursivo:[^|]+", " Tiempo Recursivo:" + reinicioTiempo + "ns ");
            } else {
                newTxt = txtActual.replaceAll("Tiempo Cache:[^|]+", " Tiempo Cache:" + reinicioTiempo + "ns ");
            }
            tiempoLabel.setText(newTxt);
        }
    }

    private void limpiarTabla() {
        int rows = maze.getGrid().length;
        int cols = maze.getGrid()[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Component comp = tablaPanel.getComponent(i * cols + j);
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

        tiempoLabel.setText("Tiempo BFS:0.0000000ns | Tiempo DFS:0.0000000ns | Tiempo Recursivo:0.0000000ns | Tiempo Cache:0.0000000ns");
    }
}
