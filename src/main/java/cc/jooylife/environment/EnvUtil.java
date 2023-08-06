package cc.jooylife.environment;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class EnvUtil {

    private static volatile Properties properties;

    /**
     * Get value from properties file
     */
    public static String getValue(String key) {
        String configPath = EnvHelper.getConfigPath();
        EnvHelper.info("Environment config path: " + configPath);
        Resource resource = new FileSystemResource(configPath);
        if (!resource.exists()) {
            EnvHelper.info("No custom environment configuration found");
            return null;
        }
        Properties properties = getProperties(resource);
        if (properties == null) {
            return null;
        }
        return properties.getProperty(key);
    }

    /**
     * 使用单例模式获取properties对象
     */
    private static Properties getProperties(Resource resource) {
        if (properties !=null) {
            return properties;
        }
        synchronized (EnvUtil.class) {
            if (properties == null) {
                try {
                    properties = PropertiesLoaderUtils.loadProperties(resource);
                } catch (IOException e) {
                    EnvHelper.error("load environment config error.", e);
                }
            }
        }
        return properties;
    }

}
