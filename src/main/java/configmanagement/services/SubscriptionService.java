package configmanagement.services;

import configmanagement.domain.Subscription;
import configmanagement.model.utils.DataUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    private static final Logger log = LogManager.getLogger(SubscriptionService.class);
    private final DataUtils dataUtils;

    @Autowired
    public SubscriptionService(DataUtils dataUtils) {
        this.dataUtils = dataUtils;
    }

    public Subscription addSuscription(Subscription subscription) {
        return dataUtils.saveSubscription(subscription);
    }

    public List<Subscription> getSubscriptions() {
        return new ArrayList<>();
    }

    public Subscription getSubscriptionById(Integer id) {
        return null;
    }

    public Subscription updateSubscription(Subscription subscription) {
        return dataUtils.saveSubscription(subscription);
    }

    public boolean deleteSubscriptionById(Integer id) {
        return false;
    }
}
