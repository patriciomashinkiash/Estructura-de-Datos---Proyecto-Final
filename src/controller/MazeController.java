package controller;

import model.*;
import view.*;

import java.util.*;

public class MazeController {
    private Maze maze;

    public void crearMaze(int rows, int cols) {
        maze = new Maze(rows, cols);
    }

    public void setObstaculos(List<int[]> obstacles) {
        for (int[] obs : obstacles) {
            maze.blockCell(obs[0], obs[1]);
        }
    }

    public Maze getMaze() {
        return maze;
    }

    public void iniciarApp() {
        new TableroView(this).setVisible(true);
    }
}
