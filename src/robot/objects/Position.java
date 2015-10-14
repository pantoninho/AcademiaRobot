package robot.objects;

/**
 * Created by pedroantoninho on 14/10/15.
 */
public class Position {

    private int col;
    private int row;

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }


    public Position getPos() {
        return this;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
