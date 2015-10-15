package robot.objects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import robot.interfaces.Pickable;
import robot.map.Position;

/**
 * Created by pedroantoninho on 15/10/15.
 */
public class Bean extends Cell implements Pickable {


    public Bean(Position pos) {
        super(pos);

    }

    @Override
    public void draw() {
        ((Rectangle)object).setColor(Color.GRAY);
        ((Rectangle)object).fill();
    }

    @Override
    public void pick() {

        delete();

    }

    @Override
    public void drop() {

    }
}
