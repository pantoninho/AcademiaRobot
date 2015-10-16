package robot.map;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;
import robot.interfaces.Pickable;
import robot.objects.GameObject;

import java.util.Deque;
import java.util.LinkedList;

public class Cell {

    private Position pos;
    protected Shape cell;
    private Deque<GameObject> objects;
    protected int cellSize;


    public Cell() {
    }

    public Cell(Position pos) {
        this.pos = pos;
        cellSize = pos.getGrid().getCellSize();

        createCell();
        objects = new LinkedList<>();
    }

    public void draw() {
        cell.draw();
    }

    public void delete() {
        cell.delete();
    }

    public Position getPos() {
        return pos;
    }

    public boolean hasObject() {

        if (objects.size() > 0) {
            return true;
        }

        return false;
    }

    public void addObject(GameObject object) {

        object.createObject(pos);
        objects.push(object);
    }

    public GameObject getObject() {
        if (!hasObject()) {
            return null;
        }

        return objects.peek();
    }

    public void pickObject() {

        if (hasObject() && getObject() instanceof Pickable) {
            objects.pop().delete();
        }
    }

    public void drawObjects() {

        for (GameObject o : objects) {
            o.draw();
        }
    }

    public void createCell() {

        cell = new Rectangle(pos.getX(),pos.getY(),cellSize,cellSize);
        ((Rectangle) cell).setColor(Color.BLACK);
    }



}
