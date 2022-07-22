package timekeeping.my.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * The Properties Util class provides access to instantiated
 * properties in .properties file.
 **/
public final class PropertiesUtil {

    private static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);
    private static final Properties properties;
    private static final String PATH = "application.properties";

    static {
        properties = new Properties();
        loadProperties();
    }

    private PropertiesUtil() {

    }

    /**
     * Gets all properties.
     * @return Properties the properties
     */
    public static Properties getProperties() {
        return properties;
    }

    /**
     * Gets property.
     * @param key the key
     * @return String the property
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    private static void loadProperties() {
        try(var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(PATH)) {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("Fail to load properties. Perhaps wrong path ==> " + PATH);
            throw new RuntimeException("Cannot obtain properties..");
        }
    }
}
