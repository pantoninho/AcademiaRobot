package robot.map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by cadet on 15/10/15.
 */
public class Loader {

    private static BufferedReader bReader;


    public static void loadMap() {
        FileReader reader = null;
        try {
            reader = new FileReader("resources/map.txt");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }


        bReader = new BufferedReader(reader);
    }

    public static String loadLine() {

        String line = null;

        try {
            if ((line = bReader.readLine()) == null) {
                bReader.close();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return line;
    }

}
