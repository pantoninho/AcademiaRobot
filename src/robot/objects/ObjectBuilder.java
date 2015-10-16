package robot.objects;


import robot.map.Position;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ObjectBuilder {

    private String type;
    private Map<Character, String> cellTypes;
    private Position pos;

    public ObjectBuilder() {
        cellTypes = new HashMap<>();

        cellTypes.put('#', "robot.objects.Wall");
        cellTypes.put('*', "robot.objects.Jar");
        cellTypes.put('x', "robot.objects.Mark");

    }

    public ObjectBuilder setPos(Position pos) {
        this.pos = pos;
        return this;
    }

    public ObjectBuilder setType(char ch) {
        type = cellTypes.get(ch);
        return this;
    }

    public GameObject createObject() {
        Class objClass;

        try {
            objClass = Class.forName(type);
            Constructor ctor = objClass.getConstructor(Position.class);

            return (GameObject) ctor.newInstance(pos);

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println("Wrong class type maybe. Type: " + type);
        }

        return null;
    }
}