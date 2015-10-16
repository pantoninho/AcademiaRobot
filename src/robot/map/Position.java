package robot.map;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Position {

    private int col;
    private int row;

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

    void nextCol() {
        col++;
    }

    void previousCol() {
        col--;
    }

    void nextRow() {
        row++;
    }

    void previousRow() {
        row--;
    }

    void match(Position pos) {
        col = pos.col;
        row = pos.row;
    }

    void mirrorHoriz() {
        row = grid.getRows()-1 - row;
    }

    void mirrorVert(){
        col = grid.getCols()-1 - col;
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

    public Cell getCellOnGrid() {
        return grid.getCell(this);
    }

    @Override
    public String toString() {
        return "Position{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}
