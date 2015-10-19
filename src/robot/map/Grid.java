package robot.map;

import robot.objects.*;

import java.util.*;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Grid {

    private int cols = 0;
    private int rows = 0;
    private int jarCounter = 0;
    private int cellSize;

    private List<Cell> cells;


    public Grid(int cellSize) {

        this.cellSize = cellSize;
        cells = new LinkedList<>();

        loadMap();
        countJars();
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

    public int getWidth() {
        return cols * cellSize;
    }

    public int getHeight() {
        return rows * cellSize;
    }

    public Cell getCell(Position pos) {

        int i = cols * pos.getRow() + pos.getCol();
        return cells.get(i);
    }

    public List<Cell> getCells() {
        return cells;
    }

    private void countJars() {

        for (Cell c : cells) {
            c.resetObjIterator();

            while (c.objectIterator().hasNext()) {
                if (c.objectIterator().next() instanceof Jar) {
                    jarCounter++;
                }
            }
        }
    }

    public boolean allJarsOnMarks() {

        int jars = 0;
        GameObject obj = null;

        for (Cell c : cells) {
            c.resetObjIterator();

            while (c.objectIterator().hasNext()) {
                if ((obj = c.objectIterator().next()) instanceof Mark) {
                    jars += ((Mark)obj).getJarCounter();
                }
            }
        }

        if (jars >= jarCounter) {
            return true;
        }
        return false;
    }

    private void loadMap() {
        char ch;
        CellBuilder cb = new CellBuilder();
        String mapLine;
        Loader.loadMap();

        while ((mapLine = Loader.loadLine()) != null) {

            cols = mapLine.length();

            for (int i = 0; i < cols; i++) {

                Cell nextCell = cb.nextCell();
                cells.add(nextCell);
                nextCell.draw();

                if ((ch = mapLine.charAt(i)) != '_') {
                    if (ch == 'p') {
                        Robot robot = new Robot(new Position(i % cols, rows, this));
                    } else {
                        GameObject obj = new ObjectBuilder()
                                .setPos(nextCell.getPos())
                                .setType(ch)
                                .createObject();

                        nextCell.addObject(obj);
                    }
                }
                nextCell.drawObjects();
            }

            cb.nextRow();
            rows++;
        }


    }

    private class CellBuilder {

        private int col = 0;
        private int row = 0;

        private Cell nextCell() {
            Cell cell = new Cell(new Position(col, row, Grid.this));
            col++;

            return cell;
        }

        private void nextRow() {
            col = 0;
            row++;
        }
    }

}
