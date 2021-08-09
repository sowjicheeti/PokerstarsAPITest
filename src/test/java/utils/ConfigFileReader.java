package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private static Properties config;

    static {
        String path = "./src/test/resources/properties/" + System.getProperty("env","qa") + ".properties";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        config = new Properties();
        try {
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public static String getProperty(String key){
        return config.getProperty(key);
}

}
