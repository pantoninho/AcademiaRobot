import robot.map.Grid;
import robot.objects.Position;
import robot.objects.Robot;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Main {


    public static void main(String[] args) {

        Grid grid = new Grid(500,500,75);
        Robot robot = new Robot(new Position(0,0),grid);
        Robot catarina = new Robot(new Position(5,5),grid);


        robot.turnLeft();
        robot.turnLeft();
        robot.moveForward();
        robot.moveForward();
        robot.moveForward();
        robot.turnLeft();
        robot.turnLeft();
        robot.turnLeft();
        catarina.turnLeft();
        robot.moveForward();
        robot.moveForward();


        catarina.turnLeft();
        catarina.turnLeft();
        catarina.moveForward();
        catarina.moveForward();
        catarina.moveForward();

        catarina.start();


        robot.start();
    }
}
