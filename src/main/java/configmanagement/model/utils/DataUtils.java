package configmanagement.model.utils;

import configmanagement.domain.Parameter;
import configmanagement.domain.Subscriber;
import configmanagement.domain.Subscription;
import configmanagement.mapper.Mapper;
import configmanagement.model.Parameter2SubscriptionTable;
import configmanagement.model.ParameterRecord;
import configmanagement.model.ParameterTable;
import configmanagement.model.Subscriber2SubscriptionTable;
import configmanagement.model.SubscriberRecord;
import configmanagement.model.SubscriberTable;
import configmanagement.model.SubscriptionRecord;
import configmanagement.model.SubscriptionTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.UpdatableRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.jooq.impl.DSL.constraint;

@Component
public class DataUtils {
    private static final Logger log = LogManager.getLogger(DataUtils.class);

    private final DSLContext dslContext;
    private final Mapper mapper;

    @Autowired
    public DataUtils(@Qualifier("default") DSLContext context, Mapper mapper) {
        this.dslContext = context;
        this.mapper = mapper;
    }

    @Transactional
    public void initTables() {
        // создать таблицу параметров
        log.info("Table {} is creating", ParameterTable.INSTANCE.getQualifiedName());
        dslContext.createTableIfNotExists(ParameterTable.INSTANCE.getQualifiedName())
                .column(ParameterTable.INSTANCE.ID)
                .column(ParameterTable.INSTANCE.VERSION)
                .column(ParameterTable.INSTANCE.CREATE_DATE)
                .column(ParameterTable.INSTANCE.UPDATE_DATE)
                .column(ParameterTable.INSTANCE.OBSOLETE)
                .column(ParameterTable.INSTANCE.NAME)
                .column(ParameterTable.INSTANCE.DESCRIPTION)
                .column(ParameterTable.INSTANCE.VALUE)
                .constraints(
                        constraint("PK_" + ParameterTable.INSTANCE.getName()).primaryKey(ParameterTable.INSTANCE.ID)
                ).execute();
        log.info("Table {} has been created successfully", ParameterTable.INSTANCE.getQualifiedName());

        // создать таблицу подписок
        log.info("Table {} is creating", SubscriptionTable.INSTANCE.getQualifiedName());
        dslContext.createTableIfNotExists(SubscriptionTable.INSTANCE.getQualifiedName())
                .column(SubscriptionTable.INSTANCE.ID)
                .column(SubscriptionTable.INSTANCE.VERSION)
                .column(SubscriptionTable.INSTANCE.CREATE_DATE)
                .column(SubscriptionTable.INSTANCE.UPDATE_DATE)
                .column(SubscriptionTable.INSTANCE.OBSOLETE)
                .column(SubscriptionTable.INSTANCE.NAME)
                .column(SubscriptionTable.INSTANCE.DESCRIPTION)
                .constraints(
                        constraint("PK_" + SubscriptionTable.INSTANCE.getName()).primaryKey(SubscriptionTable.INSTANCE.ID)
                ).execute();
        log.info("Table {} has been created successfully", SubscriptionTable.INSTANCE.getQualifiedName());

        // создать таблицу подписчиков
        log.info("Table {} is creating", SubscriberTable.INSTANCE.getQualifiedName());
        dslContext.createTableIfNotExists(SubscriberTable.INSTANCE.getQualifiedName())
                .column(SubscriberTable.INSTANCE.ID)
                .column(SubscriberTable.INSTANCE.VERSION)
                .column(SubscriberTable.INSTANCE.CREATE_DATE)
                .column(SubscriberTable.INSTANCE.UPDATE_DATE)
                .column(SubscriberTable.INSTANCE.OBSOLETE)
                .column(SubscriberTable.INSTANCE.NAME)
                .column(SubscriberTable.INSTANCE.DESCRIPTION)
                .constraints(
                        constraint("PK_" + SubscriberTable.INSTANCE.getName()).primaryKey(SubscriberTable.INSTANCE.ID)
                ).execute();
        log.info("Table {} has been created successfully", SubscriberTable.INSTANCE.getQualifiedName());

        // создать таблицу связей 'подписчики-подписки'
        log.info("Table {} is creating", Subscriber2SubscriptionTable.INSTANCE.getQualifiedName());
        dslContext.createTableIfNotExists(Subscriber2SubscriptionTable.INSTANCE.getQualifiedName())
                .column(Subscriber2SubscriptionTable.INSTANCE.SUBSCRIBER_ID)
                .column(Subscriber2SubscriptionTable.INSTANCE.SUBSCRIPTION_ID)
                .constraints(
                        constraint("PK_" + Subscriber2SubscriptionTable.INSTANCE.getName()).primaryKey(
                                Subscriber2SubscriptionTable.INSTANCE.SUBSCRIBER_ID,
                                Subscriber2SubscriptionTable.INSTANCE.SUBSCRIPTION_ID
                        ),
                        constraint("FK_" + Subscriber2SubscriptionTable.INSTANCE.getName() + "_SUBSCRIBER").foreignKey(
                                Subscriber2SubscriptionTable.INSTANCE.SUBSCRIBER_ID)
                                .references(SubscriberTable.INSTANCE)
                                .onDeleteCascade(),
                        constraint("FK_" + Subscriber2SubscriptionTable.INSTANCE.getName() + "_SUBSCRIPTION").foreignKey(
                                Subscriber2SubscriptionTable.INSTANCE.SUBSCRIPTION_ID)
                                .references(SubscriptionTable.INSTANCE)
                                .onDeleteCascade()
                ).execute();
        log.info("Table {} has been created successfully", Subscriber2SubscriptionTable.INSTANCE.getQualifiedName());

        // создать таблицу связей 'параметры-подписки'
        log.info("Table {} is creating", Parameter2SubscriptionTable.INSTANCE.getQualifiedName());
        dslContext.createTableIfNotExists(Parameter2SubscriptionTable.INSTANCE.getQualifiedName())
                .column(Parameter2SubscriptionTable.INSTANCE.PARAMETER_ID)
                .column(Parameter2SubscriptionTable.INSTANCE.SUBSCRIPTION_ID)
                .constraints(
                        constraint("PK_" + Parameter2SubscriptionTable.INSTANCE.getName()).primaryKey(
                                Parameter2SubscriptionTable.INSTANCE.PARAMETER_ID,
                                Parameter2SubscriptionTable.INSTANCE.SUBSCRIPTION_ID
                        ),
                        constraint("FK_" + Parameter2SubscriptionTable.INSTANCE.getName() + "_PARAMETER").foreignKey(
                                Parameter2SubscriptionTable.INSTANCE.PARAMETER_ID)
                                .references(ParameterTable.INSTANCE)
                                .onDeleteCascade(),
                        constraint("FK_" + Parameter2SubscriptionTable.INSTANCE.getName() + "_SUBSCRIPTION").foreignKey(
                                Parameter2SubscriptionTable.INSTANCE.SUBSCRIPTION_ID)
                                .references(SubscriptionTable.INSTANCE)
                                .onDeleteCascade()
                ).execute();
        log.info("Table {} has been created successfully", Parameter2SubscriptionTable.INSTANCE.getQualifiedName());

    }

    /**
     * Сохранить данные по подписчику
     *
     * @param subscriber Подписчик
     * @return обновленные данные
     */
    public Subscriber saveSubscriber(Subscriber subscriber) {
        if (subscriber == null) {
            throw new NullPointerException("The 'subscriber' cannot be null.");
        }

        return saveDomain(subscriber, SubscriberRecord.class);
    }

    /**
     * Сохранить данные параметра
     *
     * @param parameter Параметр
     * @return обновленные данные
     */
    public Parameter saveParameter(Parameter parameter) {
        if (parameter == null) {
            throw new NullPointerException("The 'parameter' cannot be null.");
        }

        return saveDomain(parameter, ParameterRecord.class);
    }

    /**
     * Сохранить данные подписки
     *
     * @param subscription Подписка
     * @return обновленные данные
     */
    public Subscription saveSubscription(Subscription subscription) {
        if (subscription == null) {
            throw new NullPointerException("The 'subscription' cannot be null.");
        }

        return saveDomain(subscription, SubscriptionRecord.class);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    <U extends UpdatableRecord, D> D saveDomain(D domain, Class<U> modelClass) {
        final U record = mapper.toModel(domain, modelClass);
        record.attach(dslContext.configuration());
        record.store();
        return mapper.toDomain(record, (Class<D>) domain.getClass());
    }
}
