package robot.objects;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;
import robot.map.Position;

/**
 * Created by pedroantoninho on 16/10/15.
 */
abstract public class GameObject {

    protected Shape obj;

    public GameObject() {

    }


    public void createObject(Position pos) {
        int cellSize = pos.getGrid().getCellSize();
        obj = new Rectangle(pos.getX(),pos.getY(),cellSize,cellSize);
    }

    public void draw() {
        obj.draw();
    }

    public void delete() {
        obj.delete();
    }
}
