package robot.objects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import robot.interfaces.Pickable;

public class Bean extends GameObject implements Pickable {


    public Bean() {

    }

    @Override
    public void draw() {

        ((Rectangle) obj).setColor(Color.GRAY);
        ((Rectangle) obj).fill();
    }

    @Override
    public void pick() {
        delete();

    }

    @Override
    public void drop() {

    }
}
