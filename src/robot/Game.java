package robot;

import robot.map.Grid;

/**
 * Created by cadet on 15/10/15.
 */
public class Game {

    public static Grid grid = new Grid(500,500,75);

    public static Grid getGrid() {
        return grid;
    }



}
