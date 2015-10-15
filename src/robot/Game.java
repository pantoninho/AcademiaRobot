package robot;

import robot.interfaces.Pickable;
import robot.map.Grid;
import robot.map.Position;
import robot.objects.Bean;
import robot.objects.Cell;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cadet on 15/10/15.
 */
public class Game {

    private static Grid grid = new Grid(500,500,60);
    private static List<Cell> beans = createBeans();


    public static Grid getGrid() {
        return grid;
    }


    public static List<Cell> createBeans(){
        beans = new LinkedList<>();
        Bean firstBean = new Bean(new Position(3,3,grid));
        firstBean.draw();

        Bean secondBean = new Bean(new Position(4,5,grid));
        secondBean.draw();

        beans.add(secondBean);
        beans.add(firstBean);
        return beans;
    }

    public static Pickable hasPickable(Position pos) {

        Iterator<Cell> beanIter = beans.iterator();

        while (beanIter.hasNext()) {
            Cell obj = beanIter.next();

            if (pos.equals(obj.getPos()))  {
                    if(obj instanceof Pickable) {
                        return (Pickable)obj;
                    }
                }
            }

        System.out.println("There's no bean to pick here!");
        return null;
    }

}
