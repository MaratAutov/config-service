package configmanagement.model;

import java.sql.Timestamp;
import org.jooq.impl.UpdatableRecordImpl;

public class SubscriptionRecord extends UpdatableRecordImpl<SubscriptionRecord> {
    protected SubscriptionRecord() {
        super(SubscriptionTable.INSTANCE);
    }

    public Integer getId() {
        return get(SubscriptionTable.INSTANCE.ID, Integer.class);
    }

    public Integer getVersion() {
        return get(SubscriptionTable.INSTANCE.VERSION, Integer.class);
    }

    public Timestamp getCreateDate() {
        return get(SubscriptionTable.INSTANCE.CREATE_DATE, Timestamp.class);
    }

    public Timestamp getUpdateDate() {
        return get(SubscriptionTable.INSTANCE.UPDATE_DATE, Timestamp.class);
    }

    public String getName() {
        return get(SubscriptionTable.INSTANCE.NAME, String.class);
    }

    public void setName(String name) {
        set(SubscriptionTable.INSTANCE.NAME, name);
    }

    public String getDescription() {
        return get(SubscriptionTable.INSTANCE.DESCRIPTION, String.class);
    }

    public void setDescription(String description) {
        set(SubscriptionTable.INSTANCE.DESCRIPTION, description);
    }

    public void setObsolete(Boolean obsolete) {
        set(SubscriptionTable.INSTANCE.OBSOLETE, obsolete);
    }
}