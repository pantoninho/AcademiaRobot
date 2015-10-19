package robot.interfaces;

import robot.map.Cell;

/**
 * Created by pedroantoninho on 15/10/15.
 */
public interface Pickable {

    public void pick(Cell cell);

    public void drop(Cell cell);
}
