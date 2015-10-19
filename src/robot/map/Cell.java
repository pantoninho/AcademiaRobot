package robot.map;

import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import robot.interfaces.Pickable;
import robot.objects.GameObject;

import java.util.*;

public class Cell {

    private Position pos;
    protected Shape cell;
    private List<GameObject> objects;
    private Iterator<GameObject> objectIterator;
    protected int cellSize;

    public Cell() {
    }

    public Cell(Position pos) {
        this.pos = pos;
        cellSize = pos.getGrid().getCellSize();

        createCell();
        objects = new LinkedList<>();
        objectIterator = objects.iterator();
    }

    public Position getPos() {
        return pos;
    }

    public void addObject(GameObject object) {
        object.createObject(pos);
        objects.add(object);

        drawPickables();
    }

    public boolean hasObject() {
        if (objects.size() > 0) {
            return true;
        }
        return false;
    }

    public Iterator<GameObject> objectIterator() {
        return objectIterator;
    }

    public void resetObjIterator() {
        objectIterator = objects.iterator();
    }

    public void removeObject (GameObject object) {
        objects.remove(object);

        drawPickables();
    }

    public void drawPickables() {

        Collections.sort(objects,new ItemSorter());

        for (GameObject o : objects) {
            if (o instanceof Pickable) {
                o.delete();
                o.draw();
            }
        }
    }

    public void drawObjects() {

        for (GameObject o : objects) {
            o.draw();
        }
    }

    public void createCell() {
        cell = new Picture(pos.getX(),pos.getY(),getRandomImg());
    }

    public void draw() {
        cell.draw();
    }

    public void delete() {
        cell.delete();
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
            } else if (o1.getY() > o2.getY()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
