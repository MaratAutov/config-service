package configmanagement.services;

import configmanagement.domain.Subscriber;
import configmanagement.model.utils.DataUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService {
    private static final Logger log = LogManager.getLogger(SubscriberService.class);
    private final DataUtils dataUtils;

    @Autowired
    public SubscriberService(DataUtils dataUtils) {
        this.dataUtils = dataUtils;
    }

    public Subscriber addSubscriber(Subscriber subscriber) {
        return dataUtils.saveSubscriber(subscriber);
    }

    public List<Subscriber> getSubscribers() {
        return new ArrayList<>();
    }

    public Subscriber getSubcriberById(Integer id) {
        return null;
    }

    public Subscriber updateSubscriber(Subscriber subscriber) {
        return dataUtils.saveSubscriber(subscriber);
    }

    public boolean deleteSubscriberById(Integer id) {
        return false;
    }
}
