package robot.map;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Cell {

    protected Position pos;
    protected Shape object;
    protected int cellSize;


    public Cell() {
    }

    public Cell(Position pos, int cellSize) {
        this.pos = pos;
        this.cellSize = cellSize;

        createObject();
    }

    public void draw() {
        object.draw();
    }

    public void delete() {
        object.delete();
    }

    public void createObject() {

        object = new Rectangle(pos.getCol()*cellSize,pos.getRow()*cellSize,cellSize,cellSize);

        ((Rectangle)object).setColor(Color.BLACK);
    }
}
