package robot.objects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by cadet on 15/10/15.
 */
public class Wall extends GameObject {


    public Wall() {
    }

    @Override
    public void draw() {
        ((Rectangle) obj).setColor(Color.BLACK);
        ((Rectangle) obj).fill();
    }

    @Override
    public String toString() {
        return "IM A WALL";
    }
}
