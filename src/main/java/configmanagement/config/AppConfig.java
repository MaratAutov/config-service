package configmanagement.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import configmanagement.model.utils.DataUtils;

import static org.modelmapper.config.Configuration.AccessLevel.PUBLIC;

@Configuration
@ComponentScan(basePackages = "configmanagement")
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setAmbiguityIgnored(true)
                .setFieldAccessLevel(PUBLIC);
        return mapper;
    }

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
