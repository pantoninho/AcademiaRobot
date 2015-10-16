package robot.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import robot.interfaces.Pickable;
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
    public void addObj() {



    }


    @Override
    public void draw() {
        obj.draw();
    }

    @Override
    public void pick() {
        delete();

    }

    @Override
    public void drop() {

    }
}
