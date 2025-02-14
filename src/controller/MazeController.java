package controller;

import model.*;
import view.*;

import java.util.*;

public class MazeController {
    private Maze maze;

    public void createMaze(int rows, int cols) {
        maze = new Maze(rows, cols);
    }

    public void setObstacles(List<int[]> obstacles) {
        for (int[] obs : obstacles) {
            maze.blockCell(obs[0], obs[1]);
        }
    }

    public Maze getMaze() {
        return maze;
    }

    public void launchApp() {
        new TableroView(this).setVisible(true);
    }
}
