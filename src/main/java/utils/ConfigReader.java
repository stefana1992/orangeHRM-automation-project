package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    // Holds loaded properties
    private static Properties properties;

    // Loads config.properties file
    public static void loadProperties() {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config.properties", e);
        }
    }

    // Gets value by key from loaded properties
    public static String getProperty(String key) {
        if (properties == null) {
            loadProperties(); // Load if not already loaded
        }
        return properties.getProperty(key);
    }
}
