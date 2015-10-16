package robot.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import robot.map.Position;

/**
 * Created by cadet on 15/10/15.
 */
public class Wall extends GameObject {


    public Wall() {
    }

    public Wall(Position pos) {
        super(pos);
    }

    @Override
    public void createObject(Position pos) {

        int randomInt = (int) (Math.random()*3);
        obj = new Picture(x,y,"resources/rock_" + randomInt + ".png");
    }

    @Override
    public void draw() {
        obj.draw();
    }

    @Override
    public String toString() {
        return "IM A WALL";
    }
}
