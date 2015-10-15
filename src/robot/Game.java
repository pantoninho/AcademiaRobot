package robot;

import robot.map.Grid;
import robot.map.Position;
import robot.objects.Bean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cadet on 15/10/15.
 */
public class Game {

    private static Grid grid = new Grid(500,500,75);
    public static List<Bean> beans = createBeans();


    public static Grid getGrid() {
        return grid;
    }


    public static List<Bean> createBeans(){
        beans = new LinkedList<>();
        Bean firstBean = new Bean(new Position(3,3,grid));
        firstBean.draw();

        beans.add(firstBean);
        return beans;
    }

}
