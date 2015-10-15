package robot.map;

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

    private void createCells() {

        cells = new ArrayList<>();

        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                cells.add( new Cell(new Position(c,r),cellSize) );
            }
        }
    }

    private void drawCells() {

        for (Cell c : cells) {
            c.draw();
            System.out.println(c.pos);
        }
    }
}
