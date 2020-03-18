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

public class SubscriptionTable extends CustomTable<SubscriptionRecord> {
    public static final SubscriptionTable INSTANCE = new SubscriptionTable();
    public static final String PK_NAME = "PK_SUBSCRIPTIONS";

    public final TableField<SubscriptionRecord, Integer> ID = createField(name("ID"),
            SQLDataType.INTEGER.identity(true));
    public final TableField<SubscriptionRecord, Integer> VERSION = createField(name("VERSION"),
            SQLDataType.INTEGER.defaultValue(1));
    public final TableField<SubscriptionRecord, Timestamp> CREATE_DATE = createField(name("CREATE_DATE"),
            SQLDataType.TIMESTAMP.nullable(false));
    public final TableField<SubscriptionRecord, Timestamp> UPDATE_DATE = createField(name("UPDATE_DATE"),
            SQLDataType.TIMESTAMP.nullable(false));
    public final TableField<SubscriptionRecord, Boolean> OBSOLETE = createField(name("OBSOLETE"),
            SQLDataType.BOOLEAN);
    public final TableField<SubscriptionRecord, String> NAME = createField(name("NAME"),
            SQLDataType.VARCHAR(512));
    public final TableField<SubscriptionRecord, String> DESCRIPTION = createField(name("DESCRIPTION"),
            SQLDataType.VARCHAR(1024));

    protected SubscriptionTable() {
        super(name("SUBSCRIPTIONS"));
    }

    @Override
    public Class<? extends SubscriptionRecord> getRecordType() {
        return SubscriptionRecord.class;
    }

    @Override
    public Identity<SubscriptionRecord, Integer> getIdentity() {
        return new Identity<SubscriptionRecord, Integer>() {
            @Override
            public Table<SubscriptionRecord> getTable() {
                return SubscriptionTable.INSTANCE;
            }

            @Override
            public TableField<SubscriptionRecord, Integer> getField() {
                return SubscriptionTable.INSTANCE.ID;
            }
        };
    }

    @Override
    public UniqueKey<SubscriptionRecord> getPrimaryKey() {
        return new UniqueKey<SubscriptionRecord>() {
            @Override
            public List<ForeignKey<?, SubscriptionRecord>> getReferences() {
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
            public Table<SubscriptionRecord> getTable() {
                return INSTANCE;
            }

            @Override
            public List<TableField<SubscriptionRecord, ?>> getFields() {
                return Collections.singletonList(INSTANCE.ID);
            }

            @Override
            public TableField<SubscriptionRecord, ?>[] getFieldsArray() {
                return new TableField[]{INSTANCE.ID};
            }

            @Override
            public Constraint constraint() {
                return DSL.constraint(PK_NAME);
            }
        };
    }

    @Override
    public TableField<SubscriptionRecord, ?> getRecordVersion() {
        return SubscriptionTable.INSTANCE.VERSION;
    }

    public Collection<? extends Field<?>> getColumns() {
        return asList(ID, VERSION, CREATE_DATE, UPDATE_DATE, OBSOLETE, NAME, DESCRIPTION);
    }
}
