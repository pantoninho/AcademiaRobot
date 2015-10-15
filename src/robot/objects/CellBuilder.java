package robot.objects;

import robot.map.Position;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CellBuilder {

    private Position pos;
    private String type;
    private Map<Character, String> cellTypes;

    public CellBuilder() {
        cellTypes = new HashMap<>();

        cellTypes.put(' ', "robot.objects.Cell");
        cellTypes.put('#', "robot.objects.Wall");
        cellTypes.put('*', "robot.objects.Bean");

    }

    public CellBuilder setPos(Position pos) {
        this.pos = pos;
        return this;
    }

    public CellBuilder setType(char ch) {

        type = cellTypes.get(ch);
        return this;
    }

    public Cell createCell() {


        try {

            Class cellClass = Class.forName(type);
            Constructor ctor = cellClass.getConstructor(Position.class);

            return (Cell) ctor.newInstance(pos);

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println("Wrong class type maybe. Type: " + type);
        }

        return new Cell(pos);
    }
}