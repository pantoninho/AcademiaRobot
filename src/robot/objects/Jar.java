package robot.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import robot.interfaces.Pickable;
import robot.map.Cell;
import robot.map.Position;

public class Jar extends GameObject implements Pickable {

    public Jar() {
    }

    public Jar(Position pos) {
        x = pos.randomizeX();
        y = pos.randomizeY();

    }

    @Override
    public void createObject(Position pos) {
        obj = new Picture(x = pos.randomizeX(),y = pos.randomizeY(),"resources/jar_1.png");
    }


    @Override
    public void pick(Cell cell) {
        cell.removeObject(this);
        delete();
    }

    @Override
    public void drop(Cell cell) {

        System.out.println("HERE");
        cell.resetObjIterator();

        while(cell.objectIterator().hasNext()) {
            GameObject o;
            if ((o = cell.objectIterator().next()) instanceof Mark) {
                ((Mark)o).incJarCounter();
            }
        }

        cell.addObject(this);
    }
}
