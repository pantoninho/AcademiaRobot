package robot.objects;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import robot.map.Cell;
import robot.map.Grid;
import robot.map.Position;

/**
 * Created by pedroantoninho on 15/10/15.
 */
public class Bean extends Cell implements Pickable {


    public Bean(Position pos, Grid grid) {
        super(pos, grid.getCellSize());

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
