import robot.objects.Robot;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Main {


    public static void main(String[] args) {


        Robot jose = new Robot();

        jose.turnLeft();
        jose.pickJar();
        jose.moveForward();
        jose.pickJar();
        jose.moveForward();
        jose.dropJar();
        jose.moveForward();
        jose.pickJar();
        jose.turnLeft();
        jose.turnLeft();
        jose.moveForward();
        jose.dropJar();
        jose.dropJar();

        jose.moveForward();
    }
}
