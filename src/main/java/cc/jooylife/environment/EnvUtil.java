package cc.jooylife.environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class EnvUtil {

    private static volatile Properties properties;

    private static final Logger LOGGER = LoggerFactory.getLogger(EnvUtil.class);

    /**
     * Get value from properties file
     * @return the value
     * @param key the env key
     */
    public static String getValue(String key) {
        Properties properties = getProperties();
        if (properties == null) {
            return null;
        }
        return properties.getProperty(key);
    }

    /**
     * 使用单例模式获取properties对象
     * @return properties对象
     */
    private static Properties getProperties() {
        if (properties !=null) {
            return properties;
        }
        synchronized (EnvUtil.class) {
            if (properties!=null) {
                return properties;
            }
            String configPath = EnvHelper.getConfigPath();
            LOGGER.info("Environment config path: " + configPath);
            Resource resource = new FileSystemResource(configPath);
            if (!resource.exists()) {
                LOGGER.info("No custom environment configuration found");
                return null;
            }
            try {
                properties = PropertiesLoaderUtils.loadProperties(resource);
            } catch (IOException e) {
                LOGGER.error("load environment config error.", e);
            }
        }
        return properties;
    }

}
