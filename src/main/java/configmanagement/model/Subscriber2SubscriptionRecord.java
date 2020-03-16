package configmanagement.model;

import org.jooq.impl.UpdatableRecordImpl;

public class Subscriber2SubscriptionRecord extends UpdatableRecordImpl<Subscriber2SubscriptionRecord> {
    protected Subscriber2SubscriptionRecord() {
        super(Subscriber2SubscriptionTable.INSTANCE);
    }
}
