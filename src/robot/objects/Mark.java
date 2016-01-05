package robot.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import robot.map.Position;

/**
 * Created by cadet on 16/10/15.
 */
public class Mark extends GameObject {

    private int jarCounter = 0;

    public Mark() {
    }

    public Mark(Position pos) {
        super(pos);
        jarCounter = 0;
    }

    public void incJarCounter() {
        jarCounter++;

        System.out.println(jarCounter + " jars in target");
    }

    public int getJarCounter() {
        return jarCounter;
    }



    @Override
    public void createObject(Position pos) {
        obj = new Picture(x,y,"resources/mark.png");
    }
}
