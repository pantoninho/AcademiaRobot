import robot.map.Grid;
import robot.objects.Bean;
import robot.map.Position;
import robot.objects.Robot;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Main {


    public static void main(String[] args) {

        Grid grid = new Grid(500,500,75);
        Bean newBean = new Bean(new Position(1,2,grid));
        Robot robot = new Robot(new Position(0,0,grid));

        robot.start();

        robot.turnLeft();
        robot.moveForward();
        robot.moveForward();
        robot.moveForward();


    }
}
