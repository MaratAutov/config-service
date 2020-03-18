package configmanagement.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.jooq.Constraint;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.CustomTable;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import static java.util.Arrays.asList;
import static org.jooq.impl.DSL.name;

public class SubscriberTable extends CustomTable<SubscriberRecord> {
    public static final SubscriberTable INSTANCE = new SubscriberTable();
    public static final String PK_NAME = "PK_SUBSCRIBERS";

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

    protected SubscriberTable() {
        super(name("SUBSCRIBERS"));
    }

    @Override
    public Class<? extends SubscriberRecord> getRecordType() {
        return SubscriberRecord.class;
    }

    @Override
    public Identity<SubscriberRecord, Integer> getIdentity() {
        return new Identity<SubscriberRecord, Integer>() {
            @Override
            public Table<SubscriberRecord> getTable() {
                return SubscriberTable.INSTANCE;
            }

            @Override
            public TableField<SubscriberRecord, Integer> getField() {
                return SubscriberTable.INSTANCE.ID;
            }
        };
    }

    @Override
    public UniqueKey<SubscriberRecord> getPrimaryKey() {
        return new UniqueKey<SubscriberRecord>() {
            @Override
            public List<ForeignKey<?, SubscriberRecord>> getReferences() {
                return new ArrayList<>();
            }

            @Override
            public boolean isPrimary() {
                return true;
            }

            @Override
            public String getName() {
                return PK_NAME;
            }

            @Override
            public Table<SubscriberRecord> getTable() {
                return INSTANCE;
            }

            @Override
            public List<TableField<SubscriberRecord, ?>> getFields() {
                return Collections.singletonList(INSTANCE.ID);
            }

            @Override
            public TableField<SubscriberRecord, ?>[] getFieldsArray() {
                return new TableField[]{INSTANCE.ID};
            }

            @Override
            public Constraint constraint() {
                return DSL.constraint(PK_NAME);
            }
        };
    }

    @Override
    public TableField<SubscriberRecord, ?> getRecordVersion() {
        return SubscriberTable.INSTANCE.VERSION;
    }

    public Collection<? extends Field<?>> getColumns() {
        return asList(ID, VERSION, CREATE_DATE, UPDATE_DATE, OBSOLETE, NAME, DESCRIPTION);
    }
}

