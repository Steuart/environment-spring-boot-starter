package cc.jooylife.environment;

import org.springframework.boot.logging.DeferredLog;

public class EnvHelper {

    private static final DeferredLog LOGGER = new DeferredLog();

    /**
     * Get the config path
     */
    public static String getConfigPath() {
        String customPath = getCustomPath();
        if (customPath != null) {
            return customPath;
        }
        return getDefaultPath();
    }

    /**
     * Get the custom path from the system property
     */
    public static String getCustomPath() {
        return System.getProperty("env.config");
    }

    /**
     * Get the default path from the system property
     */
    public static String getDefaultPath() {
        return System.getProperty("user.home") + java.io.File.separator + ".env" + java.io.File.separator + "config.properties";
    }

    public static void info(Object content) {
        LOGGER.info(content);
    }

    public static void info(Object content, Throwable t) {
        LOGGER.info(content, t);
    }

    public static void error(Object content) {
        LOGGER.error(content);
    }

    public static void error(Object content, Throwable t) {
        LOGGER.error(content, t);
    }

    public static void replayTo(Class<?> clazz) {
        LOGGER.replayTo(clazz);
    }
}
