package configmanagement.model;

import org.jooq.TableField;
import org.jooq.impl.CustomTable;
import org.jooq.impl.SQLDataType;

import static org.jooq.impl.DSL.name;

public class Subscriber2SubscriptionTable extends CustomTable<Subscriber2SubscriptionRecord> {
    public static final Subscriber2SubscriptionTable INSTANCE = new Subscriber2SubscriptionTable();

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
}
