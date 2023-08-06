package cc.jooylife.environment;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class EnvListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        EnvHelper.replayTo(EnvListener.class);
    }
}
