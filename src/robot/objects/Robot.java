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
    private Direction direction = Direction.SOUTH;

    private Queue<Actions> moves;
    private int actionCounter;
    private int pocketSize = 2;
    private Deque<Pickable> pocket;
    private Timer timer;
    private Text actions;

    public Robot() {
        this(Game.getGrid().getRobotPosition());
    }

    public Robot(Position pos) {

        makeMovable(pos);
        moves = new LinkedList<>();
        pocket = new LinkedList<>();
        model = new Picture(pos.getX(),pos.getY(),"resources/robot_SOUTH_" + pocket.size() + ".png");

        //resizeImage();
        model.draw();
        addText();
        start();
    }

    private void makeMovable(Position pos) {
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

    public void pickJar(){
        moves.add(Actions.PICK);
    }

    public void dropJar() {
        moves.add(Actions.DROP);
    }

    private void start()  {

        //WORKAROUND

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

        updateModel();
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
        pos.update();
    }

    private void pick() {
        actionCounter++;

        Cell cell = pos.getPos().getCellOnGrid();
        GameObject obj;

        cell.resetObjIterator();
        while (cell.objectIterator().hasNext()) {

            if ((obj = cell.objectIterator().next()) instanceof Pickable) {

                if (pocket.size() < pocketSize) {
                    pocket.push((Pickable)obj);
                    ((Pickable)obj).pick(cell);
                } else {
                    System.out.println("You've got no space to pick another jar.");
                }
            }
        }
    }

    private void drop() {
        actionCounter++;

        if (pocket.isEmpty()) {
            System.out.println("You've got no jars to drop");
            return;
        }

        pocket.pop().drop(pos.getPos().getCellOnGrid());


        model.delete();
        resizeImage();
        model.draw();
    }

    private void resizeImage() {

        int xGrowFactor = (pos.getPos().getGrid().getCellSize() - model.getWidth())/2;
        int yGrowFactor = (pos.getPos().getGrid().getCellSize() - model.getHeight())/2;

        model.grow(xGrowFactor, yGrowFactor);
        model.translate(xGrowFactor, yGrowFactor);
    }

    private void addText() {

        int gridHeight = pos.getPos().getGrid().getHeight();

        actions = new Text(10, gridHeight + 10, "Number of actions: " + actionCounter + " | Items in pocket: " + pocket.size());
        actions.setColor(Color.BLACK);
        actions.draw();


    }

    private void updateText(){
        actions.setText("Number of actions: " + actionCounter + " | Items in pocket: " + pocket.size());
    }

    private void updateModel() {
        model.load("resources/robot_" + direction.toString() + "_" + pocket.size() + ".png");
    }

    private class MoveLoop extends TimerTask {

        public MoveLoop() {}

        @Override
        public void run() {

            if (moves.isEmpty()) {
                return;
            }

            System.out.println(moves.peek());

            switch (moves.poll()) {
                case MOVE:
                    move();
                    updateText();
                    break;
                case TURN:
                    turn();
                    updateModel();
                    break;
                case PICK:
                    pick();
                    updateText();
                    updateModel();
                    break;
                case DROP:
                    drop();
                    updateText();
                    updateModel();

                    if (pos.getPos().getGrid().allJarsOnMarks()) {
                        System.out.println("DONE");
                        timer.cancel();
                    }
                    break;
            }
        }
    }
}