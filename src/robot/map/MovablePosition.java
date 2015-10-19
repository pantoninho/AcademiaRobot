package robot.map;

import robot.objects.Wall;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class MovablePosition {

    private Position pos;
    private Position nextPos;

    public MovablePosition(Position pos) {
        this.pos = pos;
        nextPos = new Position(pos.getCol(),pos.getRow(),pos.getGrid());
    }

    public void moveUp() {

        if (pos.getRow() > 0) {
            nextPos.previousRow();
        } else {
            nextPos.mirrorHoriz();
        }

        checkWall();
    }

    public void moveDown() {

        if (pos.getRow() < pos.getGrid().getRows() - 1) {
            nextPos.nextRow();
        } else {
            nextPos.mirrorHoriz();
        }

        checkWall();
    }

    public void moveLeft() {

        if (pos.getCol() > 0) {
            nextPos.previousCol();
        } else {
            nextPos.mirrorVert();
        }

        checkWall();
    }

    public void moveRight() {

        if (pos.getCol() < pos.getGrid().getCols() - 1) {
            nextPos.nextCol();
        } else {
            nextPos.mirrorVert();
        }
        checkWall();
    }

    private void checkWall() {
        if (hasWall()) {
            nextPos.match(pos);
        }
    }

    public void update() {
        pos.match(nextPos);
    }

    public int dX() {
        return nextPos.getX() - pos.getX();
    }

    public int dY() {
        return nextPos.getY() - pos.getY();
    }

    public Position getPos() {
        return pos;
    }

    private boolean hasWall() {

        Cell nextCell = nextPos.getCellOnGrid();
        nextCell.resetObjIterator();

        while (nextCell.objectIterator().hasNext()) {
            if (nextCell.objectIterator().next() instanceof Wall) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "MovablePosition{" +
                "pos=" + pos +
                ", nextPos=" + nextPos +
                '}';
    }
}