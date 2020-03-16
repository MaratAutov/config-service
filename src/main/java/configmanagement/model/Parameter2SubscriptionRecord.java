package configmanagement.model;

import org.jooq.impl.UpdatableRecordImpl;

public class Parameter2SubscriptionRecord extends UpdatableRecordImpl<Parameter2SubscriptionRecord> {
    protected Parameter2SubscriptionRecord() {
        super(Parameter2SubscriptionTable.INSTANCE);
    }
}
