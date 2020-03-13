package service.model;

import org.jooq.impl.CustomRecord;

public class Parameter2SubscriptionRecord extends CustomRecord<Parameter2SubscriptionRecord> {
    protected Parameter2SubscriptionRecord() {
        super(Parameter2SubscriptionTable.PARAMETER_TO_SUBSCRIPTION);
    }
}
