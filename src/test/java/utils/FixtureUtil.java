package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class FixtureUtil {

    public static JSONObject getFixture() {

        FileReader fileReader;
        JSONObject fixture;
        try {
            fileReader = new FileReader(new File("src/test/resources/jsonFileInput/SampleFixture.json"));
            JSONParser jparser = new JSONParser();
            Object obj = jparser.parse(fileReader);
            fixture = (JSONObject) (obj);
            return fixture;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}



