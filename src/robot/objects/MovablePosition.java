package robot.objects;

import robot.map.Grid;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class MovablePosition extends Position {

    private int col;
    private int row;
    private int x;
    private int y;

    private Grid grid;

    public MovablePosition(Position pos, Grid grid) {
        super(pos.getCol(), pos.getRow());

        this.grid = grid;
        this.x = pos.getCol() * grid.getCellSize();
        this.y = pos.getRow() * grid.getCellSize();

    }

    public void moveUp() {
        row--;

        if (row < 0) {
            row = grid.getRows() - 1;
        }
        updateXY();
    }

    public void moveDown() {
        row++;

        if (row > grid.getRows() - 1) {
            row = 0;
        }
        updateXY();
    }

    public void moveLeft() {
        col--;

        if (col < 0) {
            col = grid.getCols() - 1;
        }
        updateXY();
    }

    public void moveRight() {
        col++;

        if (col > grid.getCols() - 1) {
            col = 0;
        }
        updateXY();
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    private void updateXY() {
        x = col * grid.getCellSize();
        y = row * grid.getCellSize();
    }

}
