package model;

public class Cell {
    public int row, col;
    public boolean isBlocked;

    public Cell(int row, int col, boolean isBlocked) {
        this.row = row;
        this.col = col;
        this.isBlocked = isBlocked;
    }
}
