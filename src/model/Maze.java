package model;

public class Maze {
    private Cell[][] grid;
    private int rows, cols;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(i, j, false);
            }
        }
    }

    public void blockCell(int row, int col) {
        if (isValidCell(row, col)) {
            grid[row][col].isBlocked = true;
        }
    }

    public boolean isBlocked(int row, int col) {
        return grid[row][col].isBlocked;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
