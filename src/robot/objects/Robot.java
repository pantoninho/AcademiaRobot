package robot.objects;

import robot.map.Grid;
import robot.map.Cell;
import org.academiadecodigo.simplegraphics.graphics.Picture;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Robot extends Cell implements Movable {

    private Direction direction = Direction.NORTH;
    private Picture object;

    private List<Actions> moves;
    private int actionCounter;

    private MovablePosition pos;
    private Timer timer;


    public Robot(Position pos, Grid grid) {
        super(pos, grid.getCellSize());

        object = (Picture) object;
        this.pos = new MovablePosition(pos,grid);

        object.translate(pos.getCol()*cellSize,pos.getRow()*cellSize);
        moves = new LinkedList<>();
    }


    @Override
    public void createObject() {

        object = new Picture("resources/robot_NORTH.png");
        object.grow((cellSize - object.getWidth()) / 2, (cellSize - object.getHeight()) / 2);
        object.translate(-object.getX(), -object.getY());
        object.draw();
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

    private void turn() {

        if (direction.ordinal() == Direction.values().length - 1) {
            direction = Direction.values()[0];
        } else {
            direction = Direction.values()[direction.ordinal()+1];
        }

        object.load("resources/robot_" + direction.toString() + ".png");
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

        object.translate(pos.getX()-object.getX(), pos.getY()-object.getY());
    }

    public void start()  {

        timer = new Timer();
        timer.schedule(new MoveLoop(), 0, 1000);
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