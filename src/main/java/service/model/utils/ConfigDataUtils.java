package service.model.utils;

import org.jooq.CreateTableColumnStep;
import org.jooq.CreateTableConstraintStep;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import service.model.Parameter2SubscriptionTable;
import service.model.ParameterTable;
import service.model.Subscriber2SubscriptionTable;
import service.model.SubscriberTable;
import service.model.SubscriptionTable;

import static org.jooq.impl.DSL.exists;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;
import static org.jooq.impl.DSL.upper;

@Component
public class ConfigDataUtils {

    private final DSLContext dslContext;

    @Autowired
    public ConfigDataUtils(@Qualifier("default") DSLContext context) {
        this.dslContext = context;
    }

    @Transactional
    public void initTables() {
        // создать таблицу параметров
        CreateTableColumnStep createTableStmt =
                dslContext.createTableIfNotExists(ParameterTable.PARAMETERS.getQualifiedName())
                .column(ParameterTable.PARAMETERS.ID)
                .column(ParameterTable.PARAMETERS.VERSION)
                .column(ParameterTable.PARAMETERS.CREATE_DATE)
                .column(ParameterTable.PARAMETERS.UPDATE_DATE)
                .column(ParameterTable.PARAMETERS.OBSOLETE)
                .column(ParameterTable.PARAMETERS.NAME)
                .column(ParameterTable.PARAMETERS.DESCRIPTION)
                .column(ParameterTable.PARAMETERS.VALUE);

        CreateTableConstraintStep createTableConstraintStep = createTableStmt;
        createTableConstraintStep.execute();
        //createTableStmt.execute();

        createTableStmt = 
        dslContext.createTableIfNotExists(SubscriptionTable.SUBSCRIPTIONS.getQualifiedName())
                .column(SubscriptionTable.SUBSCRIPTIONS.ID)
                .column(SubscriptionTable.SUBSCRIPTIONS.VERSION)
                .column(SubscriptionTable.SUBSCRIPTIONS.CREATE_DATE)
                .column(SubscriptionTable.SUBSCRIPTIONS.UPDATE_DATE)
                .column(SubscriptionTable.SUBSCRIPTIONS.OBSOLETE)
                .column(SubscriptionTable.SUBSCRIPTIONS.NAME)
                .column(SubscriptionTable.SUBSCRIPTIONS.DESCRIPTION);

        createTableStmt.execute();

        createTableStmt =
                dslContext.createTableIfNotExists(SubscriberTable.SUBSCRIBERS.getQualifiedName())
                        .column(SubscriberTable.SUBSCRIBERS.ID)
                        .column(SubscriberTable.SUBSCRIBERS.VERSION)
                        .column(SubscriberTable.SUBSCRIBERS.CREATE_DATE)
                        .column(SubscriberTable.SUBSCRIBERS.UPDATE_DATE)
                        .column(SubscriberTable.SUBSCRIBERS.OBSOLETE)
                        .column(SubscriberTable.SUBSCRIBERS.NAME)
                        .column(SubscriberTable.SUBSCRIBERS.DESCRIPTION);

        createTableStmt.execute();

        createTableStmt =
                dslContext.createTableIfNotExists(Subscriber2SubscriptionTable.SUBSCRIBER_TO_SUBSCRIPTION.getQualifiedName())
                        .column(Subscriber2SubscriptionTable.SUBSCRIBER_TO_SUBSCRIPTION.SUBSCRIBER_ID)
                        .column(Subscriber2SubscriptionTable.SUBSCRIBER_TO_SUBSCRIPTION.SUBSCRIPTION_ID);

        createTableStmt.execute();

        createTableStmt =
                dslContext.createTableIfNotExists(Parameter2SubscriptionTable.PARAMETER_TO_SUBSCRIPTION.getQualifiedName())
                        .column(Parameter2SubscriptionTable.PARAMETER_TO_SUBSCRIPTION.PARAMETER_ID)
                        .column(Parameter2SubscriptionTable.PARAMETER_TO_SUBSCRIPTION.SUBSCRIPTION_ID);

        createTableStmt.execute();
    }

    private boolean checkIfTableExists(String schemaName, String tableName) {

        Object result = dslContext.select(
                field(exists(
                        dslContext.selectOne()
                                .from(table("pg_tables"))
                                .where(upper(field(name("schemaname"), String.class))
                                        .eq(schemaName.toUpperCase())
                                        .and(upper(field(name("tablename"), String.class))
                                                .eq(tableName.toUpperCase())))

                )))
                .fetchAny(0);
        return Boolean.TRUE.equals(result);
    }


    private <R extends Record> void createTableFromModel(TableImpl<R> model) {
        if (model == null) {
            throw new NullPointerException("The parameter 'model' has not specified.");
        }

        CreateTableColumnStep createTableStmt = dslContext.createTableIfNotExists(name(model.getName()));

        model.fieldStream().forEach(f -> {

        });


    }

}
