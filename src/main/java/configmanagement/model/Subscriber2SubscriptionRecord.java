package configmanagement.model;

import org.jooq.impl.UpdatableRecordImpl;

public class Subscriber2SubscriptionRecord extends UpdatableRecordImpl<Subscriber2SubscriptionRecord> {
    protected Subscriber2SubscriptionRecord() {
        super(Subscriber2SubscriptionTable.INSTANCE);
    }

    public Integer getSubscriberId() {
        return get(Subscriber2SubscriptionTable.INSTANCE.SUBSCRIBER_ID, Integer.class);
    }

    public void setSubscriberId(Integer value) {
        set(Subscriber2SubscriptionTable.INSTANCE.SUBSCRIBER_ID, value);
    }

    public Integer getSubscriptionId() {
        return get(Subscriber2SubscriptionTable.INSTANCE.SUBSCRIPTION_ID, Integer.class);
    }

    public void setSubscriptionId(Integer value) {
        set(Subscriber2SubscriptionTable.INSTANCE.SUBSCRIPTION_ID, value);
    }
}
