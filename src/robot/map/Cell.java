package robot.map;

import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import robot.interfaces.Pickable;
import robot.objects.GameObject;

import java.util.*;

public class Cell {

    private Position pos;
    protected Shape cell;
    private Queue<GameObject> objects;
    protected int cellSize;

    public Cell() {
    }

    public Cell(Position pos) {
        this.pos = pos;
        cellSize = pos.getGrid().getCellSize();

        createCell();
        objects = new PriorityQueue<>(10,new ItemSorter());
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
        objects.add(object);
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

        GameObject obj = null;

        if (hasObject() && getObject() instanceof Pickable) {
            obj = objects.poll();
            obj.delete();
        }
        return (Pickable)obj;
    }

    public void drawObjects() {

        Iterator<GameObject> objIter = objects.iterator();



    }

    public void dropPickable(Pickable _obj) {

        GameObject obj = (GameObject) _obj;
        obj.createObject(pos);
        objects.add(obj);

        drawObjects();
    }

    public void createCell() {

        cell = new Picture(pos.getX(),pos.getY(),getRandomImg());
    }

    private String getRandomImg() {

        int randomNr = (int)(3*Math.random());
        return "resources/grass_" + randomNr + ".png";
    }

    class ItemSorter implements Comparator<GameObject> {

        @Override
        public int compare(GameObject o1, GameObject o2) {
            if(o1.getY() < o2.getY()){
                return -1;
            } else {
                return 1;
            }
        }
    }

}
