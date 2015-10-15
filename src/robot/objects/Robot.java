package robot.objects;

import robot.map.Grid;
import robot.map.Cell;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import robot.map.MovablePosition;
import robot.map.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Robot extends Cell implements Movable {

    private Direction direction = Direction.NORTH;
    private Picture model;

    private List<Actions> moves;
    private int actionCounter;

    private MovablePosition pos;
    private Timer timer;


    public Robot(Position pos, Grid grid) {
        super(pos, grid.getCellSize());
        this.pos = new MovablePosition(pos,grid);

        model = (Picture) object;
        positionImage();
        draw();

        System.out.println(pos);
        moves = new LinkedList<>();
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

    public void start()  {

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
        System.out.println(pos.dX() + " " + pos.dY());
        model.translate(pos.dX(),pos.dY());
    }

    private void positionImage() {
        model.grow( (cellSize - model.getWidth())/2, (cellSize - model.getHeight())/2);
        model.translate(pos.getCol() * cellSize, pos.getRow() * cellSize);
    }

    @Override
    public void createObject() {
        object = new Picture(0,0,"resources/robot_NORTH.png");
    }

    private class MoveLoop extends TimerTask {

        private int index;

        public MoveLoop() {

        }

        @Override
        public void run() {

            System.out.println(moves.get(index));


            switch (moves.get(index)) {
                case MOVE:
                    move();
                    break;
                case TURN:
                    turn();
                    break;
            }


            if(index < moves.size() - 1) {
                index++;
            } else {
                timer.cancel();
            }
        }
    }
}