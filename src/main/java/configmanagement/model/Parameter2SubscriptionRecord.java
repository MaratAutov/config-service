package configmanagement.model;

import org.jooq.impl.UpdatableRecordImpl;

public class Parameter2SubscriptionRecord extends UpdatableRecordImpl<Parameter2SubscriptionRecord> {
    protected Parameter2SubscriptionRecord() {
        super(Parameter2SubscriptionTable.INSTANCE);
    }

    public Integer getParameterId() {
        return get(Parameter2SubscriptionTable.INSTANCE.PARAMETER_ID, Integer.class);
    }

    public void setParameterId(Integer value) {
        set(Parameter2SubscriptionTable.INSTANCE.PARAMETER_ID, value);
    }

    public Integer getSubscriptionId() {
        return get(Parameter2SubscriptionTable.INSTANCE.SUBSCRIPTION_ID, Integer.class);
    }

    public void setSubscriptionId(Integer value) {
        set(Parameter2SubscriptionTable.INSTANCE.SUBSCRIPTION_ID, value);
    }
}
