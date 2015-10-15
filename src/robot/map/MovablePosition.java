package robot.map;

import robot.objects.Cell;
import robot.objects.Wall;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class MovablePosition extends Position {

    private int x1;
    private int y1;

    private int x2;
    private int y2;


    public MovablePosition(Position pos) {
        super(pos.getCol(), pos.getRow(), pos.getGrid());

    }


    public void moveUp() {
        getXY();

        row--;
        if (row < 0) {
            row = getGrid().getRows() - 1;
        }

        if (checkWall()) {
            row++;
        }

        updateXY();
    }

    public void moveDown() {
        getXY();

        row++;
        if (row > getGrid().getRows() - 1) {
            row = 0;
        }

        if (checkWall()) {
            row--;
        }

        updateXY();
    }

    public void moveLeft() {
        getXY();

        col--;
        if (col < 0) {
            col = getGrid().getCols() - 1;
        }

        if (checkWall()) {
            col++;
        }
        updateXY();

    }

    public void moveRight() {
        getXY();

        col++;
        if (col > getGrid().getCols() - 1) {
            col = 0;
        }

        if (checkWall()) {
            col--;
        }
        updateXY();
    }

    public int dX() {
        return x2 - x1;
    }

    public int dY() {
        return y2 - y1;
    }

    private void getXY() {
        x1 = getX();
        y1 = getY();
    }

    private void updateXY() {
        x2 = col * getGrid().getCellSize();
        y2 = row * getGrid().getCellSize();
    }

    private boolean checkWall() {

        for (Cell c : getGrid().getCells()) {
            if (c.hasObject()
                    && (c.getObject()) instanceof Wall) {

                if (c.getPos().equals(this)) {
                    return true;
                }
            }
        }
        return false;
    }
}