package robot.map;

import robot.objects.Cell;
import robot.objects.Wall;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Grid {

    private int cols;
    private int rows;
    private int cellSize;

    private List<Cell> cells;


    public Grid(int width, int height, int cellSize) {

        this.cols = width / cellSize;
        this.rows = height / cellSize;
        this.cellSize = cellSize;

        createCells();
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

        String loadedMap = Loader.loadMap();
        int i = 0;

        cells = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                if (loadedMap.charAt(i) == ' ') {
                    cells.add( new Cell(new Position(c,r,this)));
                } else {
                    cells.add( new Wall(new Position(c,r,this)));
                }
                i++;
            }
        }
    }

    private void drawCells() {

        for (Cell c : cells) {
            c.draw();
        }
    }
}
