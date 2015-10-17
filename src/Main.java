import robot.Game;
import robot.map.Position;
import robot.objects.Robot;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Main {


    public static void main(String[] args) {

        Robot ricardo = new Robot();

        ricardo.moveForward();
        ricardo.moveForward();
        ricardo.moveForward();
        ricardo.moveForward();
        ricardo.moveForward();
        ricardo.pickJar();
        ricardo.turnLeft();
        ricardo.turnLeft();
        ricardo.dropJar();
        ricardo.moveForward();
        ricardo.moveForward();
        ricardo.turnLeft();
        ricardo.moveForward();
        ricardo.moveForward();
        ricardo.moveForward();
        ricardo.turnLeft();
        ricardo.turnLeft();
        ricardo.moveForward();
        ricardo.moveForward();
        ricardo.moveForward();
        ricardo.moveForward();

    }
}