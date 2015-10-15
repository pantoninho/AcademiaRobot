package robot.objects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;
import robot.map.Position;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Cell {

    protected Position pos;
    protected Shape object;
    protected int cellSize;


    public Cell() {
    }

    public Cell(Position pos) {
        this.pos = pos;
        cellSize = pos.getGrid().getCellSize();

        createObject();
    }

    public void draw() {
        object.draw();
    }

    public void delete() {
        object.delete();
    }

    public Position getPos() {
        return pos;
    }

    public void createObject() {

        object = new Rectangle(pos.getX(),pos.getY(),cellSize,cellSize);

        ((Rectangle)object).setColor(Color.BLACK);
    }


}
