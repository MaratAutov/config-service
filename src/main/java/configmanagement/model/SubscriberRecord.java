package configmanagement.model;

import org.jooq.impl.CustomRecord;

public class SubscriberRecord extends CustomRecord<SubscriberRecord> {
    protected SubscriberRecord() {
        super(SubscriberTable.INSTANCE);
    }
}