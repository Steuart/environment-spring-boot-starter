package cc.jooylife.environment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

public class EnvironmentProcessor implements EnvironmentPostProcessor, ApplicationListener<ApplicationEvent> {

    private static final DeferredLog LOGGER = new DeferredLog();

    private final static String DEFAULT_ENV_CONFIG = getDefaultEnvConfig();

    private final PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();

    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String envConfig = environment.getProperty("-Denv.config");
        if (envConfig == null) {
            LOGGER.info("Use default env config:"+DEFAULT_ENV_CONFIG);
            envConfig = DEFAULT_ENV_CONFIG;
        }
        Resource resource = new FileSystemResource(envConfig);
        if (!resource.exists()) {
            LOGGER.info("No custom environment configuration found");
            return;
        }
        PropertySource<?> propertySource = null;
        try {
            propertySource = loader.load("custom-environment", resource).get(0);
        } catch (IOException e) {
            LOGGER.error("load environment config error:"+envConfig, e);
        }
        if (propertySource != null) {
            environment.getPropertySources().addFirst(propertySource);
        }
    }

    public void onApplicationEvent(ApplicationEvent event) {
        LOGGER.replayTo(EnvironmentProcessor.class);
    }

    private static String getDefaultEnvConfig() {
        return System.getProperty("user.home") + File.separator + ".env" + File.separator + "config.properties";
    }
}
