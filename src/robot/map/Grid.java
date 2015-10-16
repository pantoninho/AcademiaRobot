package robot.map;

import robot.objects.GameObject;
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


    public Grid(int cellSize) {

        this.cellSize = cellSize;
        cells = new LinkedList<>();

        loadMap();
        drawCells();
        drawObjects();
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

    public Cell getCell(Position pos) {

        System.out.println("POS ASKED " + pos);
        System.out.println("cols " + cols + "  row " + pos.getRow() + ". col " + pos.getCol());
        int i = cols * pos.getRow() + pos.getCol();
        System.out.println("INDEX "+ i);
        System.out.println("POS GIVEN " + cells.get(i).getPos());
        return cells.get(i);
    }

    public List<Cell> getCells() {
        return cells;
    }

    private void loadMap() {
        char ch;
        CellBuilder cb = new CellBuilder();
        String loadedMap = Loader.loadMap();

        int i;
        for (i = 0; i < loadedMap.length(); i++) {

            if ((ch = loadedMap.charAt(i)) == '\n') {
                rows++;
                cb.nextRow();
                continue;
            }

            Cell nextCell = cb.nextCell();
            cells.add(nextCell);

            if (ch != ' ') {
                GameObject obj = new ObjectBuilder()
                        .setType(ch)
                        .createObject();
                nextCell.addObject(obj);
            }
        }

        rows++;
        cols = (i / rows) + 1;
    }

    private void drawCells() {

        for (Cell c : cells) {
            c.draw();
        }
    }

    private void drawObjects() {
        for (Cell c : cells) {
            c.drawObjects();
        }
    }

    private class CellBuilder {

        private int col = 0;
        private int row = 0;

        private Cell nextCell() {
            Cell cell = new Cell(new Position(col,row,Grid.this));
            col++;

            return cell;
        }

        private void nextRow() {
            col = 0;
            row++;
        }
    }

}
