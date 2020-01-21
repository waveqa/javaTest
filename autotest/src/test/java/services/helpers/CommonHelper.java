package services.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonHelper {

    public static String getDataFromProperty(String propName) {
        try (InputStream input = new FileInputStream(".\\src\\test\\resources\\config.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            return prop.getProperty(propName);
        } catch (IOException ex) {
            ex.printStackTrace();
            return "1";
        }
    }
}
