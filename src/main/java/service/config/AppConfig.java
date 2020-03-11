package service.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration
@ComponentScan(basePackages = "service")
public class AppConfig {

    @Bean
    public ServiceApplicationListener getListener() {
        return new ServiceApplicationListener();
    }

    private static class ServiceApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {

        }
    }
}
