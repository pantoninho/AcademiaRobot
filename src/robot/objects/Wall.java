package robot.objects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import robot.map.Position;

/**
 * Created by cadet on 15/10/15.
 */
public class Wall extends Cell {


    public Wall(Position pos) {
        super(pos);
    }

    @Override
    public void draw() {
        ((Rectangle)object).setColor(Color.BLACK);
        ((Rectangle)object).fill();
    }
}
