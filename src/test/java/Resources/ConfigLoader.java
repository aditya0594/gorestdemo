package Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties = new Properties();

    public static void load(String env) throws IOException {
        FileInputStream fis = new FileInputStream("src/test/java/Resources/"+env+".properties");
                //"Resources/" + env + ".properties
        properties.load(fis);
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
