package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private static final Properties properties = new Properties();

    private static Properties getProperties(String filepath) throws IOException {
        File file = new File(filepath);
        FileInputStream fileInputStream = new FileInputStream(file);
        properties.load(fileInputStream);
        fileInputStream.close();
        return properties;
    }

    public static String getCredentials(String filepath, String propertyName) throws IOException {
        return getProperties(filepath).getProperty(propertyName);
    }
}
