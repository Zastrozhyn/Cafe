package by.zastr.cafe.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationManager {
	private static final Logger logger = LogManager.getLogger();
	private static final Properties PROPERTIES = new Properties();
	private static final String PROPERTIES_NAME = "application.properties";
    private ConfigurationManager() {
    }
    static {
    	loadProperies();
    }
    public static String getProperty(String key) {
    	return PROPERTIES.getProperty(key);
    }
    private static void loadProperies() {
     	try (InputStream inputStream = ConfigurationManager.class.getClassLoader().getResourceAsStream(PROPERTIES_NAME)){
     		PROPERTIES.load(inputStream);
     	} catch (IOException e) {
			logger.log(Level.FATAL, "Error in loading config", e);
			throw new RuntimeException();
		}
    }

}
