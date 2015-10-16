package robot.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import robot.Game;
import robot.enums.Actions;
import robot.enums.Direction;
import robot.interfaces.Movable;
import robot.interfaces.Pickable;
import robot.map.Cell;
import robot.map.MovablePosition;
import robot.map.Position;

import java.util.*;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Robot implements Movable {

    private Picture model;
    private MovablePosition pos;
    private Direction direction = Direction.NORTH;

    private Queue<Actions> moves;
    private int actionCounter;
    private int pocketSize = 2;
    private int beansInPocket = 0;
    private Timer timer;

    public Robot() {
        this(new Position(3, 4, Game.getGrid()));
    }

    public Robot(Position pos) {

        model = new Picture(pos.getX(),pos.getY(),"resources/robot_NORTH.png");
        makeMovable(pos);
        moves = new LinkedList<>();

        resizeImage();
        model.draw();

        start();
    }

    public void pickBean(){
        moves.add(Actions.PICK);
    }

    @Override
    public void makeMovable(Position pos) {
        this.pos = new MovablePosition(pos);
    }

    @Override
    public void turnLeft() {
        moves.add(Actions.TURN);
    }

    @Override
    public void moveForward()  {
        moves.add(Actions.MOVE);
    }


    private void start()  {

        //WATCH OUT OMG. WORKAROUND HACK MUDAR DEPOIS CUIDADO!!!one!1
        //MUDAR PARA MULTITHREAD DPS

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer = new Timer();
        timer.schedule(new MoveLoop(), 0, 1000);
    }

    private void turn() {

        if (direction.ordinal() == Direction.values().length - 1) {
            direction = Direction.values()[0];
        } else {
            direction = Direction.values()[direction.ordinal()+1];
        }

        model.load("resources/robot_" + direction.toString() + ".png");
    }

    private void move() {

        switch (direction) {
            case NORTH:
                pos.moveUp();
                break;
            case WEST:
                pos.moveLeft();
                break;
            case SOUTH:
                pos.moveDown();
                break;
            case EAST:
                pos.moveRight();
                break;
        }

        actionCounter++;
        model.translate(pos.dX(), pos.dY());
    }

    private void pick() {

        actionCounter++;
        Cell cell = pos.getGrid().getCell(pos);
        Pickable obj;

        if ( cell.hasObject() &&
                cell.getObject() instanceof Pickable) {

            if(beansInPocket < pocketSize){
                cell.pickObject();
                beansInPocket++;
            } else {
                System.out.println("You've got no space to pick another bean");
            }
        }
    }

    private void resizeImage() {

        int xGrowFactor = (pos.getGrid().getCellSize() - model.getWidth())/2;
        int yGrowFactor = (pos.getGrid().getCellSize() - model.getHeight())/2;

        model.grow(xGrowFactor,yGrowFactor);
        model.translate(xGrowFactor, yGrowFactor);
    }

    private class MoveLoop extends TimerTask {

        public MoveLoop() {

        }

        @Override
        public void run() {

            if (moves.isEmpty()) {
                return;
            }

            System.out.println(moves.peek());

            switch (moves.poll()) {
                case MOVE:
                    move();
                    break;
                case TURN:
                    turn();
                    break;
                case PICK:
                    pick();
                    break;
            }

        }
    }
}