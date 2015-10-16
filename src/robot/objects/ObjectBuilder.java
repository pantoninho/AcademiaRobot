package robot.objects;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ObjectBuilder {

    private String type;
    private Map<Character, String> cellTypes;

    public ObjectBuilder() {
        cellTypes = new HashMap<>();

        cellTypes.put('#', "robot.objects.Wall");
        cellTypes.put('*', "robot.objects.Bean");

    }

    public ObjectBuilder setType(char ch) {
        type = cellTypes.get(ch);
        return this;
    }

    public GameObject createObject() {
        Class objClass;

        try {
            objClass = Class.forName(type);
            Constructor ctor = objClass.getConstructor();

            return (GameObject) ctor.newInstance();

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println("Wrong class type maybe. Type: " + type);
        }

        return null;
    }
}