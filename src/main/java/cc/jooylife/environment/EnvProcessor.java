package cc.jooylife.environment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class EnvProcessor implements EnvironmentPostProcessor {

    private final PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();

    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String envConfig = EnvHelper.getConfigPath();
        EnvHelper.info("Environment config path: " + envConfig);
        Resource resource = new FileSystemResource(envConfig);
        if (!resource.exists()) {
            EnvHelper.info("No custom environment configuration found");
            return;
        }
        PropertySource<?> propertySource = null;
        try {
            propertySource = loader.load("custom-environment", resource).get(0);
        } catch (IOException e) {
            EnvHelper.error("load environment config error:"+envConfig, e);
        }
        if (propertySource != null) {
            environment.getPropertySources().addFirst(propertySource);
        }
    }
}
