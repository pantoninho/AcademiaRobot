package robot.objects;


import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;
import robot.map.Position;


/**
 * Created by pedroantoninho on 16/10/15.
 */
abstract public class GameObject {

    protected Shape obj;
    protected int x;
    protected int y;

    public GameObject() {

    }

    public GameObject(Position pos) {
        x = pos.getX();
        y = pos.getY();
    }

    public void createObject(Position pos) {

        int cellSize = pos.getGrid().getCellSize();
        obj = new Rectangle(x,y,cellSize,cellSize);
        ((Rectangle)obj).setColor(Color.BLACK);
    }

    public void draw() {
        obj.draw();
    }

    public void delete() {
        obj.delete();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
