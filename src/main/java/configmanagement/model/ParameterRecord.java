package configmanagement.model;

import java.sql.Timestamp;
import org.jooq.impl.UpdatableRecordImpl;

public class ParameterRecord extends UpdatableRecordImpl<ParameterRecord> {
    protected ParameterRecord() {
        super(ParameterTable.INSTANCE);
    }

    public Integer getId() {
        return get(ParameterTable.INSTANCE.ID, Integer.class);
    }

    public Integer getVersion() {
        return get(ParameterTable.INSTANCE.VERSION, Integer.class);
    }

    public void setVersion(Integer version) {
        set(ParameterTable.INSTANCE.VERSION, version);
    }

    public Timestamp getCreateDate() {
        return get(ParameterTable.INSTANCE.CREATE_DATE, Timestamp.class);
    }

    public Timestamp getUpdateDate() {
        return get(ParameterTable.INSTANCE.UPDATE_DATE, Timestamp.class);
    }

    public String getName() {
        return get(ParameterTable.INSTANCE.NAME, String.class);
    }

    public void setName(String name) {
        set(ParameterTable.INSTANCE.NAME, name);
    }

    public String getDescription() {
        return get(ParameterTable.INSTANCE.DESCRIPTION, String.class);
    }

    public void setDescription(String description) {
        set(ParameterTable.INSTANCE.DESCRIPTION, description);
    }

    public void setObsolete(Boolean obsolete) {
        set(ParameterTable.INSTANCE.OBSOLETE, obsolete);
    }
}