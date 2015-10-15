package robot.objects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;
import robot.map.Position;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Cell {

    private Position pos;
    protected Shape cell;
    private Cell object = null;
    protected int cellSize;


    public Cell() {
    }

    public Cell(Position pos) {
        this.pos = pos;
        cellSize = pos.getGrid().getCellSize();

        createCell();
    }

    public void draw() {
        cell.draw();

        if ( hasObject() ) {
            object.draw();
        }
    }

    public void delete() {
        cell.delete();
    }

    public Position getPos() {
        return pos;
    }

    public boolean hasObject() {

        if (object != null) {
            return true;
        }

        return false;
    }

    public Cell getObject() {
        return object;
    }

    public void addObject(Cell object) {
        this.object = object;
        object.draw();
    }

    public void removeObject() {
        object.delete();
        object = null;
    }

    public void createCell() {

        cell = new Rectangle(pos.getX(),pos.getY(),cellSize,cellSize);
        ((Rectangle) cell).setColor(Color.BLACK);
    }



}
