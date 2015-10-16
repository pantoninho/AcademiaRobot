package robot.map;

import robot.objects.GameObject;
import robot.objects.Wall;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class MovablePosition extends Position {

    private Position pos;
    private Position nextPos;

    public MovablePosition(Position pos) {
        super(pos.getCol(), pos.getRow(), pos.getGrid());

        this.pos = pos;
        nextPos = new Position(pos.getCol(),pos.getRow(),pos.getGrid());
    }


    public void moveUp() {
        pos.match(nextPos);

        if (pos.getRow() > 0) {
            nextPos.previousRow();
        } else {
            nextPos.mirrorHoriz();
        }

        updatePos();
    }

    public void moveDown() {
        pos.match(nextPos);

        if (pos.getRow() < getGrid().getRows()) {
            nextPos.nextRow();
        } else {
            nextPos.mirrorHoriz();
        }

        updatePos();
    }

    public void moveLeft() {
        pos.match(nextPos);

        if (pos.getCol() > 0) {
            nextPos.previousCol();
        } else {
            nextPos.mirrorVert();
        }

        updatePos();
    }

    public void moveRight() {
        pos.match(nextPos);

        if (pos.getCol() < getGrid().getCols()) {
            nextPos.nextCol();
        } else {
            nextPos.mirrorVert();
        }

        updatePos();
    }

    private void updatePos() {
        if (hasWall()) {
            nextPos.match(pos);
        }
    }

    public int dX() {
        return nextPos.getX() - pos.getX();
    }

    public int dY() {
        System.out.println(nextPos.getX() - pos.getX());
        return nextPos.getY() - pos.getY();
    }

    private boolean hasWall() {

        Cell nextCell = getGrid().getCell(nextPos);

        if (nextCell.hasObject()) {
           if (nextCell.getObject() instanceof Wall)  {
               return true;
           }
        }

        return false;


    }
}