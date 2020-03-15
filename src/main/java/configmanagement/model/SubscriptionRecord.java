package configmanagement.model;

import org.jooq.impl.CustomRecord;

public class SubscriptionRecord extends CustomRecord<SubscriptionRecord> {
    protected SubscriptionRecord() {
        super(SubscriptionTable.INSTANCE);
    }
}