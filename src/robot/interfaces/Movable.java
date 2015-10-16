package robot.interfaces;

import robot.map.Position;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public interface Movable {

    void makeMovable(Position pos);

    public void turnLeft();

    public void moveForward();
}
