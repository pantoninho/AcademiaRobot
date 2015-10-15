package robot.map;

import robot.interfaces.Pickable;
import robot.objects.Cell;
import robot.objects.CellBuilder;
import robot.objects.Wall;

import java.util.*;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Grid {

    private int cols;
    private int rows;
    private int cellSize;

    private List<Cell> cells;
    private Map<Character, String> cellTypes;


    public Grid(int width, int height, int cellSize) {

        this.cols = width / cellSize;
        this.rows = height / cellSize;
        this.cellSize = cellSize;

        makeCellMap();
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

    private void makeCellMap() {
        cellTypes = new HashMap<>();

        cellTypes.put(' ', "Cell");
        cellTypes.put('*', "Beans");
        cellTypes.put('#', "Wall");
    }

    private void createCells() {

        String loadedMap = Loader.loadMap();
        System.out.println(loadedMap);
        int i = 0;

        cells = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                char cellCh = loadedMap.charAt(i++);
                Position pos = new Position(c,r,this);

                Cell cell = new CellBuilder().setPos(pos).setType(cellCh).createCell();
                cells.add(cell);
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
            Cell obj = cellIter.next();

            if (pos.equals(obj.getPos())) {
                if (obj instanceof Pickable) {
                    return (Pickable) obj;
                }
            }
        }

        System.out.println("There's no bean to pick here!");
        return null;
    }
}
