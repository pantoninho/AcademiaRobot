package robot.map;

import robot.interfaces.Pickable;
import robot.objects.Cell;
import robot.objects.ObjectBuilder;

import java.util.*;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Grid {

    private int cols;
    private int rows;
    private int cellSize;

    private List<Cell> cells;
    private List<Cell> gameObjects;


    public Grid(int width, int height, int cellSize) {

        this.cols = width / cellSize;
        this.rows = height / cellSize;
        this.cellSize = cellSize;

        createCells();
        createObjects();
        drawCells();
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public List<Cell> getCells() {
        return cells;
    }

    private void createCells() {

        cells = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                Position pos = new Position(c, r, this);
                cells.add(new Cell(pos));
            }
        }
    }

    private void createObjects() {

        String loadedMap = Loader.loadMap();

        for (int i = 0; i < loadedMap.length(); i++) {

            if (loadedMap.charAt(i) != ' ') {
                Cell object = new ObjectBuilder()
                        .setPos(cells.get(i).getPos())
                        .setType(loadedMap.charAt(i))
                        .createObject();

                cells.get(i).addObject(object);
            }
        }


    }

    private void drawCells() {

        for (Cell c : cells) {
            c.draw();
        }
    }

    public Pickable hasPickable(Position pos) {

        Iterator<Cell> cellIter = cells.iterator();

        while (cellIter.hasNext()) {

            Cell cell = cellIter.next();

            if (pos.equals(cell.getPos())
                    && cell.hasObject()) {

                if (cell.getObject() instanceof Pickable) {
                    return (Pickable) cell.getObject();
                }
            }
        }

        System.out.println("There's no bean to pick here!");
        return null;
    }
}
