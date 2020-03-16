package configmanagement.services;

import configmanagement.domain.Parameter;
import configmanagement.domain.Subscriber;
import configmanagement.domain.Subscription;
import configmanagement.model.utils.DataUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParameterService {

    private static final Logger log = LogManager.getLogger(ParameterService.class);
    private final DataUtils dataUtils;

    @Autowired
    public ParameterService(DataUtils dataUtils) {
        this.dataUtils = dataUtils;
    }

    public Parameter addParameter(Parameter parameter) {
        return dataUtils.saveParameter(parameter);
    }

    public List<Parameter> getParameters() {
        log.info("get parameters {}");
        return new ArrayList<>();
    }

    public Parameter getParameterById(Integer id) {
        log.info("get parameter by id {}", id);

        Parameter parameter = Parameter.builder()
                .version(1)
                .name("marat.autov")
                .description("Skills")
                .value("the best architect")
                .subscriptions(new ArrayList<>())
                .build();
        parameter.getSubscriptions()
                .add(Subscription.builder()
                        .version(1)
                        .name("Подписка №1")
                        .build());
        return parameter;
    }

    public Parameter updateParameter(Parameter parameter) {
        return dataUtils.saveParameter(parameter);
    }

    public Boolean deleteParameterById(Integer id) {
        return false;
    }
}
