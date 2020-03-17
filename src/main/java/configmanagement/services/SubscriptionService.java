package configmanagement.services;

import configmanagement.domain.Parameter;
import configmanagement.domain.Subscriber;
import configmanagement.domain.Subscription;
import configmanagement.mapper.Mapper;
import configmanagement.model.Parameter2SubscriptionRecord;
import configmanagement.model.Parameter2SubscriptionTable;
import configmanagement.model.Subscriber2SubscriptionRecord;
import configmanagement.model.Subscriber2SubscriptionTable;
import configmanagement.model.SubscriptionRecord;
import configmanagement.model.SubscriptionTable;
import configmanagement.model.utils.DataUtils;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService extends AbstractBaseService {
    private static final Logger log = LogManager.getLogger(SubscriptionService.class);

    @Autowired
    public SubscriptionService(DataUtils dataUtils, Mapper mapper) {
        super(dataUtils, mapper);
    }

    /**
     * Добавить данные подписки
     *
     * @param subscription информация о подписке
     * @return добавленные данные
     */
    public Subscription addSuscription(@NonNull Subscription subscription) {
        if (subscription.getId() != null) {
            throw new IllegalArgumentException("The 'subscription' has been stored already. Use updateSubscription method.");
        }

        final SubscriptionRecord record = getMapper().toModel(subscription, SubscriptionRecord.class);
        return getMapper().toDomain(getDataUtils().store(record), Subscription.class);
    }

    /**
     * Получить список всех подписок
     *
     * @return
     */
    public List<Subscription> getSubscriptions() {
        return getDataUtils().getAllRecords(SubscriptionTable.INSTANCE)
                .stream()
                .map(item -> getMapper().toDomain(item, Subscription.class))
                .collect(Collectors.toList());
    }

    /**
     * Получить информацию о подписке по идентификатору
     *
     * @param id идентификатор
     * @return найденная информация
     */
    public Subscription getSubscriptionById(@NonNull Integer id) {
        SubscriptionRecord record = getDataUtils().getRecordById(SubscriptionTable.INSTANCE, SubscriptionTable.INSTANCE.ID, id);
        return getMapper().toDomain(record, Subscription.class);
    }

    /**
     * Обновить данные подписки
     *
     * @param subscription информация о подписке
     * @return обновленные данные
     */
    public Subscription updateSubscription(@NonNull Subscription subscription) {
        if (subscription.getId() == null) {
            throw new IllegalArgumentException("The 'subscription' has not been stored yet. Use addSubscription method.");
        }

        final SubscriptionRecord record = getMapper().toModel(subscription, SubscriptionRecord.class);
        return getMapper().toDomain(getDataUtils().store(record), Subscription.class);
    }

    /**
     * Удалить информацию о подписке по идентификатору
     *
     * @param id идентификатор
     * @return
     */
    public boolean deleteSubscriptionById(@NonNull Integer id) {
        return getDataUtils().deleteRecordById(SubscriptionTable.INSTANCE, SubscriptionTable.INSTANCE.ID, id);
    }

    /**
     * @param id
     * @param parameter
     */
    public void addParameter2Subscription(@NonNull Integer id, @NonNull Parameter parameter) {
        if (!getDataUtils().isRecordExists(SubscriptionTable.INSTANCE, SubscriptionTable.INSTANCE.ID, id)) {
            throw new NullPointerException("The subscription by id (" + id + ") has not found.");
        }
        if (parameter.getId() == null) {
            throw new IllegalArgumentException("The 'parameter' has not been stored.");
        }

        Parameter2SubscriptionRecord record = Parameter2SubscriptionTable.INSTANCE.newRecord();
        record.setParameterId(parameter.getId());
        record.setSubscriptionId(id);
        getDataUtils().store(record);
    }

    /**
     * @param id
     * @param subscriber
     */
    public void addSubscriber2Subscription(@NonNull Integer id, @NonNull Subscriber subscriber) {
        if (!getDataUtils().isRecordExists(SubscriptionTable.INSTANCE, SubscriptionTable.INSTANCE.ID, id)) {
            throw new NullPointerException("The subscription by id (" + id + ") has not found.");
        }
        if (subscriber.getId() == null) {
            throw new IllegalArgumentException("The 'subscriber' has not been stored.");
        }

        Subscriber2SubscriptionRecord record = Subscriber2SubscriptionTable.INSTANCE.newRecord();
        record.setSubscriberId(subscriber.getId());
        record.setSubscriptionId(id);
        getDataUtils().store(record);
    }
}
