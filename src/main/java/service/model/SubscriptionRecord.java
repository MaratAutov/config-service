package service.model;

import org.jooq.impl.CustomRecord;

public class SubscriptionRecord extends CustomRecord<SubscriptionRecord> {
    protected SubscriptionRecord() {
        super(SubscriptionTable.SUBSCRIPTIONS);
    }
}