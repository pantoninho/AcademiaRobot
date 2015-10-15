import robot.objects.Robot;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Main {


    public static void main(String[] args) {


        Robot jose = new Robot();

        jose.turnLeft();
        jose.turnLeft();
        jose.moveForward();
        jose.turnLeft();
        jose.moveForward();
        jose.pickBean();


    }
}
