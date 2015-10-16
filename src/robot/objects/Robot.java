package robot.objects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
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
    private Deque<Pickable> pocket;
    private Timer timer;
    private Text actions;

    public Robot() {
        this(new Position(3, 4, Game.getGrid()));
    }

    public Robot(Position pos) {

        model = new Picture(pos.getX(),pos.getY(),"resources/robot_NORTH.png");
        makeMovable(pos);
        moves = new LinkedList<>();

        pocket = new LinkedList<>();

        actions = new Text(10,10,"Number of actions: " + actionCounter + " | Items in pocket: " + pocket.size());
        actions.setColor(Color.WHITE);
        actions.draw();

        resizeImage();
        model.draw();
        start();
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

    public void pickBean(){
        moves.add(Actions.PICK);
    }

    public void dropBean() {
        moves.add(Actions.DROP);
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

        System.out.println("MOVING TO " + pos);

        actionCounter++;
        model.translate(pos.dX(), pos.dY());
        pos.update();
    }

    private void pick() {

        actionCounter++;
        Cell cell = pos.getGrid().getCell(pos.getPos());
        Pickable obj;

        if ( cell.hasObject() &&
                cell.getObject() instanceof Pickable) {

            if(pocket.size() < pocketSize){
                pocket.push(cell.pickObject());
            } else {
                System.out.println("You've got no space to pick another bean");
            }
        } else {
            System.out.println("There isn't a pickable object in this cell");
        }
    }

    private void drop() {

        actionCounter++;
        pos.getGrid()
                .getCell(pos.getPos())
                .dropPickable(pocket.pop());
        model.delete();
        resizeImage();
        model.draw();
    }

    private void resizeImage() {

        int xGrowFactor = (pos.getGrid().getCellSize() - model.getWidth())/2;
        int yGrowFactor = (pos.getGrid().getCellSize() - model.getHeight())/2;

        model.grow(xGrowFactor, yGrowFactor);
        model.translate(xGrowFactor, yGrowFactor);
    }

    private void updateText(){
        actions.setText("Number of actions: " + actionCounter + " | Items in pocket: " + pocket.size());
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
                case DROP:
                    drop();
                    break;
            }

            updateText();
        }
    }
}