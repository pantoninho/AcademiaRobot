package robot.map;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Position {

    protected int col;
    protected int row;

    private Grid grid;

    public Position(int col, int row, Grid grid) {
        this.col = col;
        this.row = row;

        this.grid = grid;
    }



    public boolean equals(Position pos) {

        if(pos.getCol() == col && pos.getRow() == row){
            return true;
        }
        return false;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getX() {
        return col * grid.getCellSize();
    }

    public int getY() {
        return row * grid.getCellSize();
    }

    public Grid getGrid() {
        return grid;
    }

    @Override
    public String toString() {
        return "Position{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}
