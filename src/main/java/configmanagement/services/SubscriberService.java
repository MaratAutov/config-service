package configmanagement.services;

import configmanagement.domain.Subscriber;
import configmanagement.domain.Subscription;
import configmanagement.mapper.Mapper;
import configmanagement.model.Subscriber2SubscriptionRecord;
import configmanagement.model.Subscriber2SubscriptionTable;
import configmanagement.model.SubscriberRecord;
import configmanagement.model.SubscriberTable;
import configmanagement.model.utils.DataUtils;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService extends AbstractBaseService {
    private static final Logger log = LogManager.getLogger(SubscriberService.class);

    @Autowired
    public SubscriberService(DataUtils dataUtils, Mapper mapper) {
        super(dataUtils, mapper);
    }

    /**
     * Добавить данные подписчика
     *
     * @param subscriber информация о подписчике
     * @return добавленные данные
     */
    public Subscriber addSubscriber(@NonNull Subscriber subscriber) {
        if (subscriber.getId() != null) {
            throw new IllegalArgumentException("The 'subscriber' has been stored already. Use updateSubscriber method.");
        }

        final SubscriberRecord record = getMapper().toModel(subscriber, SubscriberRecord.class);
        return getMapper().toDomain(getDataUtils().store(record), Subscriber.class);
    }

    /**
     * Получить список всех подписчиков
     *
     * @return
     */
    public List<Subscriber> getSubscribers() {
        return getDataUtils().getAllRecords(SubscriberTable.INSTANCE)
                .stream()
                .map(item -> getMapper().toDomain(item, Subscriber.class))
                .collect(Collectors.toList());
    }

    /**
     * Получить информацию о подписчике по идентификатору
     *
     * @param id идентификатор
     * @return найденная информация
     */
    public Subscriber getSubcriberById(@NonNull Integer id) {
        SubscriberRecord record = getDataUtils().getRecordById(SubscriberTable.INSTANCE, SubscriberTable.INSTANCE.ID, id);
        return getMapper().toDomain(record, Subscriber.class);
    }

    /**
     * Обновить данные подписчика
     *
     * @param subscriber информация о подписчике
     * @return обновленные данные
     */
    public Subscriber updateSubscriber(@NonNull Subscriber subscriber) {
        if (subscriber.getId() == null) {
            throw new IllegalArgumentException("The 'subscriber' has not been stored yet. Use addSubscriber method.");
        }

        final SubscriberRecord record = getMapper().toModel(subscriber, SubscriberRecord.class);
        return getMapper().toDomain(getDataUtils().store(record), Subscriber.class);
    }

    /**
     * Удалить информацию о подписчике по идентификатору
     *
     * @param id идентификатор
     * @return
     */
    public boolean deleteSubscriberById(@NonNull Integer id) {
        return getDataUtils().deleteRecordById(SubscriberTable.INSTANCE, SubscriberTable.INSTANCE.ID, id);
    }

    /**
     * @param id
     * @param subscription
     */
    public void addSubscription2Subscriber(@NonNull Integer id, @NonNull Subscription subscription) {
        if (!getDataUtils().isRecordExists(SubscriberTable.INSTANCE, SubscriberTable.INSTANCE.ID, id)) {
            throw new NullPointerException("The subscriber by id (" + id + ") has not found.");
        }
        if (subscription.getId() == null) {
            throw new IllegalArgumentException("The 'subscription' has not been stored.");
        }

        Subscriber2SubscriptionRecord record = Subscriber2SubscriptionTable.INSTANCE.newRecord();
        record.setSubscriberId(id);
        record.setSubscriptionId(subscription.getId());
        getDataUtils().store(record);
    }
}
