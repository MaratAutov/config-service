package service.model;

import static org.jooq.impl.DSL.name;

import org.jooq.TableField;
import org.jooq.impl.CustomTable;
import org.jooq.impl.SQLDataType;

public class Subscriber2SubscriptionTable extends CustomTable<Subscriber2SubscriptionRecord> {
    public static final Subscriber2SubscriptionTable SUBSCRIBER_TO_SUBSCRIPTION = new Subscriber2SubscriptionTable();

    public final TableField<Subscriber2SubscriptionRecord, Integer> SUBSCRIBER_ID = createField(name("SUBSCRIBER_ID"),
            SQLDataType.INTEGER);
    public final TableField<Subscriber2SubscriptionRecord, Integer> SUBSCRIPTION_ID = createField(
            name("SUBSCRIPTION_ID"),
            SQLDataType.INTEGER);

    protected Subscriber2SubscriptionTable() {
        super(name("SUBSCRIBER_SUBSCRIPTION"));
    }

    @Override
    public Class<? extends Subscriber2SubscriptionRecord> getRecordType() {
        return Subscriber2SubscriptionRecord.class;
    }
}
