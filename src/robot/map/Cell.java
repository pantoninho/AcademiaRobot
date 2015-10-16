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

    public void addObject(GameObject object) {

        object.createObject(pos);
        objects.push(object);
    }

    public boolean hasObject() {

        if (objects.size() > 0) {
            return true;
        }

        return false;
    }

    public GameObject getObject() {
        if (!hasObject()) {
            return null;
        }

        return objects.peek();
    }

    public Pickable pickObject() {

        Pickable obj = null;

        if (hasObject() && getObject() instanceof Pickable) {
            obj = (Pickable)objects.peek();
            objects.pop().delete();
        }
        return obj;
    }

    public void drawObjects() {

        for (GameObject o : objects) {
            o.draw();
        }
    }

    public void dropPickable(Pickable obj) {

        GameObject thisObj = (GameObject) obj;
        thisObj.createObject(pos);
        thisObj.draw();
        objects.push(thisObj);
    }

    public void createCell() {

        cell = new Rectangle(pos.getX(),pos.getY(),cellSize,cellSize);
        ((Rectangle) cell).setColor(Color.BLACK);
    }



}
