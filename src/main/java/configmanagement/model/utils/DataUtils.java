package configmanagement.model.utils;

import static org.jooq.impl.DSL.constraint;

import configmanagement.model.Parameter2SubscriptionTable;
import configmanagement.model.ParameterTable;
import configmanagement.model.Subscriber2SubscriptionTable;
import configmanagement.model.SubscriberTable;
import configmanagement.model.SubscriptionTable;
import java.util.Collection;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.UpdatableRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@SuppressWarnings("unchecked")
public class DataUtils {
    private static final Logger log = LogManager.getLogger(DataUtils.class);

    private final DSLContext dslContext;

    @Autowired
    public DataUtils(@Qualifier("dslContext") DSLContext context) {
        this.dslContext = context;
    }

    /**
     * Создание таблиц
     */
    @Transactional
    public void initTables() {
        // создать таблицу параметров
        log.info("Table {} is creating", ParameterTable.INSTANCE.getQualifiedName());
        dslContext.createTableIfNotExists(ParameterTable.INSTANCE.getQualifiedName())
                .columns(ParameterTable.INSTANCE.getColumns())
                .constraints(
                        constraint(ParameterTable.PK_NAME)
                                .primaryKey(ParameterTable.INSTANCE.getPrimaryKey().getFieldsArray())
                ).execute();
        log.info("Table {} has been created successfully", ParameterTable.INSTANCE.getQualifiedName());

        // создать таблицу подписок
        log.info("Table {} is creating", SubscriptionTable.INSTANCE.getQualifiedName());
        dslContext.createTableIfNotExists(SubscriptionTable.INSTANCE.getQualifiedName())
                .columns(SubscriptionTable.INSTANCE.getColumns())
                .constraints(
                        constraint(SubscriptionTable.PK_NAME)
                                .primaryKey(SubscriptionTable.INSTANCE.getPrimaryKey().getFieldsArray())
                ).execute();
        log.info("Table {} has been created successfully", SubscriptionTable.INSTANCE.getQualifiedName());

        // создать таблицу подписчиков
        log.info("Table {} is creating", SubscriberTable.INSTANCE.getQualifiedName());
        dslContext.createTableIfNotExists(SubscriberTable.INSTANCE.getQualifiedName())
                .columns(SubscriberTable.INSTANCE.getColumns())
                .constraints(
                        constraint(SubscriberTable.PK_NAME)
                                .primaryKey(SubscriberTable.INSTANCE.getPrimaryKey().getFieldsArray())
                ).execute();
        log.info("Table {} has been created successfully", SubscriberTable.INSTANCE.getQualifiedName());

        // создать таблицу связей 'подписчики-подписки'
        log.info("Table {} is creating", Subscriber2SubscriptionTable.INSTANCE.getQualifiedName());
        dslContext.createTableIfNotExists(Subscriber2SubscriptionTable.INSTANCE.getQualifiedName())
                .columns(Subscriber2SubscriptionTable.INSTANCE.getColumns())
                .constraints(
                        constraint(Subscriber2SubscriptionTable.PK_NAME).primaryKey(
                                Subscriber2SubscriptionTable.INSTANCE.getPrimaryKey().getFieldsArray()
                        ),
                        constraint("FK_" + Subscriber2SubscriptionTable.INSTANCE.getName() + "_SUBSCRIBER").foreignKey(
                                Subscriber2SubscriptionTable.INSTANCE.SUBSCRIBER_ID)
                                .references(SubscriberTable.INSTANCE)
                                .onDeleteCascade(),
                        constraint("FK_" + Subscriber2SubscriptionTable.INSTANCE.getName() + "_SUBSCRIPTION")
                                .foreignKey(
                                        Subscriber2SubscriptionTable.INSTANCE.SUBSCRIPTION_ID)
                                .references(SubscriptionTable.INSTANCE)
                                .onDeleteCascade()
                ).execute();
        log.info("Table {} has been created successfully", Subscriber2SubscriptionTable.INSTANCE.getQualifiedName());

        // создать таблицу связей 'параметры-подписки'
        log.info("Table {} is creating", Parameter2SubscriptionTable.INSTANCE.getQualifiedName());
        dslContext.createTableIfNotExists(Parameter2SubscriptionTable.INSTANCE.getQualifiedName())
                .columns(Parameter2SubscriptionTable.INSTANCE.getColumns())
                .constraints(
                        constraint(Parameter2SubscriptionTable.PK_NAME).primaryKey(
                                Parameter2SubscriptionTable.INSTANCE.getPrimaryKey().getFieldsArray()
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
     * Сохранить и вернуть обновленные данные
     *
     * @param record
     * @param <R>
     * @return
     */
    public <R extends UpdatableRecord<R>> R store(@NonNull R record) {
        record.attach(dslContext.configuration());
        record.store();
        return record;
    }

    /**
     * @return
     */
    public <R extends Record, T extends Table<R>> Collection<R> getAllRecords(@NonNull T table) {
        Result<R> records = (Result<R>) dslContext
                .select()
                .from(table)
                .fetch();
        return records;
    }

    /**
     * Получить запись по первичному ключу
     *
     * @param id Идентификатор
     * @param table Таблица
     * @param pkField Поле первичного ключа
     * @param <R> Тип записи
     * @param <T> Тип таблицы
     * @return Найденная запись в таблице
     */
    public <R extends Record, T extends Table<R>> R getRecordById(@NonNull T table, @NonNull Field<Integer> pkField,
            @NonNull Integer id) {
        R record = (R) dslContext
                .select()
                .from(table)
                .where(pkField.eq(id))
                .fetchOne();
        return record;
    }

    /**
     * Удалить запись по первичному ключу
     *
     * @param table Таблица
     * @param pkField Поле первичного ключа
     * @param id Идентификатор
     * @param <R> Тип записи
     * @param <T> Тип таблицы
     * @return Результат удаления записи. {@code true} если запись удалена, иначе {@code false}
     */
    public <R extends Record, T extends Table<R>> Boolean deleteRecordById(@NonNull T table,
            @NonNull Field<Integer> pkField, @NonNull Integer id) {
        return dslContext.deleteFrom(table).where(pkField.eq(id)).execute() != 0;
    }

    /**
     * @param table Таблица
     * @param pkField Поле первичного ключа
     * @param id Идентификатор
     * @param <R> Тип записи
     * @param <T> Тип таблицы
     * @return
     */
    public <R extends Record, T extends Table<R>> boolean isRecordExists(@NonNull T table,
            @NonNull Field<Integer> pkField,
            @NonNull Integer id) {
        return dslContext.fetchExists(dslContext.select().from(table).where(pkField.eq(id)));
    }

    public DSLContext getDslContext() {
        return dslContext;
    }
}
