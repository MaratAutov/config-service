package service.model;

import org.jooq.impl.CustomRecord;

public class Subscriber2SubscriptionRecord extends CustomRecord<Subscriber2SubscriptionRecord> {
    protected Subscriber2SubscriptionRecord() {
        super(Subscriber2SubscriptionTable.SUBSCRIBER_TO_SUBSCRIPTION);
    }
}
