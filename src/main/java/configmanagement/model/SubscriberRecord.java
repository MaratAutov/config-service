package configmanagement.model;

import java.sql.Timestamp;
import org.jooq.impl.UpdatableRecordImpl;

public class SubscriberRecord extends UpdatableRecordImpl<SubscriberRecord> {
    protected SubscriberRecord() {
        super(SubscriberTable.INSTANCE);
    }

    public Integer getId() {
        return get(SubscriberTable.INSTANCE.ID, Integer.class);
    }

    public void setId(Integer id) {
        set(SubscriberTable.INSTANCE.ID, id);
    }

    public Integer getVersion() {
        return get(SubscriberTable.INSTANCE.VERSION, Integer.class);
    }

    public void setVersion(Integer version) {
        set(SubscriberTable.INSTANCE.VERSION, version);
    }

    public Timestamp getCreateDate() {
        return get(SubscriberTable.INSTANCE.CREATE_DATE, Timestamp.class);
    }

    public Timestamp getUpdateDate() {
        return get(SubscriberTable.INSTANCE.UPDATE_DATE, Timestamp.class);
    }

    public String getName() {
        return get(SubscriberTable.INSTANCE.NAME, String.class);
    }

    public void setName(String name) {
        set(SubscriberTable.INSTANCE.NAME, name);
    }

    public String getDescription() {
        return get(SubscriberTable.INSTANCE.DESCRIPTION, String.class);
    }

    public void setDescription(String description) {
        set(SubscriberTable.INSTANCE.DESCRIPTION, description);
    }

    public void setObsolete(Boolean obsolete) {
        set(SubscriberTable.INSTANCE.OBSOLETE, obsolete);
    }
}