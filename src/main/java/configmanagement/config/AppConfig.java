package configmanagement.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import configmanagement.model.utils.DataUtils;

@Configuration
@ComponentScan(basePackages = "configmanagement.service")
public class AppConfig {

    @Bean
    public ServiceApplicationListener getListener() {
        return new ServiceApplicationListener();
    }

    private static class ServiceApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            DataUtils dataUtils = event.getApplicationContext().getBean(DataUtils.class);
            dataUtils.initTables();
        }
    }
}
