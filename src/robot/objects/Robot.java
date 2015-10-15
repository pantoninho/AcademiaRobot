package robot.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import robot.Game;
import robot.enums.Actions;
import robot.enums.Direction;
import robot.interfaces.Movable;
import robot.map.MovablePosition;
import robot.map.Position;

import java.util.*;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Robot extends Cell implements Movable {

    private Picture model;

    private MovablePosition pos;
    private Direction direction = Direction.NORTH;

    private Queue<Actions> moves;
    private int actionCounter;
    private Timer timer;

    public Robot() {
        this(new Position(2,2,Game.getGrid()));
    }

    public Robot(Position pos) {
        super(pos);
        this.pos = new MovablePosition(pos);

        model = (Picture) object;
        positionImage();
        draw();


        moves = new LinkedList<>();
        start();
    }

    public void pickBean(){
        moves.add(Actions.PICK);

    }

    @Override
    public void turnLeft() {
        moves.add(Actions.TURN);
    }

    @Override
    public void moveForward()  {
        actionCounter++;

        moves.add(Actions.MOVE);
    }


    private void start()  {

        //WATCH OUT OMG. WORKAROUND HACK MUDAR DEPOIS CUIDADO!!!one!1
        //MUDAR PARA MULTITHREAD DPS

        try {
            Thread.sleep(5000);
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
        System.out.println(pos);
        model.translate(pos.dX(), pos.dY());
    }

    private void pick() {
        Game.hasPickable(pos);
    }

    private void positionImage() {
        //model.grow( (cellSize - model.getWidth())/2, (cellSize - model.getHeight())/2);
        model.translate(pos.getCol() * cellSize, pos.getRow() * cellSize);
    }

    @Override
    public void createObject() {
        object = new Picture(0,0,"resources/robot_NORTH.png");
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