package configmanagement.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jooq.Constraint;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.CustomTable;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import static java.util.Arrays.asList;
import static org.jooq.impl.DSL.name;

public class Subscriber2SubscriptionTable extends CustomTable<Subscriber2SubscriptionRecord> {
    public static final Subscriber2SubscriptionTable INSTANCE = new Subscriber2SubscriptionTable();
    public static final String PK_NAME = "PK_SUBSCRIBERS_SUBSCRIPTIONS";

    public final TableField<Subscriber2SubscriptionRecord, Integer> SUBSCRIBER_ID = createField(name("SUBSCRIBER_ID"),
            SQLDataType.INTEGER);
    public final TableField<Subscriber2SubscriptionRecord, Integer> SUBSCRIPTION_ID = createField(
            name("SUBSCRIPTION_ID"),
            SQLDataType.INTEGER);

    protected Subscriber2SubscriptionTable() {
        super(name("REF_SUBSCRIBERS_SUBSCRIPTIONS"));
    }

    @Override
    public Class<? extends Subscriber2SubscriptionRecord> getRecordType() {
        return Subscriber2SubscriptionRecord.class;
    }

    @Override
    public UniqueKey<Subscriber2SubscriptionRecord> getPrimaryKey() {
        return new UniqueKey<Subscriber2SubscriptionRecord>() {
            @Override
            public List<ForeignKey<?, Subscriber2SubscriptionRecord>> getReferences() {
                return new ArrayList<>();
            }

            @Override
            public boolean isPrimary() {
                return true;
            }

            @Override
            public String getName() {
                return PK_NAME;
            }

            @Override
            public Table<Subscriber2SubscriptionRecord> getTable() {
                return INSTANCE;
            }

            @Override
            public List<TableField<Subscriber2SubscriptionRecord, ?>> getFields() {
                return asList(INSTANCE.SUBSCRIBER_ID, INSTANCE.SUBSCRIPTION_ID);
            }

            @Override
            public TableField<Subscriber2SubscriptionRecord, ?>[] getFieldsArray() {
                return new TableField[]{INSTANCE.SUBSCRIBER_ID, INSTANCE.SUBSCRIPTION_ID};
            }

            @Override
            public Constraint constraint() {
                return DSL.constraint(PK_NAME);
            }
        };
    }

    public Collection<? extends Field<?>> getColumns() {
        return asList(SUBSCRIBER_ID, SUBSCRIPTION_ID);
    }
}
