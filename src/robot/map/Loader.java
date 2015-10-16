package robot.map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by cadet on 15/10/15.
 */
public class Loader {



    public static String loadMap() {


        FileReader reader = null;
        try {
            reader = new FileReader("resources/map.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        BufferedReader bReader = new BufferedReader(reader);

        String line;
        String result = "";

        try {
            while((line = bReader.readLine()) != null) {
                result += line + "\n";
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;


    }
}
