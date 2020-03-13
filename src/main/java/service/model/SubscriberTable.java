package service.model;

import static org.jooq.impl.DSL.name;

import java.sql.Timestamp;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;
import org.jooq.impl.SQLDataType;

public class SubscriberTable extends CustomTable<SubscriberRecord> {
    public static final SubscriberTable SUBSCRIBERS = new SubscriberTable();

    public final TableField<SubscriberRecord, Integer> ID = createField(name("ID"), SQLDataType.INTEGER.identity(true));
    public final TableField<SubscriberRecord, Integer> VERSION = createField(name("VERSION"),
            SQLDataType.INTEGER.defaultValue(1));
    public final TableField<SubscriberRecord, Timestamp> CREATE_DATE = createField(name("CREATE_DATE"),
            SQLDataType.TIMESTAMP.nullable(false));
    public final TableField<SubscriberRecord, Timestamp> UPDATE_DATE = createField(name("UPDATE_DATE"),
            SQLDataType.TIMESTAMP.nullable(false));
    public final TableField<SubscriberRecord, Boolean> OBSOLETE = createField(name("OBSOLETE"),
            SQLDataType.BOOLEAN);
    public final TableField<SubscriberRecord, String> NAME = createField(name("NAME"),
            SQLDataType.VARCHAR(512));
    public final TableField<SubscriberRecord, String> DESCRIPTION = createField(name("DESCRIPTION"),
            SQLDataType.VARCHAR(1024));
    public final TableField<SubscriberRecord, String> VALUE = createField(name("VALUE"),
            SQLDataType.VARCHAR(1024));

    protected SubscriberTable() {
        super(name("SUBSCRIBERS"));
    }

    @Override
    public Class<? extends SubscriberRecord> getRecordType() {
        return SubscriberRecord.class;
    }
}

